
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class PhysicsAnt extends Ant{    
    
   
    KeyHandler keyHandler;
    
    
    public PhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine);
        this.keyHandler=keyHandler;
        
        setDefaultValues();
        getSpriteImage();
    }

    public void setDefaultValues(){
        x= 500;
        y = 10;
        direction = "left";
        ySpeed = 0;
        xSpeed = 0;
        speed = 2;
        sizeX = 12;
        sizeY = 12;
        defaultSpeed = 2;
        spriteUpdateFrequency = 30;
        name = "physicsAnt";
        setBounds();
    }
    public void updateState(){
        checkCollisionsThenMove();
        //updateDirection();
        clamp();
        hitbox.setBounds((int)x,(int) y, sizeX, sizeY);
    }
    public void checkCollisionsThenMove(){
        gameEngine.collisionChecker.checkCollisionAllEntities(this);
        move();
    }
    
    public void move(){
        if(speed !=0 & !hittingSomething){
            if (keyHandler.rightPressed == true){
                direction = "right";
                x += speed;
            }
            else if (keyHandler.leftPressed == true){
                direction = "left";
                x -= speed;
                
            }
           if ((keyHandler.upPressed == true | keyHandler.spacePressed == true) & y>=gameEngine.screenHeight-18){
                direction = "up";
                ySpeed = -12;
            }
            //This bit does the gravity physics
            physicsCounter++;
            if(physicsCounter > 1){
                if (y<(gameEngine.screenHeight-16)){  
                    ySpeed=ySpeed+1;  
                          
                    
                }
                else{
                    y=(int)(y-ySpeed);
                } 
                
                physicsCounter = 0;
            }
            y=(int)(y+ySpeed);  
            
        }
        
        
    }

    public void getSpriteImage(){
        try {
           up1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUp.png"));
            up2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUp2.png"));
            down1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDown.png"));
            down2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDown2.png"));
            left1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntLeft.png"));
            left2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntLeft2.png"));
            right1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntRight.png"));
            right2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntRight2.png"));
            
            upRight1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUpRight.png"));
            upRight2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUpRight2.png"));
            downLeft1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDownLeft.png"));
            downLeft2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDownLeft2.png"));
            upLeft1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUpLeft.png"));
            upLeft2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntUpLeft2.png"));
            downRight1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDownRight.png"));
            downRight2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/PlayerBlueAnt/AntDownRight2.png"));

        } catch (IOException e) {
           e.printStackTrace();
            
            
        }
    }

    public void drawHitbox(Graphics2D g2){
        g2.setColor(Color.blue);
        g2.draw(hitbox);
    }
        
    }

    
    

