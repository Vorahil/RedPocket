package com.redpocket.UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class AddButtonUI extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        JButton button = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (button.getModel().isPressed()) {
            g2d.setColor(Color.ORANGE.darker());  // 按下时的颜色
        } else {
            g2d.setColor(Color.ORANGE);  // 普通状态下的颜色
        }

        int width = button.getWidth();
        int height = button.getHeight();
        g2d.fillRect(0, 0, width, height);

        Font buttonFont = button.getFont();
        Font font = buttonFont.deriveFont(Font.BOLD, 16f);
        g2d.setFont(font);

        g2d.setColor(Color.BLACK);
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(button.getText());
        int textHeight = metrics.getHeight();
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + metrics.getAscent();
        g2d.drawString(button.getText(), x, y);

        super.paint(g, c);
    }
}
