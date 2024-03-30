import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameEngine extends JPanel implements Runnable{

    //Below if for if we want our game to have distinct "tile" spaces
    final int originalTileSize = 16;
    final int scale = 1;
    public final int tileSize = originalTileSize * scale; //32 pixel by 32 pixel tiles
    public final int maxScreenCol = 80;
    public final int maxScreenRow = 60;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public int numRedAnts, numBlueAnts, numGreenAnts, numPhysicsAnts, numPlayerAnts, numCenterSeekingPhysAnts, totalAnts;
    
    
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    
    //Adds entities
    //Player player = new Player(this, keyHandler);
    public Entity [] entityList;
    RedAntColony redColony;
    BlueAntColony blueColony;
    GreenAntColony greenColony;
    int FPS = 60;  
    


    //CenterSeekingPhysicsAnt centerPhysicsAnt = new CenterSeekingPhysicsAnt(this, keyHandler);
    //PhysicsAnt physicsAnt = new PhysicsAnt(this, keyHandler);
    
    public GameEngine(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        //The KeyHandler class defines a key listener, we then add that to the game engine
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        setNumAnts();
        entityList = new Entity[totalAnts+3];
        redColony = new RedAntColony(this, numRedAnts);
        blueColony = new BlueAntColony(this, numBlueAnts);
        greenColony = new GreenAntColony(this, numGreenAnts);
        entityList[0] = redColony;
        entityList[1] = blueColony;
        entityList[2] = greenColony;

        for (int i =3; i < entityList.length; i++){
            if (i < entityList.length-(numBlueAnts+numGreenAnts+numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts)){
                entityList[i] = new RedAnt(this);
                redColony.addAnt((Ant) entityList[i]);
            }
            else if(i>=entityList.length-(numBlueAnts+numGreenAnts+numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts) & i<entityList.length-(numGreenAnts+numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts)){
                entityList[i] = new BlueAnt(this);
                blueColony.addAnt((Ant) entityList[i]);
            }
            else if(i>=entityList.length-(numGreenAnts+numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts) & i<entityList.length-(numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts)){
                entityList[i] = new GreenAnt(this);
                greenColony.addAnt((Ant) entityList[i]);
            }
            else if(i>=entityList.length-(numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts) & i<entityList.length-(numCenterSeekingPhysAnts+numPlayerAnts)){
                entityList[i] = new PhysicsAnt(this, keyHandler);
            }
            else if(i>=entityList.length-(numCenterSeekingPhysAnts+numPlayerAnts) & i<entityList.length-(numPlayerAnts)){
                entityList[i] = new CenterSeekingPhysicsAnt(this, keyHandler);
            }
            else if(i>=entityList.length-(numPlayerAnts)){
                entityList[i] = new Player(this, keyHandler);
            }
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    public void setNumAnts(){
        numRedAnts = 0;
        numBlueAnts =5;
        numGreenAnts = 0;
        numPhysicsAnts = 0;
        numCenterSeekingPhysAnts = 0;
        numPlayerAnts = 1;
        totalAnts = numRedAnts+numBlueAnts+numGreenAnts+numPhysicsAnts+numCenterSeekingPhysAnts+numPlayerAnts;
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
        //player.updateState();
        for (int i = 0; i < entityList.length; i++){
            if (entityList[i] != null){
                entityList[i].updateState();
            }
            
        }
        //physAnt.updateState();
        //centerPhysicsAnt.updateState();
        

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //player.draw(g2);
        for (int i = 0; i < entityList.length; i++){
            if (entityList[i] != null){
                entityList[i].draw(g2);
            }
            
        }
    }
        //physAnt.draw(g2);
        //centerPhysicsAnt.draw(g2);
          

    public void removeEntity(Entity entity){
        if (entity != null){
            for(int i = 0; i< entityList.length; i++){                
                if(entity.equals(entityList[i])){                    
                    entityList[i] = null;
                    i = entityList.length-1;
                }
            }
        }
    }



}
