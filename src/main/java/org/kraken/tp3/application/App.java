package org.kraken.tp3.application;

import com.sun.codemodel.JOp;
import org.kraken.tp3.dao.*;
import org.kraken.tp3.entity.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anonhyme on 12/4/2016.
 */

public class App {

    private enum Mode {UPDATE, RECHERCHE, AJOUT, NAVIGATION, DEFAULT}

    private static final String HTML_CONTAINER = "<html>%s</html>";
    private static final String HTML_LIST_CONTAINER = "<ul>%s</ul>";
    private static final String HTML_RESULTAT_LISTE_TEMPLATE = "<li></li>";
    private ArrayList<JTextComponent> inputComponents = new ArrayList<>();
    private MainView frame;
    private ChroniqueurDao chroniqueurDao = new ChroniqueurDao();
    private JournalisteDao journalisteDao = new JournalisteDao();
    private CategorieArticleDao categorieArticleDao = new CategorieArticleDao();
    private ChroniqueDao chroniqueDao = new ChroniqueDao();
    private ArticleDao articleDao = new ArticleDao();
    private List<JournalisteEntity> journalistes;
    private List<ChroniqueurEntity> chroniqueurs;

    private ReponseFormulaire formulaire;
    private DefaultComboBoxModel modelComboBoxAuteurSearch;
    private DefaultComboBoxModel modelComboBoxAuteur;
    private DefaultComboBoxModel modelComboBoxCategorieSearch;
    private DefaultComboBoxModel modelComboBoxCategorie;

    private ArticleEntity derniereSelection;
    private Mode currentMode = Mode.DEFAULT;

    public App() {
        this.frame = new MainView();
        this.frame.setTitle(getMode());
        this.refreshDataModel();

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

    public DefaultComboBoxModel getModelComboBoxAuteurSearch() {
        return this.modelComboBoxAuteurSearch;
    }

    public DefaultComboBoxModel getModelComboBoxCategorieSearch() {
        return this.modelComboBoxCategorieSearch;
    }

    private void setMode(Mode mode) {
        this.currentMode = mode;
        frame.setTitle(getMode());
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
        }

        frame.getComboboxAuteur().setModel(modelComboBoxAuteur);
        frame.getComboBoxCategorie().setModel(modelComboBoxCategorie);
        frame.getComboboxAuteur().setSelectedIndex(-1);
        frame.getComboBoxCategorie().setSelectedIndex(-1);
    }

    public void setFormulaire(ReponseFormulaire formulaire) {
        this.formulaire = formulaire;
    }

    private void setButtonAction() {
        //---------BOUTTON RECHERCHER---------
        frame.getBtnRechercher().addActionListener(e -> this.actionRechercher());
        //---------BOUTTON PURGER---------
        frame.getBtnPurger().addActionListener(e -> this.purgerAction());

        //---------BOUTTON AJOUTER---------
        frame.getBtnAjouter().addActionListener(e -> this.actionAjouter());

        //---------BOUTTON ENREGISTRER---------
        frame.getBtnEnregistrer().addActionListener(e -> actionEnregistrer());

        //---------BOUTTON ANNULER---------
        frame.getBtnAnnuler().addActionListener(e -> this.actionAnnuler());

        frame.getLabelPhotoIcon().addMouseListener(pathSelection());

    }

    private void actionRechercher() {
        this.setMode(Mode.RECHERCHE);

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
                default:
                    this.searchWithUnknownParam();
                    break;
            }
            this.setMode(Mode.UPDATE);
        }
    }

    private void actionAjouter() {
        this.setMode(Mode.AJOUT);
        if(frame.getListeArticle().getSelectedIndex() != -1) {
            SelectionHistory.getInstance().push(frame.getModelListeArticle(),
                                                frame.getListeArticle().getSelectedIndex());
        }

        this.clearInput();
        this.refreshDataModel();
    }

    private void actionEnregistrer() {
        switch(currentMode) {
            case AJOUT:
                this.ajouterArticle();
                break;
            case RECHERCHE:
                break;
            case UPDATE:
                this.updateArticle();
                break;
        }
    }

    private void actionAnnuler() {
        if(SelectionHistory.getInstance().getLastList() != null) {
            Object[] o = SelectionHistory.getInstance().getLastList();
            for(Object article : o) {
                frame.getModelListeArticle().addElement((ArticleEntity) article);
            }
            frame.getListeArticle().setSelectedIndex(SelectionHistory.getInstance().getLastSelectedArticle());
            frame.refreshSelectionForm();
            this.setMode(Mode.NAVIGATION);
        }
    }

    private void updateArticle() {
        ArticleEntity article = (ArticleEntity) frame.getModelListeArticle()
                                                     .getElementAt(frame.getListeArticle().getSelectedIndex());
        articleDao.updateArticle(article);
        frame.getTextAreaResultat().setText("L'article " + article.getTitreArt() + " à bien été modifiÉ");
    }

    private void searchWithNoParam() {
        for(ArticleEntity articleEntity : articleDao.findAll()) {
            frame.getModelListeArticle().addElement(articleEntity);
            frame.getListeArticle().setSelectedIndex(0);
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
                refreshResultatRecherche(articleTrouve);
                frame.updateArticleModel(article);
                break;
            case AUTEUR:
                switch(formulaire.getAuteur().getType()) {
                    case CHRONIQUEUR:
                        List<ChroniqueEntity> chroniques = chroniqueDao.findArticleByAuteur(
                                (AuteurEntity) formulaire.getAuteur());
                        refreshResultatRecherche(chroniques.size());
                        frame.updateArticleModel(chroniques);
                        break;
                    case JOURNALISTE:
                        articles = articleDao.findArticlesByAuteur((AuteurEntity) formulaire.getAuteur());
                        refreshResultatRecherche(articles.hashCode());
                        frame.updateArticleModel(articles);
                        break;

                }
                break;
            case CATEGORIE:
                articles = articleDao.findArticleByCategorie(formulaire.getCategorie());
                refreshResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case TEXTE:
                articles = articleDao.findArticleByTexte(formulaire.getTexte());
                refreshResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case DATE_MISE_A_JOUR:
                articles = articleDao.findArticlesByDateMiseAJour(formulaire.getDateMiseAjour());
                refreshResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            case DATE_PUBLICATION:
                articles = articleDao.findArticlesByDatePublication(formulaire.getDatePublication());
                refreshResultatRecherche(articles.size());
                frame.updateArticleModel(articles);
                break;
            default:

        }
    }

    private void searchWithUnknownParam() {
        List<ArticleEntity> articles;
        articles = articleDao.findArticleByForm(formulaire);
        refreshResultatRecherche(articles.size());
        frame.updateArticleModel(articles);
    }

    private void refreshResultatRecherche(int i) {
        frame.getTextAreaResultat().setText(String.format(HTML_CONTAINER, "Résultat(s) trouvé: " + i));
    }


    private String getMode() {
        switch(currentMode) {
            case AJOUT:
                return "Ajout";
            case RECHERCHE:
                return "Recherche";
            case UPDATE:
                return "Mise à jour";
            case NAVIGATION:
                return "Navigation";
            case DEFAULT:
                return "Default";
            default:
                return "<span style=\"color:red\">Aucun mode trouver</span>";
        }
    }

    private void ajouterArticle() {
        ArticleEntity article = new ArticleEntity();
        article.setAuteur((JournalisteEntity) frame.getComboboxAuteur().getSelectedItem());
        article.setCategorieArticle((CategorieArticleEntity) frame.getComboBoxCategorie().getSelectedItem());
        article.setTitreArt(frame.getTextFieldTitre().getText());
        article.setTexteArt(frame.getTextAreaTexte().getText());
        article.setLeadArt(frame.getTextAreaLead().getText());
        article.setExergueArt(frame.getTextFieldExergues().getText());
        article.setPhotoArt(frame.getPhotoPath());
        article.setLongitudeArt(Float.parseFloat(frame.getTextFieldLongitude().getText()));
        article.setLatitudeArt(Float.parseFloat(frame.getTextFieldLatitude().getText()));
        try {
            articleDao.addArticles(article);
        } catch (Exception e) {
            frame.getTextAreaResultat().setText("Erreur: " + e.getStackTrace());
        }
        frame.getTextAreaResultat().setText("L'article " + article.getTitreArt() + " à bien été ajouté");
        ArticleEntity ae = articleDao.findArticleByTitre(article.getTitreArt());
        frame.getModelListeArticle().removeAllElements();
        frame.getModelListeArticle().addElement(ae);
        frame.getListeArticle().setSelectedValue(ae, true);
    }


    private void clearInput() {
        frame.getModelListeArticle().removeAllElements();
        frame.getTextFieldTitre().setText(null);
        frame.getTextAreaTexte().setText(null);
        frame.getTextFieldNoArticle().setText(null);
        frame.getTextAreaLead().setText(null);
        frame.getTextFieldExergues().setText(null);
        frame.getTextFieldLongitude().setText(null);
        frame.getTextFieldLatitude().setText(null);
        frame.getTextAreaResultat().setText(null);
        frame.getLabelPhotoIcon().setText("Cliquer pour ajouter une photo");
        frame.getComboboxAuteur().removeAllItems();
        frame.getComboBoxCategorie().removeAllItems();
    }

    private void purgerAction() {
        String inputPuger = JOptionPane.showInputDialog(
                "Supprimer les articles plus ancien que(Format: 2016-01-30 00:00:00): ");
        try {
            articleDao.purgerArticles(Timestamp.valueOf(inputPuger));
        } catch (Exception e) {
            frame.getTextAreaResultat().setText("Erreur: " + e.getStackTrace());
        }
    }

    private MouseAdapter pathSelection() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String pathPhoto = JOptionPane.showInputDialog("Nom du fichier [ " + frame.getPhotoPath() + " ]: ");
                if(frame.getPhotoPath().isEmpty()) {
                    pathPhoto = JOptionPane.showInputDialog("Nom du fichier: ");
                }
                try {
                    frame.setPhoto(pathPhoto);
                } catch (Exception ex) {
                }

            }
        };
    }


}
