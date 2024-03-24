
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

    GameEngine gameEngine;
    KeyHandler keyHandler;

    

    public Player(GameEngine gameEngine, KeyHandler keyHandler){
        this.gameEngine = gameEngine;
        this.keyHandler = keyHandler;
        
        setDefaultValues();
        getPlayerImage();



    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2;
        direction = "down";
        sizeX = 16;
        sizeY = 16;
        defaultSpeed = 2;
        setBounds();
    }

    public void updateState(){

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if (keyHandler.upPressed == true){
                if(keyHandler.leftPressed == true){
                    direction = "upLeft";
                    x -= speed;
                }
                else if (keyHandler.rightPressed == true){
                    direction = "upRight";
                    x += speed;
                }
                else {
                    direction = "up";
                }
                y -= speed;
            }
            else if (keyHandler.downPressed == true){
                if(keyHandler.leftPressed == true){
                    direction = "downLeft";
                    x -= speed;
                }
                else if (keyHandler.rightPressed == true){
                    direction = "downRight";
                    x += speed;
                }
                else {
                    direction = "down";
                }
                
                y += speed;
            }
            else if (keyHandler.rightPressed == true){
                direction = "right";
                x += speed;
            }
            else if (keyHandler.leftPressed == true){
                direction = "left";
                x -= speed;
                
            }
    
                //This updates the images associated with a sprite ever 10 iterations of update method
            spriteCounter++;
            if(spriteCounter>15){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
        //Clamps position to within bounds of screen
        clamp();
        hitbox.setBounds(x, y, sizeX, sizeY);
        

    }

    public void getPlayerImage(){
        try {
            up1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUp.png"));
            up2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUp2.png"));
            down1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDown.png"));
            down2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDown2.png"));
            left1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntLeft.png"));
            left2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntLeft2.png"));
            right1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntRight.png"));
            right2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntRight2.png"));
            
            upRight1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUpRight.png"));
            upRight2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUpRight2.png"));
            downLeft1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDownLeft.png"));
            downLeft2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDownLeft2.png"));
            upLeft1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUpLeft.png"));
            upLeft2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntUpLeft2.png"));
            downRight1 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDownRight.png"));
            downRight2 = (BufferedImage) ImageIO.read(new File("images/PlayerBlueAnt/AntDownRight2.png"));

        } catch (IOException e) {
            
            e.printStackTrace();
            
            
        }

    }


    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        switch(direction){
        case "up":
            if(spriteNumber == 1){
                image = up1;
            }
            if(spriteNumber == 2){
                image = up2;
            }
            break;            
        case "down":
            if(spriteNumber == 1){
                image = down1;
            }
            if(spriteNumber == 2){
                image = down2;
            }
            break;
        case "left":
            if(spriteNumber == 1){
                image = left1;
            }
            if(spriteNumber == 2){
                image = left2;
            }
            break;
        case "right":
            if(spriteNumber == 1){
                image = right1;
            }
            if(spriteNumber == 2){
                image = right2;
            }
            break;
        case "upLeft":
            if(spriteNumber == 1){
                image = upLeft1;
            }
            if(spriteNumber == 2){
                image = upLeft2;
            }
            
            break;            
        case "upRight":
            if(spriteNumber == 1){
                image = upRight1;
            }
            if(spriteNumber == 2){
                image = upRight2;
            }
            break;            
        case "downLeft":
            if(spriteNumber == 1){
                image = downLeft1;
            }
            if(spriteNumber == 2){
                image = downLeft2;
            }
            break;     
        case "downRight":
            if(spriteNumber == 1){
                image = downRight1;
            }
            if(spriteNumber == 2){
                image = downRight2;
            }
            break;         
        
        
            

        }
        //g2.setColor(Color.white);
        //g2.drawRect(x, y, gameEngine.tileSize, gameEngine.tileSize);
        g2.drawImage(image, x, y, sizeX, sizeY, null);
        g2.setColor(Color.cyan);
        g2.draw(hitbox);


       

    }
    public void clamp(){
        if (x < 0){
            x=0;
        }
        if (y < 0){
            y = 0;
        }
        if (x>gameEngine.screenWidth-sizeX){
            x=gameEngine.screenWidth-sizeX;
        }
        if (y>gameEngine.screenHeight-sizeY){
            y = gameEngine.screenHeight-sizeY;
        }
    }

}
