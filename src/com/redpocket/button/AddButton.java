package com.redpocket.button;

import com.redpocket.UI.AddButtonUI;
import com.redpocket.dialog.RedPocketDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton extends JButton {
    public AddButton(JFrame frame,JPanel panel) {
        super("+");
        setPreferredSize(new Dimension(45, 45));
        setUI(new AddButtonUI());
        setOpaque(false); // 设置按钮不透明，确保背景颜色不会被填充，按钮背景透明
        setContentAreaFilled(false); // 设置按钮的内容区域不填充背景颜色，保持透明
        setFocusPainted(false); // 设置按钮失去焦点时不显示焦点框，通常用于去除按钮的默认焦点效果

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRedPocket(frame,panel);
            }
        });
    }

    private void showRedPocket(JFrame frame,JPanel panel) {
        RedPocketDialog redPocket = new RedPocketDialog(frame, "RedPocket", panel);
        redPocket.setVisible(true);
    }
}
