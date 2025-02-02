package com.redpocket.UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * 设置系统按钮的UI,圆角、白底、黑字
 */
public class SystemButtonUI extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        JButton button = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = button.getWidth();
        int height = button.getHeight();

        if (button.getModel().isPressed()) {
            g2d.setColor(new Color(200, 200, 200));
        } else if (button.getModel().isRollover()) {
            g2d.setColor(new Color(220, 220, 220));
        } else {
            g2d.setColor(Color.WHITE);
        }

        int arc = 10;
        g2d.fillRoundRect(1, 1, width - 2, height - 2, arc, arc);

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

