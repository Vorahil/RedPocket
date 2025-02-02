package com.redpocket.button;

import com.redpocket.UI.PresentButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PresentButton extends JButton {
    double money;
    int number;
    public PresentButton(JFrame frame,JPanel panel,JDialog dialog,JTextField moneyField,JTextField numberField) {
        super("Send");
        setFont(new Font("Arial", Font.BOLD, 20));
        setPreferredSize(new Dimension(250, 60));
        setUI(new PresentButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(e -> {
            if (!moneyField.getText().isEmpty() && !numberField.getText().isEmpty()) {
                money = BigDecimal.valueOf(Double.parseDouble(moneyField.getText())).setScale(2, RoundingMode.HALF_UP).doubleValue();
                number = Integer.parseInt(numberField.getText());
            }
                sendRedPocket(panel,money,number,frame);
                dialog.dispose();
        });
    }

    private void sendRedPocket(JPanel panel,double money,int number,JFrame frame) {
        RedPocketButton redPocket = new RedPocketButton(money,number,frame);

        redPocket.setLayout(new BorderLayout());
        redPocket.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
        wrapper.add(redPocket);
        panel.add(wrapper);
        panel.revalidate();
        panel.repaint();
    }
}
