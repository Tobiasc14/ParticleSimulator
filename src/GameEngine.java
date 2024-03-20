import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameEngine extends JPanel implements Runnable{

    //Below if for if we want our game to have distinct "tile" spaces
    final int originalTileSize = 16;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; //32 pixel by 32 pixel tiles
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 30;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);
    RedAnt redAnt = new RedAnt(this, keyHandler);
    RedAnt redAnt1 = new RedAnt(this, keyHandler);
    RedAnt redAnt2 = new RedAnt(this, keyHandler);
    RedAnt redAnt3 = new RedAnt(this, keyHandler);
    RedAnt redAnt4 = new RedAnt(this, keyHandler);
    GreenAnt greenAnt = new GreenAnt(this, keyHandler);
    GreenAnt greenAnt1 = new GreenAnt(this, keyHandler);
    GreenAnt greenAnt2 = new GreenAnt(this, keyHandler);
    GreenAnt greenAnt3 = new GreenAnt(this, keyHandler);
    GreenAnt greenAnt4 = new GreenAnt(this, keyHandler);

    CenterSeekingPhysicsAnt centerPhysicsAnt = new CenterSeekingPhysicsAnt(this, keyHandler);
    PhysicsAnt physicsAnt = new PhysicsAnt(this, keyHandler);
    





    public GameEngine(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        //The KeyHandler class defines a key listener, we then add that to the game engine
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        
        


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
       double drawInterval = 1000000000/FPS;
       double delta = 0;
       long lastTime = System.nanoTime();
       long currentTime;
       //Timer is how often the FPS counter updates
       //Draw count is the number of frames drawn
       long timer = 0;
       int drawCount = 0;

      
      


        while(gameThread != null){
            //gets current time in nanoseconds
            currentTime = System.nanoTime();
            //Calculates total time that's passed since last drawing as farctino of desired time pass
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            //When desired amount of time has passed, execute game loop
            if(delta >= 1){
                updateState();
                repaint();
                delta--;
                //Ever time a frame us drawn add to drawCount
                drawCount++;
            }
            //Update FPS counter if more than 1s has passed
            if(timer >= 1000000000){
                System.out.println("FPS " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            


        }

    }

    public void updateState(){
        player.updateState();
        redAnt.updateState();
        redAnt1.updateState();
        redAnt2.updateState();
        redAnt3.updateState();
        redAnt4.updateState();
        greenAnt.updateState();
        greenAnt1.updateState();
        greenAnt2.updateState();
        greenAnt3.updateState();
        greenAnt4.updateState();
        physicsAnt.updateState();
        

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        redAnt.draw(g2);
        redAnt1.draw(g2);
        redAnt2.draw(g2);
        redAnt3.draw(g2);
        redAnt4.draw(g2);
        greenAnt.draw(g2);
        greenAnt1.draw(g2);
        greenAnt2.draw(g2);
        greenAnt3.draw(g2);
        greenAnt4.draw(g2);
        physicsAnt.draw(g2);
          

    }



}
