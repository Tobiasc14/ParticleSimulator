import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Ant extends Entity{


    GameEngine gameEngine;

    public Ant(GameEngine gameEngine){
        this.gameEngine = gameEngine;

    }
    

    public void setDefaultValues(){        
        speed = 2;
        direction = "down";
        sizeX = 16;
        sizeY = 16;
        x = 100;
        y = 100;
        defaultSpeed = 2;
        setBounds();
        name = "ant";
    }

    public void updateState(){
        takePause();        
        if(!isPaused){
            checkCollisionsThenMove();
            updateDirection();
        }
        
        clamp();
        hitbox.setBounds(x, y, sizeX, sizeY);
        
    }
    public void updateDirection(){
        directionChangeCounter++;
        if(directionChangeCounter>spriteDirectionChangeFrequency+randomVariability(15)){
            this.direction = pickDirection();
            directionChangeCounter=0;
        }
    }
    public void move(){
        
        if (speed != 0 & !hittingSomething){
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
            updateSprite();
        }
    }
    public void updateSprite(){
        spriteCounter++;
            if(spriteCounter>spriteUpdateFrequency+randomVariability(10)){
                
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
            spriteCounter = 0;
            }
    }
    public void checkCollisionsThenMove(){
        
        gameEngine.collisionChecker.checkAllCollisions(this);
        move();
    }
    public void takePause(){
        pauseCounter++;
        if (pauseCounter > 30+randomVariability(240)){
            randomPause(0.1);
            pauseCounter = 0;
        }
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
        g2.drawImage(image, x, y, sizeX, sizeY, null);
        //drawHitbox(g2);
        

    }
    public void drawHitbox(Graphics2D g2){
        g2.setColor(Color.white);
        g2.draw(hitbox);
    }



}
