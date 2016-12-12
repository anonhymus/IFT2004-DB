package org.anonhyme.tp3.application;

import org.anonhyme.tp3.entity.AuteurEntity;
import org.anonhyme.tp3.entity.CategorieArticleEntity;
import org.anonhyme.tp3.entity.ChroniqueurEntity;
import org.anonhyme.tp3.entity.JournalisteEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/7/2016.
 */
public class SearchDialog extends JDialog {
    private static final int WIDTH_FORM = 160;
    private static final int HEIGHT_FORM = 20;

    private static final int WIDTH_LABEL = 100;

    private static final int X_COLUMN_1 = 10;

    private static final int X_COLUMN_2 = X_COLUMN_1 + WIDTH_LABEL + 10;
    private static final int X_COLUMN_3 = X_COLUMN_2 + WIDTH_FORM + 10;

    private static final int WIDTH_WINDOW = 381;
    private static final int HEIGHT_WINDOW = 300;


    private final JPanel contentPanel = new JPanel();
    private List<ChroniqueurEntity> chroniqueurs;
    private List<JournalisteEntity> journalistes;

    private JComboBox<AuteurEntity> auteurCombobox = new JComboBox<>();
    private JComboBox<CategorieArticleEntity> categorieCombobox;

    private JLabel lblDateMiseAjour = new JLabel("Date mise à jour");
    private JTextField textFieldDateMiseAJour = new JTextField();

    private JLabel lblDatePublication = new JLabel("Date publication");
    private JTextField textFieldDatePublication = new JTextField();

    private JLabel lblDateFormat = new JLabel("Format date: 2015-12-30 12:01:01");
    private static final String HTML_LABEL_COLOR_TEMPLATE = "<html><span style=\"color:%s\";>%s</span></html>";

    private JTextField textFieldTitre = new JTextField();
    private JTextArea textAreaText = new JTextArea();
    private Boolean wasCancel = true;

    ReponseFormulaire formulaire;

    public SearchDialog(Frame owner, App appContainer) {
        super(owner);
        this.setBounds(100, 100, WIDTH_WINDOW, HEIGHT_WINDOW);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(owner);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(null);

        JLabel lblTitre = new JLabel("Titre");
        lblTitre.setBounds(X_COLUMN_1, 10, WIDTH_LABEL, 14);
        contentPanel.add(lblTitre);

        JLabel lblTexte = new JLabel("Texte");
        lblTexte.setBounds(X_COLUMN_1, 60, WIDTH_LABEL, 14);
        contentPanel.add(lblTexte);

        JLabel lblCatgorie = new JLabel("Cat\u00E9gorie");
        lblCatgorie.setBounds(X_COLUMN_1, 109, WIDTH_LABEL, 14);
        contentPanel.add(lblCatgorie);


        lblDatePublication.setBounds(X_COLUMN_1, 134, WIDTH_LABEL, 14);
        contentPanel.add(lblDatePublication);


        lblDateMiseAjour.setBounds(X_COLUMN_1, 159, WIDTH_LABEL, 14);
        contentPanel.add(lblDateMiseAjour);


        lblDateFormat.setBounds(X_COLUMN_1, 179, WIDTH_FORM + 100, HEIGHT_FORM + 20);
        contentPanel.add(lblDateFormat);

        JLabel lblAuteur = new JLabel("Auteur");
        lblAuteur.setBounds(X_COLUMN_1, 36, WIDTH_LABEL, 14);
        contentPanel.add(lblAuteur);

        textFieldDateMiseAJour = new JTextField();
        textFieldDateMiseAJour.setBounds(X_COLUMN_2, 156, WIDTH_FORM, HEIGHT_FORM);
        textFieldDateMiseAJour.setColumns(10);

        contentPanel.add(textFieldDateMiseAJour);

        textFieldDatePublication = new JTextField();
        textFieldDatePublication.setBounds(X_COLUMN_2, 131, WIDTH_FORM, HEIGHT_FORM);
        contentPanel.add(textFieldDatePublication);
        textFieldDatePublication.setColumns(10);

        textFieldTitre = new JTextField();
        textFieldTitre.setBounds(X_COLUMN_2, 8, WIDTH_FORM, HEIGHT_FORM);
        contentPanel.add(textFieldTitre);
        textFieldTitre.setColumns(10);

        auteurCombobox = new JComboBox(appContainer.getModelComboBoxAuteurSearch());
        auteurCombobox.setBounds(X_COLUMN_2, 33, WIDTH_FORM, HEIGHT_FORM);
        auteurCombobox.setSelectedIndex(-1);
        categorieCombobox = new JComboBox(appContainer.getModelComboBoxCategorieSearch());
        categorieCombobox.setBounds(X_COLUMN_2, 106, WIDTH_FORM, HEIGHT_FORM);
        categorieCombobox.setSelectedIndex(-1);
        contentPanel.add(auteurCombobox);
        contentPanel.add(categorieCombobox);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(X_COLUMN_2, 56, 130, 39);
        contentPanel.add(textArea);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> {
            boolean isDatePublicationValide = this.textFieldDatePublication.getText().length() == 19 ||
                                              this.textFieldDatePublication.getText().isEmpty();

            boolean isDateMiseAJour = this.textFieldDateMiseAJour.getText().length() == 19 ||
                                      this.textFieldDateMiseAJour.getText().isEmpty();

            if(!isDateMiseAJour) {
                lblDateFormat.setText(String.format(HTML_LABEL_COLOR_TEMPLATE, "red",
                                                    "* Un champ date doit être vide </br> " +
                                                    "ou du format: 2015-12-30 12:01:01"));
                lblDateMiseAjour.setText(String.format(HTML_LABEL_COLOR_TEMPLATE, "red", "Date mise à jour"));
            } else if(!isDatePublicationValide) {
                lblDateFormat.setText(String.format(HTML_LABEL_COLOR_TEMPLATE, "red",
                                                    "* Un champ date doit être vide </br> " +
                                                    "ou du format: 2015-12-30 12:01:01"));
                lblDatePublication.setText(String.format(HTML_LABEL_COLOR_TEMPLATE, "red", "Date publication"));
            } else {
                this.wasCancel = false;
                this.formulaire = new ReponseFormulaire();
                formulaire.setTitre(textFieldTitre.getText());
                formulaire.setAuteur((AuteurEntity) auteurCombobox.getSelectedItem());
                if(!textAreaText.getText().isEmpty()) {
                    formulaire.setTexte(textAreaText.getText());
                }
                if(categorieCombobox.getSelectedItem() != null) {
                    formulaire.setCategorie((CategorieArticleEntity) categorieCombobox.getSelectedItem());
                }
                if(!textFieldDateMiseAJour.getText().isEmpty()) {
                    formulaire.setDateMiseAjour(this.textFieldDateMiseAJour.getText());
                }
                if(!textFieldDatePublication.getText().isEmpty()) {
                    formulaire.setDatePublication(textFieldDatePublication.getText());
                }
                appContainer.setFormulaire(this.formulaire);
                dispose();
            }
        });
        buttonPane.add(btnRechercher);
        getRootPane().setDefaultButton(btnRechercher);


        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> {
            formulaire = null;
            this.wasCancel = true;
            dispose();

        });
        buttonPane.add(btnAnnuler);
        this.add(contentPanel);


    }

    public boolean isCancel() {
        return wasCancel;
    }
}
