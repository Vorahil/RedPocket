package com.redpocket.button;

import com.redpocket.UI.SendButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SendButton extends JButton {
    /**
     * 发送聊天信息按钮
     * @param field 主界面
     * @param panel 聊天框面板
     */
    public SendButton(JTextField field, JPanel panel) {
        super("Send");
        setPreferredSize(new Dimension(100, 45));
        setUI(new SendButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(_ -> {//发送聊天信息到文本框具体操作
            String string = field.getText().trim();
            if (!string.isEmpty()) {
                message(string, panel);
            }
            field.setText("");
        });
    }

    /**
     * 发送聊天信息到文本框的具体操作
     * @param message 发送的信息
     * @param panel 聊天框面板
     */
    private void message(String message, JPanel panel) {
        JPanel bubble = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                g2.setColor(new Color(173, 216, 230));
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
