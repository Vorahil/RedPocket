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

    /**
     * 发红包按钮
     * @param frame 主界面
     * @param panel 聊天框
     * @param dialog 发送红包的会话
     * @param moneyField 金额
     * @param numberField 红包数量
     */
    public PresentButton(JFrame frame,JPanel panel,JDialog dialog,JTextField moneyField,JTextField numberField) {
        super("Send");
        setFont(new Font("Arial", Font.BOLD, 20));
        setPreferredSize(new Dimension(250, 60));
        setUI(new PresentButtonUI());//发红包按钮的UI设置
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(_ -> {//点击发送按钮，将金额与红包数量转化为特定变量存储
            if (!moneyField.getText().isEmpty() && !numberField.getText().isEmpty()) {
                money = BigDecimal.valueOf(Double.parseDouble(moneyField.getText())).setScale(2, RoundingMode.HALF_UP).doubleValue();
                number = Integer.parseInt(numberField.getText());
            }
                sendRedPocket(panel,money,number,frame);
                dialog.dispose();
        });
    }

    /**
     * 发红包具体操作
     * @param panel 聊天框
     * @param money 金额
     * @param number 数量
     * @param frame 主界面
     */
    private void sendRedPocket(JPanel panel,double money,int number,JFrame frame) {
        RedPocketButton redPocket = new RedPocketButton(money,number,frame);//显示在文本框的按钮

        redPocket.setLayout(new BorderLayout());
        redPocket.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel wrapper = new JPanel();//让红包在聊天框显示
        wrapper.setLayout(new FlowLayout(FlowLayout.RIGHT));
        wrapper.add(redPocket);
        panel.add(wrapper);
        panel.revalidate();
        panel.repaint();
    }
}
