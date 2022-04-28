package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    // constructor
    public TileManager(GamePanel gp) {
        this.gp = gp;
        // kind of tiles
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load a number map --> mapTileNum
    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    //String  ---> Integer
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

//        int worldCol = (gp.player.worldX - gp.player.screenX) / gp.tileSize;
//        int worldRow = (gp.player.worldY - gp.player.screenY) / gp.tileSize;
////        int x = 0;
////        int y = 0;
//
//        while (worldRow < (gp.player.worldY + gp.player.screenY) / gp.tileSize) {
//
//            int tileNum = mapTileNum[worldCol][worldRow];
//
//            int worldX = worldCol * gp.tileSize;
//            int worldY = worldRow * gp.tileSize;
//            int screenX = gp.player.screenX - (gp.player.worldX - worldX);
//            int screenY = gp.player.screenY - (gp.player.worldY - worldY);
//
//            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            worldCol++;
////            x += gp.tileSize;
//
//            if (worldCol == (gp.player.worldX + gp.player.screenX) / gp.tileSize) {
//                worldCol = 0;
////                x = 0;
//                worldRow++;
////                y += gp.tileSize;
//            }
//        }

        int worldCol = 0;
        int worldRow = 0;
//        int x = 0;
//        int y = 0;

        while (worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = gp.player.screenX - (gp.player.worldX - worldX);
            int screenY = gp.player.screenY - (gp.player.worldY - worldY);

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
//            x += gp.tileSize;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
//                x = 0;
                worldRow++;
//                y += gp.tileSize;
            }
        }
//        g2.drawImage(tile[0].image, 0,0,gp.tileSize, gp.tileSize,null);
//        g2.drawImage(tile[1].image, 48,0,gp.tileSize, gp.tileSize,null);
//        g2.drawImage(tile[2].image, 96,0,gp.tileSize, gp.tileSize,null);
    }
}
