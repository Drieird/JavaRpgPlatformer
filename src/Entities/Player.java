package Entities;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        playerDefaultValue();
        getPlayerImage();
    }
    
    public void playerDefaultValue(){
        x = 100;y = 300;movementStatus = "idle";movementDirection = "right";
        wlkspd = 5;
        fallspd = 5;
        jumpCount = 2;
    }
    
    public void getPlayerImage(){
        
        try{
            
            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/playerIdle1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/player/playerIdle2.png"));
            walk1 = ImageIO.read(getClass().getResourceAsStream("/player/playerWalk1.png"));
            walk2 = ImageIO.read(getClass().getResourceAsStream("/player/playerWalk2.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        
        if(keyH.shiftKeyPressed){
            if(wlkspd<10){
                wlkspd++;
                movementStatus = "sprint";
            }
        }else if(wlkspd>5){
                wlkspd--;
                movementStatus = "walk";
        }
        
        if(y<576){
            y += fallspd;
            movementStatus = "fall";
        }else{
            movementStatus = "idle";
        }
        
        if(keyH.leftKeyPressed || keyH.rightKeyPressed) {
            if(keyH.leftKeyPressed && keyH.rightKeyPressed) {
                x += (wlkspd-wlkspd);
                movementStatus = "idle";
            }
            else if(keyH.rightKeyPressed) {
                x += wlkspd;
                movementDirection = "right";
            }
            else if(keyH.leftKeyPressed) {
                x -= wlkspd;
                movementDirection = "left";
            }
        }
        
        if(keyH.jumpKeyPressed && y<=576){
            if(jumpCount>=1){
                for(int i=0;i<y-300;i++){
                    y -= fallspd;
                    jumpCount--;
                    movementStatus="jump";
                }
            }
        }
        
        if(jumpCount<1 && y==576){
            jumpCount=1;
        }
        
        //idle animation counter
        spriteCounter++;
        if(spriteCounter==60){
            if(spriteNum==1){
                spriteNum++;
            }
            else if(spriteNum==2){
                spriteNum--;
            }
            spriteCounter=0;
        }
        
        //walking animation counter
        walkSpriteCounter++;
        if(walkSpriteCounter==15){
            if(walkSpriteNum<4){
                walkSpriteNum++;
            }
            else if(walkSpriteNum==4){
                walkSpriteNum=1;
            }
            walkSpriteCounter=0;
        }
    }
    
    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        
        //moving animation
        switch(movementStatus){
            case "idle"->{
                switch(movementDirection){
                    case "right" -> {
                        if(spriteNum==1){
                            image = idle1;
                        }else if(spriteNum==2){
                            image = idle2;
                        }
                    }
                    case "left" -> {
                        if(spriteNum==1){
                            image = idle1;
                        }else if(spriteNum==2){
                            image = idle2;
                        }
                    }
                }
            }
            case "walk"->{
                switch (movementDirection) {
                    case "right" -> {
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                        break;
                    }
                    case "left"->{
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                    break;
                    }
                }
            }
            case "sprint"->{
                switch (movementDirection) {
                    case "right" -> {
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                        break;
                    }
                    case "left"->{
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                    break;
                    }
                }
            }
            case "fall" -> {
                switch (movementDirection) {
                    case "right" -> {
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                        break;
                    }
                    case "left"->{
                        switch (walkSpriteNum) {
                            case 1 -> image = walk1;
                            case 2 -> image = idle1;
                            case 3 -> image = walk2;
                            case 4 -> image = idle1;
                            default -> {
                            }
                        }
                    break;
                    }
                }
            }
        }
        g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
    }
}