package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    //Screen Settings
    final int originalTileSize = 64;
    final int scale = 1;
    
    public final int tileSize = originalTileSize * scale;//64x64
    final int maxScreenColumn= 20;
    final int maxScreenRow= 12;
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    
    //FPS
    int FPS = 60;
    
    //Playerdata
    int spd = 5;
    int x = 100;
    int y = 100;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS;// 1 frame every 16.666 nanoseconds or 0,016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frameCount = 0;
        
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            
            if(delta >=1) {
                update();
                repaint(); 
                delta--;
                frameCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS : "+ frameCount);
                frameCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        
        if(keyH.shiftKeyPressed){
            if(spd<10){
                spd++;
            }
        }
        else{
            if(spd>5){
                spd--;
            }
        }
        
        if(keyH.upKeyPressed || keyH.downKeyPressed) {
            
            if(keyH.upKeyPressed && keyH.downKeyPressed) {
                y += (spd-spd);
            }
            else if(keyH.downKeyPressed) {
                y += spd;
            }
            else if(keyH.upKeyPressed) {
                y -= spd;
            }
        }
        else if(keyH.leftKeyPressed || keyH.rightKeyPressed) {
            if(keyH.leftKeyPressed && keyH.rightKeyPressed) {
                x += (spd-spd);
            }
            else if(keyH.rightKeyPressed) {
                x += spd;
            }
            else if(keyH.leftKeyPressed) {
                x -= spd;
            }
        }
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.red);
        g2.fillRect(x, y, tileSize, tileSize);
    }
}