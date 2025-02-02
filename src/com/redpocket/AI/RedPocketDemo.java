package com.redpocket.AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RedPocketDemo extends JFrame {
    private JButton redPocketButton;
    private JLabel amountLabel;
    private int amount; // 存储红包金额，确保每次打开是同一个金额

    public RedPocketDemo() {
        setTitle("WeChat Red Pocket Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 生成固定金额（假设 1~100 随机金额）
        amount = new Random().nextInt(100) + 1;

        // 初始红包按钮
        redPocketButton = new JButton("Open Red Pocket");
        redPocketButton.setPreferredSize(new Dimension(150, 50));
        add(redPocketButton);

        // 显示金额的 Label
        amountLabel = new JLabel("");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(amountLabel);

        // 按钮点击事件
        redPocketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击后禁用按钮
                redPocketButton.setEnabled(false);
                redPocketButton.setText("Opening...");

                // 使用 Swing Timer 模拟延迟效果
                Timer timer = new Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        amountLabel.setText("You got: ¥" + amount);
                        redPocketButton.setText("Opened!");
                    }
                });

                timer.setRepeats(false);
                timer.start();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RedPocketDemo::new);
    }
}
