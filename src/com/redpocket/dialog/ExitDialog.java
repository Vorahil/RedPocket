package com.redpocket.dialog;

import com.redpocket.button.CancelButton;
import com.redpocket.button.OkButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitDialog extends JDialog {
    /**
     * 退出时的对话框
     * @param frame 主界面
     * @param title 此对话框标题
     * @param message 对话框显示的信息
     */
    public ExitDialog(JFrame frame, String title, String message) {
        super(frame, title, true);
        setLayout(new BorderLayout());//设置整体布局

        setBackground(Color.lightGray);
        JLabel label = new JLabel(message, SwingConstants.CENTER);//将显示的信息设置为对话框中央
        add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));//按钮面板
        OkButton okButton=new OkButton();//确认键
        okButton.addActionListener(_ -> System.exit(0));
        CancelButton cancelButton=new CancelButton();//取消键
        cancelButton.addActionListener(_ -> {
            dispose();
            frame.requestFocus();
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);//相当于取消键
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                frame.requestFocus();
            }
        });

        setSize(300, 180);
        setResizable(false);
        setLocationRelativeTo(frame);//基于主界面定位
    }
}

