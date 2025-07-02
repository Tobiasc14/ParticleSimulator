
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Ant {

   
    KeyHandler keyHandler;


    public Player(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine);
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
        spriteUpdateFrequency = 30;
        name = "player";
    }

    public void updateState(){

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){    
            checkCollisionsThenMove();  
            updateDirection();             
        }        
        clamp();
        hitbox.setBounds(x, y, sizeX, sizeY);        

    }
    
    public void updateDirection(){
        if (keyHandler.upPressed == true){
            if(keyHandler.leftPressed == true){
                direction = "upLeft";
            }
            else if (keyHandler.rightPressed == true){
                direction = "upRight";
            }
            else {
                direction = "up";
            }
        }
        else if (keyHandler.downPressed == true){
            if(keyHandler.leftPressed == true){
                direction = "downLeft";
            }
            else if (keyHandler.rightPressed == true){
                direction = "downRight";
            }
            else {
                direction = "down";
            }
        }
        else if (keyHandler.rightPressed == true){
            direction = "right";
        }
        else if (keyHandler.leftPressed == true){
            direction = "left";
            
        }
    }

    public void getPlayerImage(){
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
        g2.setColor(Color.cyan);
        g2.draw(hitbox);
    } 
    
}
