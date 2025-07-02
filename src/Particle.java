/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author tobias
 */
public class Particle extends Entity{

    GameEngine gameEngine;
    double angle;
    int mass;
    

    public Particle(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        setDefaultValues();
        
    }


    public void setDefaultValues(){
        speed = 0;        
        sizeX = 1;
        sizeY = 1;
        mass = 
        x = (int) (gameEngine.screenWidth*Math.random())-sizeX;
        y = (int) (gameEngine.screenHeight*Math.random())-sizeY;
        defaultSpeed = 7;
        angle = Math.random()*2*Math.PI;
        xSpeed = (int)(defaultSpeed*Math.cos(angle));
        ySpeed = (int)(defaultSpeed*Math.sin(angle));        
        setBounds();
        clamp();
        name = "particle";      
       
        
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.lightGray);
        g2.fill(hitbox);        
        g2.draw(hitbox);
        
    }
    public void updateState(){ 
        y=y+ySpeed;
        x=x+xSpeed;
        clamp();
        hitbox.setBounds(x, y, sizeX, sizeY);
        
    }

    

    public void clamp(){
        if (x < 0){
            x=x+gameEngine.screenWidth;
        }
        if (y < 0){
            y = y+gameEngine.screenHeight;
        }
        if (x>gameEngine.screenWidth-sizeX){
            x=x-gameEngine.screenWidth;
        }
        if (y>gameEngine.screenHeight-sizeY){
            y = y-gameEngine.screenHeight;
        }
    }

}
