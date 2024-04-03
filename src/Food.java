import java.awt.Color;
import java.awt.Graphics2D;

public class Food extends Entity{

    int foodValue;
    GameEngine gameEngine;


    public Food(GameEngine gameEngine, int baseFoodValue){
        this.gameEngine = gameEngine;
        foodValue = baseFoodValue+randomVariability(5);
        setDefaultValues();


    }
    
    public void setDefaultValues(){
        speed = 0;
        direction = "down";
        sizeX = 3;
        sizeY = 3;
        x = (int) (gameEngine.screenWidth*Math.random())-sizeX;
        y = (int) (gameEngine.screenHeight*Math.random())-sizeY;
        defaultSpeed = 2;
        setBounds();
        clamp();
        name = "food";            
        
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.draw(hitbox);
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



}
