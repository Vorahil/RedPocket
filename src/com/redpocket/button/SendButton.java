package com.redpocket.button;

import com.redpocket.UI.SendButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SendButton extends JButton {
    public SendButton(JTextField field, JPanel panel) {
        super("Send");
        setPreferredSize(new Dimension(100, 45));
        setUI(new SendButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(e -> {
            String string = field.getText().trim();
            if (!string.isEmpty()) {
                message(string, panel);
            }
            field.setText("");
        });
    }

    private void message(String message, JPanel panel) {
        JPanel bubble = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                g2.setColor(new Color(173, 216, 230)); // 浅蓝色
                g2.fillRoundRect(5, 5, width - 10, height - 10, 15, 15);
            }
        };

        bubble.setLayout(new BorderLayout());
        bubble.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bubble.add(messageLabel, BorderLayout.CENTER);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
        wrapper.add(bubble);
        panel.add(wrapper);
        panel.revalidate();
        panel.repaint();

    }
}
