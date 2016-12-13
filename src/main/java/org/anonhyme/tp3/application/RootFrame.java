package org.anonhyme.tp3.application;

import lombok.Getter;
import org.anonhyme.tp3.entity.ArticleBase;
import org.anonhyme.tp3.entity.ArticleEntity;
import org.anonhyme.tp3.entity.ArticleType;
import org.anonhyme.tp3.entity.AuteurEntity;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * org.anonhyme.tp3.application.RootFrame.java
 *
 * @author Anonhyme
 *         12/7/2016.
 */
@Getter
public class RootFrame extends JFrame {
    private static final String TITLE = "Db Explorer";
    // --- UI COMPONENTS ------
    private JLabel labelAuteur;
    private JComboBox<AuteurEntity> comboboxAuteur;
    private JList<String> listeArticle;
    private JTextField textFieldNoArticle;
    private JTextField textFieldTitre;
    private JTextField textFieldExergues;
    private JTextField textFieldLongitude;
    private JTextField textFieldLatitude;
    private JTextArea textAreaLead;
    private JTextArea textAreaTexte;
    private JLabel textAreaResultat;
    private JComboBox<Object> comboBoxCategorie;
    private JLabel labelPhotoIcon;

    //    --- Boutton ---
    private JButton btnRechercher;
    private JButton btnAjouter;
    private JButton btnQuitter;
    private JButton btnEnregistrer;
    private JButton btnAnnuler;
    private JButton btnPurger;

    //    --- DATA ---
    private DefaultListModel modelListeArticle;
    private DefaultComboBoxModel modelComboBoxAuteur = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelComboBoxCategorie = new DefaultComboBoxModel();


    public RootFrame() throws HeadlessException {
        super();
        this.setBounds(100, 100, 938, 653);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

        this.init();
    }

    public void updateArticleModel(ArticleEntity article) {
        this.getModelListeArticle().clear();
        this.getModelListeArticle().addElement(article);
        this.getListeArticle().setSelectedIndex(0);
    }

    public void updateArticleModel(List<?> articles) {
        this.getModelListeArticle().clear();
        for(Object article : articles) {
            getModelListeArticle().addElement(article);
            getListeArticle().setSelectedIndex(0);
        }
        this.getListeArticle().setSelectedIndex(0);
    }

    private void init() {
        setListeAricles();
        initNoArticle();
        initAuteur();
        initTitre();
        initExergues();
        initLead();
        initTexte();
        initCategorie();
        initResultat();
        initPhoto();
        initCoord();
        initButton();
    }

    private void setListeAricles() {
        JLabel labelListeArticles = new JLabel("Liste des articles");
        labelListeArticles.setBounds(10, 11, 200, 14);

        JScrollPane scrollpaneListeArticles = new JScrollPane();
        scrollpaneListeArticles.setBounds(10, 25, 592, 115);

        this.getContentPane().add(labelListeArticles);
        this.getContentPane().add(scrollpaneListeArticles);

        modelListeArticle = new DefaultListModel();
        listeArticle = new JList<>(modelListeArticle);
        listeArticle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        listeArticle.setBackground(new Color(255, 255, 255));
        listeArticle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listeArticle.addListSelectionListener(e -> this.refreshSelectionForm());

        scrollpaneListeArticles.setViewportView(listeArticle);
    }

    private void initNoArticle() {
        JLabel labelNoArticle = new JLabel("No article");
        labelNoArticle.setBounds(10, 151, 80, 14);
        this.getContentPane().add(labelNoArticle);

        textFieldNoArticle = new JTextField();
        textFieldNoArticle.setBounds(80 + 20, 148, 86 - 20, 20);
        textFieldNoArticle.setEditable(false);
        this.getContentPane().add(textFieldNoArticle);
        textFieldNoArticle.setColumns(10);
    }

    private void initAuteur() {
        labelAuteur = new JLabel("Auteur");
        labelAuteur.setBounds(214, 151, 46, 14);
        this.getContentPane().add(labelAuteur);

        comboboxAuteur = new JComboBox<>();
        comboboxAuteur.setBounds(258, 148, 344, 20);
        this.getContentPane().add(comboboxAuteur);
    }

    private void initTitre() {
        JLabel labelTitre = new JLabel("Titre");
        labelTitre.setBounds(10, 176, 46, 14);
        this.getContentPane().add(labelTitre);

        textFieldTitre = new JTextField();
        textFieldTitre.setBounds(80 + 20, 173, 522 - 20, 20);
        this.getContentPane().add(textFieldTitre);
        textFieldTitre.setColumns(10);
    }

    private void initExergues() {
        JLabel labelExergues = new JLabel("Exergues");
        labelExergues.setBounds(10, 201, 80, 14);
        this.getContentPane().add(labelExergues);

        textFieldExergues = new JTextField();
        textFieldExergues.setBounds(80 + 20, 198, 522 - 20, 20);
        this.getContentPane().add(textFieldExergues);
        textFieldExergues.setColumns(10);
    }

    private void initLead() {
        JLabel labelLead = new JLabel("Lead");
        labelLead.setBounds(10, 226, 46, 14);
        this.getContentPane().add(labelLead);

        textAreaLead = new JTextArea();
        textAreaLead.setBounds(80 + 20, 229, 522 - 20, 40);
        this.getContentPane().add(textAreaLead);
    }

    private void initTexte() {
        JLabel labelTexte = new JLabel("Texte");
        labelTexte.setBounds(10, 278, 46, 14);
        this.getContentPane().add(labelTexte);

        JScrollPane scrollpaneTexte = new JScrollPane();
        scrollpaneTexte.setBounds(80 + 20, 280, 522 - 20, 62);
        this.getContentPane().add(scrollpaneTexte);

        textAreaTexte = new JTextArea();

        scrollpaneTexte.setViewportView(textAreaTexte);
        textAreaTexte.setLineWrap(true);
        textAreaTexte.setWrapStyleWord(true);
    }

    private void initCategorie() {
        JLabel labelCategorie = new JLabel("Categorie");
        labelCategorie.setBounds(10, 356, 64, 14);
        this.getContentPane().add(labelCategorie);

        comboBoxCategorie = new JComboBox();
        comboBoxCategorie.setBounds(80 + 20, 353, 130, 20);
        this.getContentPane().add(comboBoxCategorie);
    }

    private void initResultat() {
        JLabel labelResultat = new JLabel("R\u00E9sultat");
        labelResultat.setBounds(10, 384, 100, 14);
        this.getContentPane().add(labelResultat);

        textAreaResultat = new JLabel();
        textAreaResultat.setBounds(10, 409, 592, 160);
        textAreaResultat.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.getContentPane().add(textAreaResultat);
    }

    private void initPhoto() {
        JLabel labelPhoto = new JLabel("Photo");
        labelPhoto.setBounds(658, 11, 46, 14);
        this.getContentPane().add(labelPhoto);

        labelPhotoIcon = new JLabel("New label");
        labelPhotoIcon.setBackground(Color.GRAY);
        labelPhotoIcon.setBounds(658, 32, 254, 237);
        this.getContentPane().add(labelPhotoIcon);
    }

    private void initCoord() {
        JLabel labelLongitude = new JLabel("Longitude");
        labelLongitude.setBounds(658, 315, 64, 14);
        this.getContentPane().add(labelLongitude);

        JLabel labelLatitude = new JLabel("Latitude");
        labelLatitude.setBounds(658, 340, 46, 14);
        this.getContentPane().add(labelLatitude);

        textFieldLongitude = new JTextField();
        textFieldLongitude.setBounds(716, 312, 196, 20);
        this.getContentPane().add(textFieldLongitude);
        textFieldLongitude.setColumns(10);

        textFieldLatitude = new JTextField();
        textFieldLatitude.setColumns(10);
        textFieldLatitude.setBounds(716, 337, 196, 20);
        this.getContentPane().add(textFieldLatitude);
    }

    private void initButton() {
        btnRechercher = new JButton("Recherche");
        btnRechercher.setBounds(10, 580, 120, 23);
        this.getContentPane().add(btnRechercher);

        btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(140, 580, 77, 23);
        this.getContentPane().add(btnAjouter);

        btnPurger = new JButton("Purger");
        btnPurger.setBounds(295, 580, 99, 23);
        this.getContentPane().add(btnPurger);

        btnAnnuler = new JButton("Annuler");
        btnAnnuler.setBounds(404, 580, 89, 23);
        this.getContentPane().add(btnAnnuler);

        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBounds(503, 580, 99, 23);
        this.getContentPane().add(btnEnregistrer);


        btnQuitter = new JButton("Quitter");
        btnQuitter.setBounds(823, 580, 89, 23);
        this.getContentPane().add(btnQuitter);

    }

    public void refreshSelectionForm() {
        if(listeArticle.getSelectedIndex() != -1) {
            if(((ArticleBase) modelListeArticle.get(
                    listeArticle.getSelectedIndex())).getType() == ArticleType.ARTICLE) {
                ArticleEntity article = (ArticleEntity) modelListeArticle.get(listeArticle.getSelectedIndex());
                textFieldNoArticle.setText("" + article.getNoArticle());
                comboboxAuteur.setSelectedItem(article.getAuteur());
                textFieldTitre.setText(article.getTitreArt());
                textFieldExergues.setText(article.getExergueArt());
                textAreaLead.setText(article.getLeadArt());
                textAreaTexte.setText(article.getTexteArt());
                comboBoxCategorie.setSelectedItem(article.getCategorieArticle());
                textFieldLongitude.setText(String.valueOf(article.getLongitudeArt()));
                textFieldLatitude.setText(String.valueOf(article.getLatitudeArt()));
            }
        }

    }

    @Override
    public void setTitle(String mode) {
        super.setTitle(String.format("Mode: %s [%s]", TITLE, mode));

    }


}
