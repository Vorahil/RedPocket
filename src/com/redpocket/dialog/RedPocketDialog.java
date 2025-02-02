package com.redpocket.dialog;

import com.redpocket.button.PresentButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RedPocketDialog extends JDialog {
    public RedPocketDialog(JFrame frame, String title, JPanel chatPanel) {
        super(frame, title, true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        row1.add(new JLabel("Money:"));
        JTextField moneyField = new JTextField(20);
        moneyField.setText("Please enter a number");
        moneyField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (moneyField.getText().equals("Please enter a number")) {
                    moneyField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (moneyField.getText().isEmpty()) {
                    moneyField.setText("Please enter a number");
                }
            }
        });
        row1.add(moneyField);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        row2.add(new JLabel("Number:"));
        JTextField numberField = new JTextField(20);
        numberField.setText("Please enter a number");
        numberField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (numberField.getText().equals("Please enter a number")) {
                    numberField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (numberField.getText().isEmpty()) {
                    numberField.setText("Please enter a number");
                }
            }
        });
        row2.add(numberField);

        totalPanel.add(row1);
        totalPanel.add(row2);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(totalPanel);

        add(Box.createVerticalStrut(20));
        add(centerPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // 按钮居中
        PresentButton presentButton = new PresentButton(frame,chatPanel, this, moneyField, numberField);
        buttonPanel.add(presentButton);

        add(Box.createVerticalStrut(10));
        add(buttonPanel);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                frame.requestFocus();
            }
        });

        setSize(400, 650);
        setResizable(false);
        setLocationRelativeTo(frame);
    }
}
