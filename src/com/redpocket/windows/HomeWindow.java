package com.redpocket.windows;

import com.redpocket.button.AddButton;
import com.redpocket.button.SendButton;
import com.redpocket.dialog.ExitDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 主界面，上面是标题，中间是聊天框，下面是功能键
 */
public class HomeWindow {
    public static void show() {
        int width = 400, height = 650;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(width, height);
        frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);//设置窗口大小并定位在显示器正中央

        frame.addWindowListener(new WindowAdapter() {//添加退出时提醒对话框
            public void windowClosing(WindowEvent e) {
                ExitDialog exitDialog = new ExitDialog(frame, "Exit", "Are you sure you want to exit the application?");
                exitDialog.setVisible(true);
            }
        });

        JLabel customLabel = new JLabel("Welcome to RedPocket");//设置标题区域
        customLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customLabel.setFont(new Font("Arial", Font.BOLD, 20));
        customLabel.setOpaque(true);
        customLabel.setBackground(Color.gray);
        frame.add(customLabel, BorderLayout.NORTH);

        JPanel chatPanel = new JPanel();//设置聊天框
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());//设置功能区域
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.lightGray);

        AddButton addButton = new AddButton(frame, chatPanel);//发红包功能键
        inputPanel.add(addButton, BorderLayout.WEST);

        JTextField inputField = new JTextField();//聊天输入框
        inputPanel.add(inputField, BorderLayout.CENTER);

        SendButton sendButton = new SendButton(inputField, chatPanel);//发送聊天输入键
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}