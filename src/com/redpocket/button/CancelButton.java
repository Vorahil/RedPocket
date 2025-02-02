package com.redpocket.button;

import com.redpocket.UI.SystemButtonUI;

import javax.swing.*;

public class CancelButton extends JButton {
    public CancelButton() {
        super("Cancel");
        setUI(new SystemButtonUI());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
