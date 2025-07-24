package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

        // Screen Settings
        final int originalTileSize = 16; // 16x16
        final int scale = 3;

        public final int tileSize = originalTileSize * scale; // 48x48 tile
        final int maxScreenCol = 16;
        final int maxScreenRow= 12;
        final int screenWidth = tileSize * maxScreenCol; // 768 pixels
        final int screenHeight = tileSize * maxScreenRow; // 576 pixels

        // FPS
        int FPS = 60;

        KeyHandler keyH = new KeyHandler(); // instantiating new key handler for directional use
        Thread gameThread;
        Player player = new Player(this,keyH);

        //Set players default position
        int playerX = 100;
        int playerY = 100;
        int playerSpeed = 4;


        public GamePanel() {
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);
        }

        public void startGameThread() {
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
//        public void run() {
//
//            double drawInterval = 1000000000/FPS; // 0.01666 seconds
//            double nextDrawTime = System.nanoTime() + drawInterval;
//
//            while(gameThread !=null) {
//
//                //long currentTime = System.nanoTime(); // long currentTime2 = System.currentTimeMillis();
//
//                //1 Update: update character position
//                update();
//
//                //2 Draw: draw screen with updated info
//                repaint();
//
//                try {
//                    double remainingTime = nextDrawTime - System.nanoTime();
//                    remainingTime = remainingTime/1000000; // converting nanoseconds to milliseconds
//
//                    if(remainingTime < 0) { // if update takes more time then draw interval then no time is left
//                        remainingTime =0;
//                    }
//
//                    Thread.sleep((long) remainingTime);
//
//                    nextDrawTime += drawInterval;
//
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
        public void run() {
            double drawInterval = 1000000000/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while(gameThread != null) {

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS:" + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

        public void update() {

            player.update();

        }
        public void paintComponent(Graphics g) { // Graphics is like your paint brush
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;

            player.draw(g2);

            g2.dispose();

        }
}
