package com.redpocket.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.PriorityQueue;

public class ResultDialog extends JDialog {
    /**
     * 显示抢红包结果的界面
     * @param frame 主界面
     * @param title 标题
     * @param thisTurnResult 此次抢得的红包
     * @param redPocketTotalRank 红包排名
     */
    public ResultDialog(JFrame frame, String title, double thisTurnResult, PriorityQueue<Double> redPocketTotalRank) {
        super(frame, title, true);
        setSize(400, 650);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                frame.requestFocus();
            }
        });
        setLocationRelativeTo(frame);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();//上半部分给出此次所抢金额
        topPanel.setBackground(Color.RED);
        String resultString = "You get $" + thisTurnResult + " ";
        JLabel resultLabel = new JLabel(resultString, JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 30)); // 设置字体大小
        resultLabel.setForeground(Color.WHITE);
        topPanel.add(resultLabel);
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();//下半部分给出排名
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        int rankRow = redPocketTotalRank.size();
        PriorityQueue<Double>outPut = new PriorityQueue<>(Collections.reverseOrder());
        outPut.addAll(redPocketTotalRank);
        for (int i = 0; i < rankRow; i++) {
            double rankValue = outPut.poll();
            JLabel label = new JLabel((i + 1) + "." + "                " + "$" + rankValue);
            bottomPanel.add(label);
        }
        panel.add(bottomPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();//退出按钮面板
        JButton closeButton = new JButton("Exit");
        closeButton.addActionListener(_ -> {
            dispose();
            frame.requestFocus();
        });
        closeButton.setBackground(Color.BLUE);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
