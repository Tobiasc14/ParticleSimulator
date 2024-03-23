import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y, speed, ySpeed, xSpeed, defaultSpeed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage upLeft1, upLeft2, downRight1, downRight2, downLeft1, downLeft2, upRight1, upRight2;
    public String direction;
    public int directionChangeCounter = 0;
    public int physicsCounter = 0;
    public int pauseCounter = 0;
    public int spriteCounter = 0;
    public int spriteNumber = 1;



    public void updateState(){

    }
    public void draw(Graphics2D g2){
        
    }
    public void randomPause(double frequency){
        if (Math.random() < frequency){
            speed = 0;
        }
        else{
            speed = defaultSpeed;
        }
    }



}
