
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class GreenAnt extends Ant{
   


    public GreenAnt(GameEngine gameEngine){
        super(gameEngine);
        
        setDefaultValues();
        getSpriteImage();
    }
    public void setDefaultValues(){        
        speed = 1;
        direction = "down";
        sizeX = 16;
        sizeY = 16;
        x = (int) (gameEngine.screenWidth*Math.random())-sizeX;
        y = (int) (gameEngine.screenHeight*Math.random())-sizeY;
        defaultSpeed = 1;
        name = "greenAnt";
        setBounds();
        spriteDirectionChangeFrequency = 31;
        spriteUpdateFrequency = 20;
    }

    public void getSpriteImage(){
        try {
            up1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUp.png"));
            up2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUp2.png"));
            down1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDown.png"));
            down2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDown2.png"));
            left1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntLeft.png"));
            left2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntLeft2.png"));
            right1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntRight.png"));
            right2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntRight2.png"));
            
            upRight1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUpRight.png"));
            upRight2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUpRight2.png"));
            downLeft1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDownLeft.png"));
            downLeft2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDownLeft2.png"));
            upLeft1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUpLeft.png"));
            upLeft2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntUpLeft2.png"));
            downRight1 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDownRight.png"));
            downRight2 = (BufferedImage) ImageIO.read(new File(System.getProperty("user.dir") + "/images/GreenAnt/AntDownRight2.png"));

        } catch (IOException e) {            
            e.printStackTrace();
        }
    }

    public void drawHitbox(Graphics2D g2){     
        g2.setColor(Color.green);
        g2.draw(hitbox);

    }

}