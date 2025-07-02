import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class GameEngine extends Canvas implements Runnable{

    final int originalTileSize = 16;
    final int scale = 1;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 80;
    public final int maxScreenRow = 60;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public int numParticles = 200;
    public double tempDistance;
    double G = 1.0; // gravitational constant
    double drag = .5;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Entity[] entityList;
    
    
    int FPS = 60;

    public GameEngine() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);        
        
        entityList = new Entity[numParticles+1];        
        

        for (int i = 0; i < entityList.length; i++) {
            if (i < numParticles) {
                entityList[i] = new Particle(this);
                
                
            } else {
                //entityList[i] = new Player(this, keyHandler);
            }
        }

      
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }    

    @Override
    public void run() {
        this.createBufferStrategy(3);
    BufferStrategy bs = this.getBufferStrategy();

    final long drawInterval = 1_000_000_000 / FPS; // 16,666,666 ns for 60 FPS
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    int drawCount = 0;

    while (gameThread != null) {
        long currentTime = System.nanoTime();
        long elapsed = currentTime - lastTime;

        if (elapsed >= drawInterval) {
            lastTime = currentTime;

            long start = System.nanoTime();
            updateState();
            draw(bs);
            long end = System.nanoTime();
            System.out.printf("Frame time: %.3f ms%n", (end - start) / 1e6);

            drawCount++;
        } else {
            // Sleep until it's time to render the next frame
            long sleepTime = (drawInterval - elapsed) / 1_000_000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Report FPS every second
        if (System.currentTimeMillis() - timer >= 1000) {
            System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer += 1000;
        }
    }
    }

    public void updateState() {
    
    for (Entity entity : entityList) {
        if (entity == null) continue;

        for (Entity entity2 : entityList) {
            if (entity2 == null || entity == entity2) continue;

            double dx = entity2.x - entity.x;
            double dy = entity2.y - entity.y;
            double distanceSquared = dx * dx + dy * dy;

            if (entity.hitbox.intersects(entity2.hitbox)){

                entity.xSpeed = (entity.xSpeed*entity.mass + entity2.xSpeed*entity2.mass)/(entity.mass+entity2.mass);
                entity.ySpeed = (entity.ySpeed*entity.mass + entity2.ySpeed*entity2.mass)/(entity.mass+entity2.mass);
                entity.mass = entity.mass+entity2.mass;
                entity.sizeX=(int)Math.sqrt(entity.mass);
                entity.sizeY=(int)Math.sqrt(entity.mass);
                
                //entity.sizeX+=entity2.sizeX;
                //entity.sizeY+=entity2.sizeY;


                removeEntity(entity2);

            } // skip self or overlapping particles
            else{
                double distance = Math.sqrt(distanceSquared);

                double acceleration = G * entity2.mass / distanceSquared;

                // Add directional acceleration
                entity.xAcceleration += acceleration * dx / distance;
                entity.yAcceleration += acceleration * dy / distance;
            }
            
        }

        // Apply acceleration to velocity
        entity.xSpeed += entity.xAcceleration * drag/ 10 ;
        entity.ySpeed += entity.yAcceleration * drag/ 10 ;

        // Debug output
        //System.out.printf("xAcc: %.3f, xSpd: %.3f | yAcc: %.3f, ySpd: %.3f\n",
                          //entity.xAcceleration, entity.xSpeed,
                          //entity.yAcceleration, entity.ySpeed);

        entity.updateState();

        // Reset for next frame
        entity.acceleration = 0;
        entity.xAcceleration = 0;
        entity.yAcceleration = 0;
    }
}

    public void draw(BufferStrategy bs) {
        do {
            do {
                Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
                g2.setColor(getBackground());
                g2.fillRect(0, 0, getWidth(), getHeight());

                for (Entity entity : entityList) {
                    if (entity != null) entity.draw(g2);
                }

                g2.dispose();
            } while (bs.contentsRestored());
            bs.show();
            Toolkit.getDefaultToolkit().sync();
        } while (bs.contentsLost());
    }

    public void removeEntity(Entity entity) {
        if (entity != null) {
            for (int i = 0; i < entityList.length; i++) {
                if (entity.equals(entityList[i])) {
                    entityList[i] = null;
                    break;
                }
            }
        }
    }
    public int max(int a, int b){
        if (a>=b){
            return a;
        }
        else{
            return b;
        }
    }
}