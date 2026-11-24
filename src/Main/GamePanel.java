package Main;

import Entities.Player;
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
    final int maxScreenColumn= 24;//1280 px
    final int maxScreenRow= 12;//768 px
    final int screenWidth = tileSize * maxScreenColumn;
    final int screenHeight = tileSize * maxScreenRow;
    
    //FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    
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
                System.out.println("movement status :"+ player.movementStatus);
                System.out.println("movement direction :"+ player.movementDirection);
                System.out.println("position : x "+ player.x +", y "+player.y);
                System.out.println(" ");
                frameCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        
        player.update();
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        player.draw(g2);
        
        g2.dispose();
    }
}