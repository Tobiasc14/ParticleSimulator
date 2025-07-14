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
        speed = (int)(Math.random()*21);        
        mass = Math.random()*25+2;
        sizeX = (int)Math.sqrt(mass);
        sizeY = (int)Math.sqrt(mass);        
        acceleration = 0;
        x = (Math.random() * (gameEngine.screenWidth - sizeX-1));
        y = (Math.random() * (gameEngine.screenHeight - sizeY-1));
        defaultSpeed = 0;
        angle = Math.random()*2*Math.PI;
        xSpeed = Math.random()*speed-speed/2.0;
        ySpeed = Math.random()*speed-speed/2.0;
        absKineticEnergy = 0.5*mass*speed*speed;
        gravBindingEnergy= 0.6*gameEngine.G*mass*mass/sizeX/2;        
        //setBounds();
        clamp();
        name = "particle";      
       
        
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.lightGray);
        //g2.fill(hitbox);        
        g2.drawOval((int)x-(sizeX/2), (int)y-(sizeY/2), sizeX, sizeY);
        g2.fillOval((int)x-(sizeX/2), (int)y-(sizeY/2), sizeX, sizeY);
        
    }
    public void updateState(){ 
        y=(y+ySpeed);
        x=(x+xSpeed);
        //kineticEnergy = 0.5 * mass * (xSpeed * xSpeed + ySpeed * ySpeed);
        gravBindingEnergy = 0.6 * gameEngine.G * mass * mass / sizeX / 2;
        clamp();
        //ellipse.setBounds((int)x, (int)y, sizeX, sizeY);
        
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
