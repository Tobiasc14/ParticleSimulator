
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Hud {



public GameEngine gameEngine;
public Rectangle frame, restartButton, pauseButton, numParticles, massRange, speedRange;
public ArrayList<Rectangle> hudButtons; 

    public Hud(GameEngine gameEngine){
        gameEngine = this.gameEngine;
        hudButtons = new ArrayList<>();
        pauseButton = new Rectangle(10,10,80,16);
        hudButtons.add(pauseButton);
    }

    public void updateState(){

    }
    public void draw(Graphics2D g){
        for (int i = 0;i<hudButtons.size(); i++){
            
            g.setColor(Color.lightGray);
            g.fillRect(hudButtons.get(i).x,hudButtons.get(i).y,hudButtons.get(i).width,hudButtons.get(i).height);
            g.setColor(Color.darkGray);
            g.draw(hudButtons.get(i));
            g.drawString("Start/Stop", hudButtons.get(i).x+2,hudButtons.get(i).y+12);
        }
        
    }
}
