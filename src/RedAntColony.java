import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RedAntColony extends AntColony{

    public RedAntColony(GameEngine gameEngine, int numAnts) {
        super(gameEngine, numAnts);
        getSpriteImage();
        
    
    }
    

    public void setDefaultValues(){
        foodRemaining = 200;
        sizeX = 16;
        sizeY = 16;
        speed = 0;
        x = (int) (gameEngine.screenWidth*Math.random())-sizeX;
        y = (int) (gameEngine.screenHeight*Math.random())-sizeY;
        setBounds();
    }

    public void getSpriteImage(){
        try {
            image = (BufferedImage) ImageIO.read(new File("images/AntColony/RedAntColony.png"));
        } catch (IOException e) {            
            e.printStackTrace();
        };
    }
    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, sizeX, sizeY, null);
    }

}
