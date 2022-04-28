package main;

import object.OBJ_Key;


import java.awt.*;
import java.awt.image.BufferedImage;


public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage KeyImage;
    Boolean messageOn = false;
    int messageCount = 0;
    String message;
    public Boolean gameFinished = false;
    double playtime;

    public void shouMessage(String text) {
        message = text;
        messageOn = true;
    }

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        KeyImage = new OBJ_Key().image;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;
            text = "You found the treasure!";

            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenHeight / 2 - textLength / 4;
            y = gp.screenWidth / 2 - (gp.tileSize * 3);
            g2.drawString(text,x,y);

            g2.setColor(Color.BLUE);
            text = "Congratulations";

            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenHeight / 2 - textLength / 4;
            y = gp.screenWidth / 2 + (gp.tileSize * 3);
            g2.drawString(text,x,y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(KeyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("X " + gp.player.hasKey, 74, 65);

            playtime += (double) 1 / 60;
            g2.drawString("Time: " + playtime, gp.tileSize*11, 65);
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCount++;

                if (messageCount > 100) {
                    messageOn = false;
                    messageCount = 0;
                }
            }
        }
    }
}

