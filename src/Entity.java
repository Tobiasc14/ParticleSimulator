import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Entity {

    public int speed, defaultSpeed, sizeX, sizeY;
    public double angle, acceleration, xAcceleration, yAcceleration, mass, xSpeed, ySpeed, x, y, absKineticEnergy, gravBindingEnergy, relKineticEnergy;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage upLeft1, upLeft2, downRight1, downRight2, downLeft1, downLeft2, upRight1, upRight2;
    public String direction, name;
    public int entityListLoc; 
    public Ellipse2D ellipse = new Ellipse2D.Double(x, y, sizeX, sizeY);  
    //public Shape  = new Rectangle((int) x, (int) y, sizeX, sizeY);
    
    public boolean collision = true;
    public boolean isPaused = false;
    public boolean hittingSomething = false;
    public int directionChangeCounter = 0;
    public int physicsCounter = 0;
    public int pauseCounter = 0;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int spriteUpdateFrequency = 8;
    public int spriteDirectionChangeFrequency = 20;
    


    public void updateState(){

    }
    public void draw(Graphics2D g2){
        
    }
    public void randomPause(double frequency){
        if (Math.random() < frequency){
            speed = 0;
            isPaused = true;
        }
        else{
            speed = defaultSpeed;
            isPaused = false;
        }
    }
    public int randomVariability(int maxVariability){
        return (int)Math.random()*maxVariability;
    }

    public void setBounds(){
        ellipse.intersects((int)x,(int) y, sizeX, sizeY);
    }
    

    



}
