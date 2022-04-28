package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // the player position on the world map
    public int worldX, worldY;
    public int speed;
    // describe an Image
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

}
