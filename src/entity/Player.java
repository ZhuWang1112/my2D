package entity;

import main.GamePanel;
import main.KeyHandler;
import rotate.RotateImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
//        solidArea.x = 0;
//        solidArea.y = 0;
//        solidArea.width = gp.tileSize;
//        solidArea.height = gp.tileSize;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;



        setDefaultValue();
        getPlayerImage();
    }
    public void setDefaultValue() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 8;
        // Any direction if fine;
        direction = "down";
    }
    public  void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed | keyH.downPressed | keyH.leftPressed | keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
//            worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
//            worldY += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
//            worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
//            worldX += speed;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // if collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.ui.shouMessage("You got a key!");
                    gp.obj[i] = null;
                    System.out.println(hasKey);
                    break;
                case "Door":
                    gp.playSE(3);
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }

    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        draw a rectangle as player
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up2;
                } else {
                    image = up1;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down2;
                } else {
                    image = down1;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left2;
                } else {
                    image = left1;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right2;
                } else {
                    image = right1;
                }
                break;
        }


        //g2.rotate(Math.toRadians(45),image.getWidth() / 2, image.getHeight() / 2 + (image.getWidth() - image.getHeight()) / 2);

        g2.drawImage(RotateImage.Rotate(image,45), screenX, screenY, gp.tileSize, gp.tileSize, null);
//        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
    }

//    public BufferedImage rotateImage (BufferedImage image, int angle)
//    {
//        BufferedImage bufferedimage = (BufferedImage) image;//        int h = bufferedimage.getHeight();
//        int type = bufferedimage.getColorModel().getTransparency();
//        BufferedImage img;
//        Graphics2D graphics2d;
//        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics2d.rotate(Math.toRadians(angle), w / 2, h / 2 + (w - h) / 2);
//        graphics2d.drawImage(bufferedimage, 0, 0, null);
//        graphics2d.dispose();
//
//        return img;
//    }

}
