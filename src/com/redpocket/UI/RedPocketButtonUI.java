package com.redpocket.UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RedPocketButtonUI extends BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        JButton button = (JButton) c;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (button.getModel().isPressed()) {
            g2d.setColor(Color.RED.darker());
        } else {
            g2d.setColor(Color.RED);
        }

        int width = button.getWidth();
        int height = button.getHeight();

        int arc = 15;

        g2d.fillRoundRect(0, 0, width, height, arc, arc);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(button.getText());
        int textHeight = metrics.getHeight();
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + metrics.getAscent();
        g2d.drawString(button.getText(), x, y);
    }
}
