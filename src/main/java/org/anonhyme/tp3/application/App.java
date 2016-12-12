package org.anonhyme.tp3.application;

import lombok.Getter;
import org.anonhyme.tp3.dao.*;
import org.anonhyme.tp3.entity.*;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Anonhyme on 12/4/2016.
 */

public class App {
    private static final String HTML_CONTAINER = "<html>%s</html>";
    private static final String HTML_LIST_CONTAINER = "<ul>%s</ul>";
    private static final String HTML_RESULTAT_LISTE_TEMPLATE = "<li></li>";
    private ArrayList<JTextComponent> inputComponents = new ArrayList<>();
    private RootFrame frame;
    private ChroniqueurDao chroniqueurDao = new ChroniqueurDao();
    private JournalisteDao journalisteDao = new JournalisteDao();
    private CategorieArticleDao categorieArticleDao = new CategorieArticleDao();
    private ChroniqueDao chroniqueDao = new ChroniqueDao();
    private ArticleDao articleDao = new ArticleDao();
    private List<JournalisteEntity> journalistes;
    private List<ChroniqueurEntity> chroniqueurs;

    private ReponseFormulaire formulaire;
    @Getter
    private DefaultComboBoxModel modelComboBoxAuteurSearch;
    private DefaultComboBoxModel modelComboBoxAuteur;
    @Getter
    private DefaultComboBoxModel modelComboBoxCategorieSearch;
    private DefaultComboBoxModel modelComboBoxCategorie;

    private ArticleEntity derniereSelection;

    public App() {
        this.frame = new RootFrame();
        this.refreshDataModel();

        frame.getComboboxAuteur().setModel(modelComboBoxAuteur);
        frame.getComboBoxCategorie().setModel(modelComboBoxCategorie);
        frame.getComboboxAuteur().setSelectedIndex(-1);
        frame.getComboBoxCategorie().setSelectedIndex(-1);


        formulaire = new ReponseFormulaire();
        setButtonAction();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App app = new App();
                app.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void refreshDataModel() {
        chroniqueurs = chroniqueurDao.getChroniqueurs();
        journalistes = journalisteDao.getJournalistes();
        modelComboBoxCategorie = new DefaultComboBoxModel(categorieArticleDao.getCategories().toArray());
        modelComboBoxCategorieSearch = new DefaultComboBoxModel(categorieArticleDao.getCategories().toArray());

        modelComboBoxAuteur = new DefaultComboBoxModel(chroniqueurs.toArray());
        modelComboBoxAuteurSearch = new DefaultComboBoxModel(chroniqueurs.toArray());

        for(JournalisteEntity journaliste : journalistes) {
            modelComboBoxAuteurSearch.addElement(journaliste);
            modelComboBoxAuteur.addElement(journaliste);
        }
    }

    public void setFormulaire(ReponseFormulaire formulaire) {
        this.formulaire = formulaire;
    }

    private void setButtonAction() {
        frame.getBtnRechercher().addActionListener(e -> {
            SearchDialog searchDialog = new SearchDialog(this.frame, this);
            searchDialog.setVisible(true);
            frame.getModelListeArticle().removeAllElements();
            if(!searchDialog.isCancel()) {
                doSearch();
            }
        });
        frame.getBtnPurger().addActionListener(e -> this.clearInput());

        frame.getBtnAjouter().addActionListener(e -> {
            this.derniereSelection = (ArticleEntity) frame.getModelListeArticle().get(
                    frame.getListeArticle().getSelectedIndex());
            this.clearInput();
        });
        frame.getBtnAnnuler().addActionListener(e->{
            if(this.derniereSelection != null) {

            }
        });
    }

    private void doSearch() {
        List<ArticleEntity> articles;
        if(formulaire.getRequestParam().size() == 1) {
            String key = formulaire.getRequestParam().get(0);
            if(key.equals("titre")) {
                int articleTrouve = 0;
                ArticleEntity article = articleDao.getArticleByTitre(formulaire.getTitre());
                if(article != null) {
                    articleTrouve = 1;
                }

                updateResultatRecherche(articleTrouve);
                frame.updateArticleModel(article);
            } else if(key.equals("auteur")) {
                if(formulaire.getAuteur().getType() == AuteurType.JOURNALISTE) {
                    articles = articleDao.getArticleByAuteur((AuteurEntity) formulaire.getAuteur());
                    frame.getTextAreaResultat().setText(String.format(HTML_CONTAINER,
                                                                      "Résultat(s) trouvé: " + articles.size()));
                    frame.updateArticleModel(articles);

                } else if(formulaire.getAuteur().getType() == AuteurType.CHRONIQUEUR) {
                    List<ChroniqueEntity> chroniques = chroniqueDao.getArticleByAuteur(
                            (AuteurEntity) formulaire.getAuteur());
                    updateResultatRecherche(chroniques.size());
                    frame.updateArticleModel(chroniques);
                }

            } else if(key.equals("categorie")) {
                articles = articleDao.getArticleByCategorie(formulaire.getCategorie());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);

            } else if(key.equals("dateMiseAJour")) {
                articles = articleDao.getArticlesByDateMiseAJour(formulaire.getDateMiseAjour());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);

            } else if(key.equals("datePublication")) {
                articles = articleDao.getArticlesByDatePublication(formulaire.getDatePublication());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
            }
        }
        if(formulaire.getRequestParam().size() < 1) {
            for(ArticleEntity articleEntity : articleDao.getArticles()) {
                frame.getModelListeArticle().addElement(articleEntity);
            }
        }

    }

    private void updateResultatRecherche(int i) {
        frame.getTextAreaResultat().setText(String.format(HTML_CONTAINER, "Résultat(s) trouvé: " + i));
    }

    private void registerInput(JTextComponent component) {
        inputComponents.add(component);
    }

    private void ajouterArticle() {
        ArticleEntity article = new ArticleEntity();
        article.setTitreArt(frame.getTextFieldTitre().getText());
        article.setTexteArt(frame.getTextAreaTexte().getText());
        article.setLeadArt(frame.getTextAreaLead().getText());
        article.setExergueArt(frame.getTextFieldExergues().getText());
        article.setLongitudeArt(Long.parseLong(frame.getTextFieldLongitude().getText()));
        article.setLatitudeArt(Long.parseLong(frame.getTextFieldLatitude().getText()));
        articleDao.addArticles(article);
//        article.setNo(frame.getTextFieldNoArticle().getText());
    }

    private void clearInput() {
        frame.getTextFieldTitre().setText(null);
        frame.getTextAreaTexte().setText(null);
        frame.getTextFieldNoArticle().setText(null);
        frame.getTextAreaLead().setText(null);
        frame.getTextFieldExergues().setText(null);
        frame.getTextFieldLongitude().setText(null);
        frame.getTextFieldLatitude().setText(null);
        frame.getTextAreaResultat().setText(null);
        frame.getLabelPhotoIcon().setText(null);
        modelComboBoxAuteur.removeAllElements();
        modelComboBoxAuteurSearch.removeAllElements();
        modelComboBoxAuteurSearch.removeAllElements();
        modelComboBoxCategorie.removeAllElements();
    }

}
