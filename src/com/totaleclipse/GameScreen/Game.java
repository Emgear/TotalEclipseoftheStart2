package com.totaleclipse.GameScreen;

import com.totaleclipse.enemy.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    // properties for the entire game
    // create a window for the game
    JFrame gameWindow;  // JFrame is also a topLevel Container, the container is similar to block level elements in HTML
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 30); // setting font properties for the game title
    Container container;

    // properties for the HomeScreen
    JPanel gameTitlePanel, gameStartPanel, gameExitPanel; // JPanel is a lowLevel Container
    JLabel gameTitleLabel; // JLevel is also a LowLevel Container
    JButton gameStartButton, gameExitButton;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 70); // setting font properties for the game title

    // properties for the gamePlayScreen
    JPanel gameTextPanel, directionsPanel, userInputPanel;
    static JTextArea gameTextArea; // has to be static in order to be used by DisplayScreen
    JTextArea userInputArea; // where the story/main content of our game will be held
    JButton eastButton, westButton, northButton, southButton;
    JTextField userInputText;
    JButton inputSubmitButton;

    // action handler for the game (Clicks)
//    GameActionHandler gameActionHandler1 = new GameActionHandler(); // doesn't work, issues with separating it as external file
    GameActionHandler2 gameActionHandler2 = new GameActionHandler2(); // listener for start game button
    EndGameAction endGameAction = new EndGameAction(); // listener for endgame button

    public Game() {
        setHomeScreen();
//        setGameScreen(); - we use it in the event listener instead
        // make sure the client can actually see the screen - for now I am leaving it at the end because if it occurs in the start it doesn't display any of my panels
        gameWindow.setVisible(true);
    }

    public void setHomeScreen() {
        // initialize the JFrame
        gameWindow = new JFrame();
        // disable the default layout, and it will be replaced with our custom layout later
        gameWindow.setLayout(null);
        // set size of the gameWindow
        gameWindow.setSize(1000, 600);
        // closes window properly when user hits the X button on top right screen
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set Background Color of the game
        gameWindow.getContentPane().setBackground(Color.darkGray);
        // set window to constant size
        gameWindow.setResizable(false);
        // initialize the Container to hold the game
        container = gameWindow.getContentPane();

        // background for the game title
        // panel for the title
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(150, 150, 700, 250);
        gameTitlePanel.setBackground(Color.darkGray);
        // what the game title should display
        gameTitleLabel = new JLabel("Total Eclipse of the Start");
        // color of the title text
        gameTitleLabel.setForeground(Color.GREEN);
        // set font for title
        gameTitleLabel.setFont(titleFont);
        // add label to the panel
        gameTitlePanel.add(gameTitleLabel);
        // add the panel to the main container
        container.add(gameTitlePanel);

        // gameStartButtonCreation
        // panel for the button
        gameStartPanel = new JPanel();
        gameStartPanel.setBounds(400, 400, 200, 50);
        gameStartPanel.setBackground(Color.blue);
        // the button itself
        gameStartButton = new JButton("START GAME");
        gameStartButton.setBackground(Color.darkGray);
        gameStartButton.setForeground(Color.GREEN);
        gameStartButton.setFont(gameFont);
        // implementing the action listener, similar to AddEventListener in JavaScript with onClick parameter
        gameStartButton.addActionListener(gameActionHandler2);

        gameStartPanel.add(gameStartButton);
        container.add(gameStartPanel);


        // gameExitButtonCreation
        // panel for the button
        gameExitPanel = new JPanel();
        gameExitPanel.setBounds(400, 450, 200, 50);
        gameExitPanel.setBackground(Color.red);
        // the button itself
        gameExitButton = new JButton("EXIT GAME");
        gameExitButton.setBackground(Color.DARK_GRAY);
        gameExitButton.setForeground(Color.GREEN);
        gameExitButton.setFont(gameFont);
        // action listener for end game
        gameExitButton.addActionListener(endGameAction);

        gameExitPanel.add(gameExitButton);
        container.add(gameExitPanel);
    }

    public void closeHomeScreen(){
        // home screen panels set to disabled, otherwise it will still appear alongside the GameScreen
        gameTitlePanel.setVisible(false);
        gameStartPanel.setVisible(false);
        gameExitPanel.setVisible(false);
    }

    public void setGameScreen() {
        // panel for the game text
        gameTextPanel = new JPanel();
        gameTextPanel.setBounds(10, 10, 600, 500);
        gameTextPanel.setBackground(Color.LIGHT_GRAY);

        gameTextArea = new JTextArea("Total Eclipse game play. I like to move it, move it. You like to move it, move it!! I like to move it, move it. You like to move it, move it!!");
        gameTextArea.setBounds(12, 12, 550, 500);
        gameTextArea.setBackground(Color.black);
        gameTextArea.setForeground(Color.white);
        gameTextArea.setFont(gameFont);
        gameTextArea.setLineWrap(true);
//        gameTextArea.setEditable(false);

        gameTextPanel.add(gameTextArea);
        container.add(gameTextPanel);

        // panel for directions buttons in the game
        directionsPanel = new JPanel();
        directionsPanel.setBounds(630, 10, 300, 200);
        directionsPanel.setBackground(Color.ORANGE);

        // buttons for directions in the game
            // north
        northButton = new JButton("Go North");
        northButton.setBackground(Color.white);
        northButton.setForeground(Color.black);
        northButton.setFont(gameFont);
            // west
        westButton = new JButton("Go West");
        westButton.setBackground(Color.white);
        westButton.setForeground(Color.black);
        westButton.setFont(gameFont);
            // east
        eastButton = new JButton("Go East");
        eastButton.setBackground(Color.white);
        eastButton.setForeground(Color.black);
        eastButton.setFont(gameFont);
            //south
        southButton = new JButton("Go South");
        southButton.setBackground(Color.white);
        southButton.setForeground(Color.black);
        southButton.setFont(gameFont);

        directionsPanel.add(northButton);
        directionsPanel.add(westButton);
        directionsPanel.add(eastButton);
        directionsPanel.add(southButton);
        container.add(directionsPanel);

        // panel for user input/ game controls in the game
        userInputPanel = new JPanel();
        userInputPanel.setBounds(630, 420, 300, 100);
        userInputPanel.setBackground(Color.orange);
            // user input text field
        userInputText = new JTextField(20); // accepts upto 10 characters
//        userInputText.setFont(gameFont);
        userInputText.setBounds(632, 420, 290, 200);
            // user input sumbit button
        inputSubmitButton = new JButton("Run Command!");
        userInputPanel.add(userInputText);
        userInputPanel.add(inputSubmitButton);

//        // text Area For userInputs
//        userInputArea = new JTextArea("Take Input SomeHow SomeWay You can do it!");
//        userInputArea.setBounds(630, 300, 300, 200);
//        userInputArea.setBackground(Color.black);
//        userInputArea.setForeground(Color.white);
//        userInputArea.setFont(gameFont);
//        userInputArea.setLineWrap(true);
//
//        userInputPanel.add(userInputArea);
        container.add(userInputPanel);
    }

    // overload game text display
    public static void setGameText(String text){
        Game.gameTextArea.setText(text);
    }
    public static void setGameText(Enemy enemy){
        Game.gameTextArea.setText(String.valueOf(enemy));
    }
    public static void setGameText(String string, Integer integer){
        Game.gameTextArea.setText(string + integer);
    }
    public static void setGameText(Integer integer){
        Game.gameTextArea.setText(String.valueOf(integer));
    }
    // EventListeners
    // doesn't work as an external file, keep getting stackoverflow error?
    public class GameActionHandler2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            closeHomeScreen();
            setGameScreen();
        }
    }

    public class EndGameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            closeHomeScreen();
        }
    }
}
