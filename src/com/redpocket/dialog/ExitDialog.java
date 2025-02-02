package com.redpocket.dialog;

import com.redpocket.button.CancelButton;
import com.redpocket.button.OkButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitDialog extends JDialog {
    public ExitDialog(JFrame frame, String title, String message) {
        super(frame, title, true);
        setLayout(new BorderLayout());

        setBackground(Color.lightGray);
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        OkButton okButton=new OkButton();
        okButton.addActionListener(_ -> System.exit(0));

        CancelButton cancelButton=new CancelButton();
        cancelButton.addActionListener(_ -> {
            dispose();
            frame.requestFocus();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                frame.requestFocus();
            }
        });

        setSize(300, 180);
        setResizable(false);
        setLocationRelativeTo(frame);
    }
}

