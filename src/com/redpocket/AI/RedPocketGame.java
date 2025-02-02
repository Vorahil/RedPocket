package com.redpocket.AI;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class RedPocketGame extends JFrame {
    private JButton openButton;
    private List<Integer> redPackets;  // 存储红包金额
    private List<Integer> receivedAmounts;  // 存储已领取的金额

    public RedPocketGame() {
        setTitle("Red Pocket Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 初始化红包金额（10元拆成 2 份：[1,9]）
        redPackets = new ArrayList<>(Arrays.asList(1, 9));
        Collections.shuffle(redPackets); // 打乱顺序，模拟随机领取
        receivedAmounts = new ArrayList<>();

        // 创建“开红包”按钮
        openButton = new JButton("Open Red Pocket");
        openButton.setPreferredSize(new Dimension(150, 50));
        add(openButton);

        // 按钮点击事件
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!redPackets.isEmpty()) {
                    openButton.setEnabled(false);  // 禁用按钮，防止多次点击

                    // 使用 SwingWorker 来处理红包领取和弹窗显示
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            // 模拟领取红包操作
                            Thread.sleep(1000);  // 延时1秒

                            // 领取红包金额
                            int receivedAmount = redPackets.remove(0);
                            receivedAmounts.add(receivedAmount);  // 记录已领取金额

                            // 排行榜更新（按照金额降序排序）
                            Collections.sort(receivedAmounts, Collections.reverseOrder());

                            // 显示红包领取结果
                            SwingUtilities.invokeLater(() -> {
                                showResultDialog(receivedAmount);
                            });

                            return null;
                        }

                        @Override
                        protected void done() {
                            // 领取红包后更新按钮状态
                            if (redPackets.isEmpty()) {
                                openButton.setText("No Red Pocket Left");
                                openButton.setEnabled(false); // 红包领完禁用按钮
                            } else {
                                openButton.setText("Open Red Pocket");
                                openButton.setEnabled(true);  // 恢复按钮
                            }
                        }
                    }.execute();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 显示红包领取结果对话框
    private void showResultDialog(int receivedAmount) {
        JDialog dialog = new JDialog(this, "Red Pocket Result", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(0, 1));

        JLabel resultLabel = new JLabel("You got: ¥" + receivedAmount, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dialog.add(resultLabel);

        // 计算并显示排名
        int rank = receivedAmounts.indexOf(receivedAmount) + 1; // 获取当前金额的排名
        JLabel rankLabel = new JLabel("Rank: #" + rank, SwingConstants.CENTER);
        rankLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(rankLabel);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RedPocketGame::new);
    }
}

