
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Hud {



public GameEngine gameEngine;
public Rectangle sliderFrame, restartButton, pauseButton, numParticles, sliderBody, sliderFrame2, sliderBody2,sliderFrame3, sliderBody3,sliderFrame4, sliderBody4,sliderFrame5, sliderBody5;
public ArrayList<Rectangle> hudButtons; 
public ArrayList<String> hudButtonText; 
public int sliderWidth, sliderHeight;

    public Hud(GameEngine gameEngine){
        gameEngine = this.gameEngine;
        sliderWidth = 100;
        sliderHeight = 16;

        hudButtons = new ArrayList<>();
        hudButtonText = new ArrayList<>();
        pauseButton = new Rectangle(10,10,80,16);
        restartButton = new Rectangle(100,10,80,16);
        sliderFrame = new Rectangle(190, 10, sliderWidth, sliderHeight);
        sliderBody = new Rectangle(190+5, 8, 10, 20);
        sliderFrame2 = new Rectangle(300, 10, sliderWidth, sliderHeight);
        sliderBody2 = new Rectangle(300+5, 8, 10, 20);
        sliderFrame3 = new Rectangle(410, 10, sliderWidth, sliderHeight);
        sliderBody3 = new Rectangle(410+5, 8, 10, 20);
        sliderFrame4 = new Rectangle(520, 10, sliderWidth, sliderHeight);
        sliderBody4 = new Rectangle(520+5, 8, 10, 20);
        sliderFrame5 = new Rectangle(630, 10, sliderWidth, sliderHeight);
        sliderBody5 = new Rectangle(630+5, 8, 10, 20);
        hudButtons.add(pauseButton);
        hudButtons.add(restartButton);
        hudButtons.add(sliderFrame);
        hudButtons.add(sliderFrame2);
        hudButtons.add(sliderFrame3);
        hudButtons.add(sliderFrame4);
        hudButtons.add(sliderFrame5);
        hudButtonText.add("Start/Stop");
        hudButtonText.add("Restart");
        hudButtonText.add("Starting Particles");
        hudButtonText.add("Starting Speed");
        hudButtonText.add("Starting Size");
        hudButtonText.add("Strength of G");
        hudButtonText.add("Drag coefficient");
        
    }

    public void updateState(){

    }
    public void draw(Graphics2D g){
        for (int i = 0;i<hudButtons.size(); i++){
            
            g.setColor(Color.lightGray);
            g.fillRect(hudButtons.get(i).x,hudButtons.get(i).y,hudButtons.get(i).width,hudButtons.get(i).height);
            g.setColor(Color.black);
            g.draw(hudButtons.get(i));
            g.drawString(hudButtonText.get(i), hudButtons.get(i).x+2,hudButtons.get(i).y+13);
        }
        g.setColor(Color.GRAY);
        g.fillRect(sliderBody.x, sliderBody.y, sliderBody.width, sliderBody.height);
        g.fillRect(sliderBody2.x, sliderBody2.y, sliderBody2.width, sliderBody2.height);
        g.fillRect(sliderBody3.x, sliderBody3.y, sliderBody3.width, sliderBody3.height);
        g.fillRect(sliderBody4.x, sliderBody4.y, sliderBody4.width, sliderBody4.height);
        g.fillRect(sliderBody5.x, sliderBody5.y, sliderBody5.width, sliderBody5.height);
        
        
        
    }
}
