package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // screen setting
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    // horizontally
    public final int maxScreenCol = 16;
    // vertically
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxScreenCol;
//    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    public CollisonChecker cChecker = new CollisonChecker(this);

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();

    Sound music = new Sound();
    Sound se = new Sound();

    public UI ui = new UI(this);

    // keep game running until stop
    Thread gameThread;

    public Player player = new Player(this, keyH);

    public SuperObject[] obj = new SuperObject[10];

    public AssetSetter aSetter = new AssetSetter(this);

//    // Set player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;


    // constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight ));
        this.setBackground(Color.black);
        // help drawing?
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        // focused on receive key input
        this.setFocusable(true);

    }
    // set objects key, balabala
    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {                 // thread will automatically call run

        double drawInterval = 1000000000.0 / FPS; // ≈ 0.0167 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
//            System.out.println("The game loop is running! ");

            // 1 update the information such as  character position
            // update();
            // 2 redraw the screen with the updated information
            // repaint();              // call the paintComponent
        }
    }

    public void update() {
//        long currentTime = System.nanoTime();
//        System.out.println(currentTime);
//        if (keyH.upPressed) {
//            playerY -= playerSpeed;
//        } else if (keyH.downPressed) {
//            playerY += playerSpeed;
//        } else if (keyH.leftPressed) {
//            playerX -= playerSpeed;
//        } else if (keyH.rightPressed) {
//            playerX += playerSpeed;
//        }
        player.update();
    }

    // build-in method in java
    // from JComponet - > JPanel
    // g 怎么知道gp 画出相对坐标??
    public void paintComponent(Graphics g) {
        // super -- > JPanel
        super.paintComponent(g);
        // Graphics2D extends the Graphics
        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.white);
//        // draw a rectangle as player
//        g2.fillRect(playerX,playerY,tileSize,tileSize);

        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++ ) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        // like a layer, so make sure tile before player
        player.draw(g2);

        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
