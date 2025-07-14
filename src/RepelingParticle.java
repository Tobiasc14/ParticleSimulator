import java.awt.Color;
import java.awt.Graphics2D;

public class RepelingParticle extends Particle{




    public RepelingParticle(GameEngine gameEngine){
        super(gameEngine);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.MAGENTA);
        //g2.fill(hitbox);        
        g2.drawOval((int)x-(sizeX/2), (int)y-(sizeY/2), sizeX, sizeY);
        g2.fillOval((int)x-(sizeX/2), (int)y-(sizeY/2), sizeX, sizeY);
        
    }
    public void updateState(){
        y=(y-ySpeed);
        x=(x-xSpeed);
        //kineticEnergy = 0.5 * mass * (xSpeed * xSpeed + ySpeed * ySpeed);
        gravBindingEnergy = 0.6 * gameEngine.G * mass * mass / sizeX / 2;
        clamp();
    }







}
