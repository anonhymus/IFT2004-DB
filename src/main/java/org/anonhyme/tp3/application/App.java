package org.anonhyme.tp3.application;

import lombok.Getter;
import org.anonhyme.tp3.dao.*;
import org.anonhyme.tp3.entity.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

//        frame.getComboboxAuteur().setModel(modelComboBoxAuteur);
//        frame.getComboBoxCategorie().setModel(modelComboBoxCategorie);
//        frame.getComboboxAuteur().setSelectedIndex(-1);
//        frame.getComboBoxCategorie().setSelectedIndex(-1);
        this.refreshDataModel();


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

        modelComboBoxAuteur = new DefaultComboBoxModel(journalistes.toArray());
        modelComboBoxAuteurSearch = new DefaultComboBoxModel(journalistes.toArray());

        for(ChroniqueurEntity chroniqueur : chroniqueurs) {
            modelComboBoxAuteurSearch.addElement(chroniqueur);
//            modelComboBoxAuteur.addElement(chroniqueur);
        }
        frame.getComboboxAuteur().removeAllItems();
        frame.getComboBoxCategorie().removeAllItems();
        frame.getComboboxAuteur().setModel(modelComboBoxAuteur);
        frame.getComboBoxCategorie().setModel(modelComboBoxCategorie);
        frame.getComboboxAuteur().setSelectedIndex(-1);
        frame.getComboBoxCategorie().setSelectedIndex(-1);
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
                switch(formulaire.getRequestParamCount()) {
                    case 0:
                        this.searchWithNoParam();
                        break;
                    case 1:
                        this.searchWithOneParam();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
            }
        });
        frame.getBtnPurger().addActionListener(e -> this.clearInput());

        frame.getBtnAjouter().addActionListener(e -> {
            this.clearInput();
            this.refreshDataModel();
//            frame.getComboboxAuteur().setModel(modelComboBoxAuteur);
//            frame.getComboBoxCategorie().setModel(modelComboBoxCategorie);
//            this.derniereSelection = (ArticleEntity) frame.getModelListeArticle().get(
//                    frame.getListeArticle().getSelectedIndex());
//            this.clearInput();
        });
        frame.getBtnEnregistrer().addActionListener(e -> {
            this.ajouterArticle();

        });
        frame.getBtnAnnuler().addActionListener(e -> {
            if(this.derniereSelection != null) {

            }
        });
    }

    private void searchWithNoParam() {
        for(ArticleEntity articleEntity : articleDao.getArticles()) {
            frame.getModelListeArticle().addElement(articleEntity);
        }
    }

    private void searchWithOneParam() {
        List<ArticleEntity> articles;
        FormulaireInputType key = formulaire.getRequestParam().get(0);
        switch(key) {
            case TITRE:
                int articleTrouve = 0;
                ArticleEntity article = articleDao.findArticleByTitre(formulaire.getTitre());
                if(article != null) {
                    articleTrouve = 1;
                }
                updateResultatRecherche(articleTrouve);
                frame.updateArticleModel(article);
                break;
            case AUTEUR:
                switch(formulaire.getAuteur().getType()) {
                    case CHRONIQUEUR:
                        List<ChroniqueEntity> chroniques = chroniqueDao.findArticleByAuteur(
                                (AuteurEntity) formulaire.getAuteur());
                        updateResultatRecherche(chroniques.size());
                        frame.updateArticleModel(chroniques);
                        break;
                    case JOURNALISTE:
                        articles = articleDao.findArticlesByAuteur((AuteurEntity) formulaire.getAuteur());
                        updateResultatRecherche(articles.hashCode());
                        frame.updateArticleModel(articles);
                        break;

                }
                break;
            case CATEGORIE:
                articles = articleDao.findArticleByCategorie(formulaire.getCategorie());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case TEXTE:
                articles = articleDao.findArticleByTexte(formulaire.getTexte());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case DATE_MISE_A_JOUR:
                articles = articleDao.findArticlesByDateMiseAJour(formulaire.getDateMiseAjour());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case DATE_PUBLICATION:
                articles = articleDao.findArticlesByDatePublication(formulaire.getDatePublication());
                updateResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            default:

        }
    }

    private void updateResultatRecherche(int i) {
        frame.getTextAreaResultat().setText(String.format(HTML_CONTAINER, "Résultat(s) trouvé: " + i));
    }

    private void ajouterArticle() {
        ArticleEntity article = new ArticleEntity();
        article.setAuteur((JournalisteEntity) frame.getComboboxAuteur().getSelectedItem());
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
        frame.getListeArticle().removeAll();
        frame.getTextFieldTitre().setText(null);
        frame.getTextAreaTexte().setText(null);
        frame.getTextFieldNoArticle().setText(null);
        frame.getTextAreaLead().setText(null);
        frame.getTextFieldExergues().setText(null);
        frame.getTextFieldLongitude().setText(null);
        frame.getTextFieldLatitude().setText(null);
        frame.getTextAreaResultat().setText(null);
        frame.getLabelPhotoIcon().setText(null);
        frame.getComboboxAuteur().removeAllItems();
        frame.getComboBoxCategorie().removeAllItems();

//        modelComboBoxAuteur.removeAllElements();
//        modelComboBoxAuteurSearch.removeAllElements();
//        modelComboBoxAuteurSearch.removeAllElements();
//        modelComboBoxCategorie.removeAllElements();
    }

}
