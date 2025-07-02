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
    
    
    

    public Particle(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        setDefaultValues();
        
    }


    public void setDefaultValues(){
        speed = 0;        
        sizeX = 1;
        sizeY = 1;
        mass = 20;
        acceleration = 0;
        x = (Math.random() * (gameEngine.screenWidth - sizeX-1));
        y = (Math.random() * (gameEngine.screenHeight - sizeY-1));
        defaultSpeed = 0;
        angle = Math.random()*2*Math.PI;
        xSpeed = 0;
        ySpeed = 0;        
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
        y=(int)(y+ySpeed);
        x=(int)(x+xSpeed);
        clamp();
        hitbox.setBounds((int)x, (int)y, sizeX, sizeY);
        
    }

    

    public void clamp(){
        if (x < 0){
            x=gameEngine.screenWidth+x;
        }
        if (y < 0){
            y = gameEngine.screenHeight+y;
        }
        if (x>=gameEngine.screenWidth-sizeX){
            x=x-gameEngine.screenWidth-sizeX;
        }
        if (y>=gameEngine.screenHeight-sizeY){
            y = y-gameEngine.screenHeight-sizeY;
        }
        if (xSpeed > 20){
            xSpeed = 20;
        }
        if (ySpeed > 20){
            ySpeed = 20;
        }
    }

}
