package com.redpocket.button;

import com.redpocket.UI.SystemButtonUI;

import javax.swing.*;


public class OkButton extends JButton {
    public OkButton() {
        super("OK");
        setUI(new SystemButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
