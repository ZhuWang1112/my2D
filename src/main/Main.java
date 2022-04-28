package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        // close the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // resize the window
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //display the window ?
        window.pack();

        //display at the center of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // set objects key,balabala
        gamePanel.setupGame();

        gamePanel.startGameThread();


    }
}
