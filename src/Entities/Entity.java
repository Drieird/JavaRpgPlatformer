package Entities;

import java.awt.image.BufferedImage;

public class Entity {
    
    public int x, y, wlkspd, fallspd, jumpCount;
    
    public BufferedImage idle1, idle2, walk1, walk2;
    
    public String movementDirection, movementStatus;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int walkSpriteCounter = 0;
    public int walkSpriteNum = 1;
}