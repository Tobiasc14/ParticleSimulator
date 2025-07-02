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

    public int numParticles = 50;

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
            if (entity != null) {
                entity.updateState();
            }
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
}