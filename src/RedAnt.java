
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RedAnt extends Ant{


    public RedAnt(GameEngine gameEngine){
        super(gameEngine);        
        setDefaultValues();
        getSpriteImage();
        
    }

    public void setDefaultValues(){        
        speed = 2;
        direction = "down";
        sizeX = 12;
        sizeY = 12;
        x = (int) (gameEngine.screenWidth*Math.random())-sizeX;
        y = (int) (gameEngine.screenHeight*Math.random())-sizeY;
        defaultSpeed = 2;
        setBounds();
    }

    public void getSpriteImage(){
        try {
            up1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUp.png"));
            up2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUp2.png"));
            down1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDown.png"));
            down2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDown2.png"));
            left1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntLeft.png"));
            left2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntLeft2.png"));
            right1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntRight.png"));
            right2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntRight2.png"));
            
            upRight1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUpRight.png"));
            upRight2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUpRight2.png"));
            downLeft1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDownLeft.png"));
            downLeft2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDownLeft2.png"));
            upLeft1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUpLeft.png"));
            upLeft2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntUpLeft2.png"));
            downRight1 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDownRight.png"));
            downRight2 = (BufferedImage) ImageIO.read(new File("images/RedAnt/AntDownRight2.png"));

        } catch (IOException e) {
          
            e.printStackTrace();
            
            
        }
    }

    public void drawHitbox(Graphics2D g2){       
        g2.setColor(Color.red);
        g2.draw(hitbox);
    }



}
