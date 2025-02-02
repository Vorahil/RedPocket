package com.redpocket.button;

import com.redpocket.UI.RedPocketButtonUI;
import com.redpocket.dialog.ResultDialog;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class RedPocketButton extends JButton {
    double money;
    int number;
    PriorityQueue<Double> redPocketTotalRank;//从大到小排列红包，与排名有关系
    double[] moneyList;//随机放置红包，按数组的顺序抽取
    int index=0;//按照moneyList的顺序发红包

    /**
     * 抢红包按钮的功能
     * @param money 金额
     * @param number 数量
     * @param frame 主界面
     */
    public RedPocketButton(double money, int number,JFrame frame) {
        super("Good Luck");
        setSize(100, 40);
        this.money = money;
        this.number = number;
        this.redPocketTotalRank = new PriorityQueue<>(Collections.reverseOrder());//从大到小放入
        moneyList = new double[number];
        setUI(new RedPocketButtonUI());

        double sum = 0;
        int allInt = 99;
        for (int i = 0; i < moneyList.length - 1; i++) {
            int anInt = new Random().nextInt(allInt) + 1;
            moneyList[i] = anInt * money / 100;
            allInt -= anInt;
            sum += moneyList[i];
        }
        DecimalFormat df = new DecimalFormat("#.##");
        moneyList[moneyList.length - 1] = Double.parseDouble(df.format(money - sum));//确保总金额一定

        addActionListener(_ -> {//抢红包按钮的操作
            setEnabled(false);
            new SwingWorker<Void, Void>() {//不影响主线程
                @Override
                protected Void doInBackground() {
                    if (index>moneyList.length-1) {//如果红包抢完
                        new ResultDialog(frame,"Rank",0,redPocketTotalRank);
                    }
                    double thisTurnResult=moneyList[index];
                    if (thisTurnResult!=0){
                        redPocketTotalRank.add(thisTurnResult);
                    }
                    new ResultDialog(frame,"Rank",thisTurnResult,redPocketTotalRank);
                    return null;
                }

                @Override
                protected void done() {
                    index++;
                    setEnabled(true);
                }
            }.execute();
        });
    }
}
