package com.redpocket.button;

import com.redpocket.UI.RedPocketButtonUI;
import com.redpocket.dialog.RedPocketDialog;
import com.redpocket.dialog.ResultDialog;

import javax.swing.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class RedPocketButton extends JButton {
    double money;
    int number;
    PriorityQueue<Double> redPocketTotalRank;//从大到小排列红包，与排名有关系
    double[] moneyList;//随机放置红包，按数组的顺序抽取
    int index=0;

    public RedPocketButton(double money, int number,JFrame frame) {
        super("Good Luck");
        setSize(100, 40);
        this.money = money;
        this.number = number;
        this.redPocketTotalRank = new PriorityQueue<>(Collections.reverseOrder());
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
        moneyList[moneyList.length - 1] = money - sum;

        addActionListener(e -> {
            setEnabled(false);
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    if (index>moneyList.length-1) {
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
