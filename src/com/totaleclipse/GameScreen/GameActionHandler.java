package com.totaleclipse.GameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionHandler extends Game implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        setGameScreen();
    }
}
