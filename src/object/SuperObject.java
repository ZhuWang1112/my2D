package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {

        // copy from TileManager
        int screenX = gp.player.screenX - (gp.player.worldX - worldX);
        int screenY = gp.player.screenY - (gp.player.worldY - worldY);

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
