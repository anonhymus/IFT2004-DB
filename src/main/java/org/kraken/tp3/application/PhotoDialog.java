package org.kraken.tp3.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * tp3
 * 12/13/2016.
 *
 * @author Anonhyme
 */
public class PhotoDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel labelCheminPhoto = new JLabel("Chemin de la photo: ");
    private JTextField textFieldCheminPhot = new JTextField();

    public PhotoDialog(Frame owner, App appContainer) {
        super(owner);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(owner);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(contentPanel, BorderLayout.CENTER);
        this.contentPanel.setLayout(null);


        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnOk = new JButton("Ok");
        JButton btnCancel = new JButton("Cancel");

    }
}
