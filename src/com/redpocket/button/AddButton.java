package com.redpocket.button;

import com.redpocket.UI.AddButtonUI;
import com.redpocket.dialog.RedPocketDialog;

import javax.swing.*;
import java.awt.*;

public class AddButton extends JButton {
    /**
     * 发红包键功能
     * @param frame 主界面
     * @param panel 所显示的面板
     */
    public AddButton(JFrame frame,JPanel panel) {
        super("+");
        setPreferredSize(new Dimension(45, 45));
        setUI(new AddButtonUI());//设置UI样式
        setOpaque(false); // 设置按钮不透明，确保背景颜色不会被填充，按钮背景透明
        setContentAreaFilled(false); // 设置按钮的内容区域不填充背景颜色，保持透明
        setFocusPainted(false); // 设置按钮失去焦点时不显示焦点框，通常用于去除按钮的默认焦点效果

        addActionListener(_ -> {//点击按钮时的功能
            showRedPocket(frame,panel);
        });
    }

    /**
     * 点击按钮时的功能
     * @param frame 主界面
     * @param panel 所应该显示的界面
     */
    private void showRedPocket(JFrame frame,JPanel panel) {
        RedPocketDialog redPocket = new RedPocketDialog(frame, "RedPocket", panel);//设置填写红包的会话
        redPocket.setVisible(true);
    }
}
