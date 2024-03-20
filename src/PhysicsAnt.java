import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class PhysicsAnt extends Entity{    
    
    GameEngine gameEngine;
    KeyHandler keyHandler;
    
    
    public PhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        this.gameEngine=gameEngine;
        this.keyHandler=keyHandler;

        setDefaultValues();
        getSpriteImage();
    }

    public void setDefaultValues(){
        x= 500;
        y = 10;
        direction = "left";
        ySpeed = 0;
        speed = 2;
    }
    public void updateState(){

        if (keyHandler.rightPressed == true){
            direction = "right";
            x += speed;
        }
        else if (keyHandler.leftPressed == true){
            direction = "left";
            x -= speed;
            
        }
        //This bit does the gravity physics
        physicsCounter++;
        if(physicsCounter > 1){
            if (y<(gameEngine.screenHeight-16)){  
                ySpeed++;          
                y = y+ySpeed;
            } 
            physicsCounter = 0;
        }
        

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

        //Clamps position to within bounds of screen
        if (x < 0){
            x=0;
        }
        if (y < 0){
            y = 0;
        }
        if (x>gameEngine.screenWidth-16){
            x=gameEngine.screenWidth-16;
        }
        if (y>gameEngine.screenHeight-16){
            y = gameEngine.screenHeight-16;
            //System.out.println("Final ySpeed: " + ySpeed);
            ySpeed = 0;
           
        }

    }

    public void getSpriteImage(){
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
            // TODO Auto-generated catch block
            
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
        g2.drawImage(image, x, y, gameEngine.tileSize/2, gameEngine.tileSize/2, null);
    }

}
