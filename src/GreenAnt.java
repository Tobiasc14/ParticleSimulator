
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class GreenAnt extends Entity{
    GameEngine gameEngine;
    //KeyHandler keyHandler;


    public GreenAnt(GameEngine gameEngine){
        this.gameEngine=gameEngine;
        //this.keyHandler=keyHandler;
        
        setDefaultValues();
        getSpriteImage();
    }
    public void setDefaultValues(){
        x = 500;
        y = 200;
        speed = 1;
        direction = "down";
        sizeX = 16;
        sizeY = 16;
        defaultSpeed = 1;
        setBounds();
    }

    

    public void updateState(){
        
        pauseCounter++;
        if (pauseCounter > 50){
            randomPause(0.1);
            pauseCounter = 0;
        }
        if (speed != 0){
            if (this.direction.equals("upLeft")){
                x -= speed;
                y -= speed;
            }
            else if(this.direction.equals("upRight")){
                x += speed;
                y -= speed;
            }
            else if(this.direction.equals("downLeft")){
                x -= speed;
                y += speed;
            }
            else if(this.direction.equals("downRight")){
                x += speed;
                y += speed;
            }
            else if(this.direction.equals("up")){
                y -= speed;
            }
            else if(this.direction.equals("down")){
                y += speed;
            }
            else if(this.direction.equals("left")){
                x -= speed;
            }
            else if(this.direction.equals("right")){
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter>18){
                
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
            spriteCounter = 0;
            }
            directionChangeCounter++;
            if(directionChangeCounter>30){
                this.direction = pickDirection();
                directionChangeCounter=0;
            }   
        
                    //This updates the images associated with a sprite ever 12 iterations of update method
                
            
            //Clamps position to within bounds of screen
            clamp();
            hitbox.setBounds(x, y, sizeX, sizeY);
        }
        
        

    }

    public void getSpriteImage(){
        try {
            up1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUp.png"));
            up2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUp2.png"));
            down1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDown.png"));
            down2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDown2.png"));
            left1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntLeft.png"));
            left2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntLeft2.png"));
            right1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntRight.png"));
            right2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntRight2.png"));
            
            upRight1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUpRight.png"));
            upRight2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUpRight2.png"));
            downLeft1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDownLeft.png"));
            downLeft2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDownLeft2.png"));
            upLeft1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUpLeft.png"));
            upLeft2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntUpLeft2.png"));
            downRight1 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDownRight.png"));
            downRight2 = (BufferedImage) ImageIO.read(new File("images/GreenAnt/AntDownRight2.png"));

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
        
        g2.drawImage(image, x, y, sizeX,sizeY, null);
        g2.setColor(Color.green);
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

    public String pickDirection(){
        String direction = this.direction;
        String [] directionOptions = {"up", "upRight", "right", "downRight", "down", "downLeft", "left", "upLeft"};
        int lastDirectionIndex = Arrays.asList(directionOptions).lastIndexOf(direction);

        if (Math.random()>=0.5){
            lastDirectionIndex++;
        }
        else{
            lastDirectionIndex--;
        }
        if (lastDirectionIndex < 0){
            lastDirectionIndex = 7;
        }
        if (lastDirectionIndex >7){
            lastDirectionIndex = 0;
        }
        return directionOptions[lastDirectionIndex];


    }




}