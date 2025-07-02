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
        speed = 5;        
        mass = Math.random()*4+1;
        sizeX = (int)Math.sqrt(mass);
        sizeY = (int)Math.sqrt(mass);        
        acceleration = 0;
        x = (Math.random() * (gameEngine.screenWidth - sizeX-1));
        y = (Math.random() * (gameEngine.screenHeight - sizeY-1));
        defaultSpeed = 0;
        angle = Math.random()*2*Math.PI;
        xSpeed = Math.random()*speed/2;
        ySpeed = Math.random()*speed/2;        
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
        y=(y+ySpeed);
        x=(x+xSpeed);
        clamp();
        hitbox.setBounds((int)x, (int)y, sizeX, sizeY);
        
    }

    

    public void clamp() {
    // Wrap X
    if (x < 0) {
        //System.out.println("X Value: " + x);
        x += gameEngine.screenWidth;
    } else if (x >= gameEngine.screenWidth) {
        x -= gameEngine.screenWidth;
    }

    // Wrap Y
    if (y < 0) {
        //System.out.println("Y Value: " + y);
        y += gameEngine.screenHeight;

    } else if (y >= gameEngine.screenHeight) {
        y -= gameEngine.screenHeight;
    }

    // Clamp speed in both directions
    xSpeed = Math.max(-20, Math.min(xSpeed, 20));
    ySpeed = Math.max(-20, Math.min(ySpeed, 20));
}

}
