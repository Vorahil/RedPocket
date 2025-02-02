package com.redpocket.windows;

import com.redpocket.button.AddButton;
import com.redpocket.button.SendButton;
import com.redpocket.dialog.ExitDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeWindow {
    public static void show() {
        int width = 400, height = 650;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(width, height);
        frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ExitDialog exitDialog = new ExitDialog(frame, "Exit", "Are you sure you want to exit the application?");
                exitDialog.setVisible(true);
            }
        });

        JLabel customLabel = new JLabel("Welcome to RedPocket");
        customLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customLabel.setFont(new Font("Arial", Font.BOLD, 20));
        customLabel.setOpaque(true);
        customLabel.setBackground(Color.gray);
        frame.add(customLabel, BorderLayout.NORTH);

        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.lightGray);

        AddButton addButton=new AddButton(frame,chatPanel);
        inputPanel.add(addButton, BorderLayout.WEST);

        JTextField inputField = new JTextField();
        inputPanel.add(inputField,BorderLayout.CENTER);

        SendButton sendButton=new SendButton(inputField,chatPanel);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}