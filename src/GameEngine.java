import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class GameEngine extends Canvas implements Runnable{

    final int originalTileSize = 16;
    final int scale = 1;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 90;
    public final int maxScreenRow = 67;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public int numParticles = 520;
    public double tempDistance;
    public double G = .25; // gravitational constant
    double drag = .29; //.75 is a good value 

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public ArrayList<Entity> entityList = new ArrayList<>();
    
    
    int FPS = 60;

    public GameEngine() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);      
        
        Particle centralStar = new Particle(this);
        centralStar.mass = 5000;
        centralStar.speed=0;
        centralStar.xSpeed = 0;
        centralStar.ySpeed = 0;
        centralStar.sizeX = (int)Math.sqrt(centralStar.mass);
        centralStar.sizeY = (int)Math.sqrt(centralStar.mass);
        centralStar.x=screenWidth/2-Math.sqrt(centralStar.mass)/2;
        centralStar.y=screenHeight/2-Math.sqrt(centralStar.mass)/2;
        
        
        for (int i = 0; i < numParticles; i++) {
            Particle p = new Particle(this);
            
            //p.x = screenWidth+Math.random()*30;
            entityList.add(p);
            
        }
        //entityList.add(centralStar);
        
        
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
    List<Entity> toAdd = new ArrayList<>();
    List<Entity> toRemove = new ArrayList<>();

    for (int i = 0; i < entityList.size(); i++) {
        Entity entity = entityList.get(i);
        if (entity == null || toRemove.contains(entity)) continue;

        for (int j = 0; j < entityList.size(); j++) {
            Entity entity2 = entityList.get(j);
            if (entity2 == null || entity == entity2 || toRemove.contains(entity2)) continue;

            double dx = entity2.x - entity.x;
            double dy = entity2.y - entity.y;
            double distanceSquared = (dx * dx) + (dy * dy);
            double approxRadius1 = (entity.sizeX/2+entity.sizeY/2)/2.0;
            double approxRadius2 = (entity2.sizeX/2+entity2.sizeY/2)/2.0;
            if (Math.sqrt(distanceSquared)<(approxRadius1+approxRadius2)) {
                if (entity2.kineticEnergy + entity.kineticEnergy > entity.gravBindingEnergy) {
                    if (entity.mass < 1) continue;

                    double childMass = entity.mass / 2.0;
                    if (childMass < 2) continue;

                    for (int k = 0; k < 2; k++) {
                        Particle fragment = new Particle(this);
                        fragment.mass = Math.max(childMass, 1);
                        fragment.x = entity.x + Math.random() * 10 - 5;
                        fragment.y = entity.y + Math.random() * 10 - 5;
                        fragment.xSpeed = entity.xSpeed + Math.random() - 0.5;
                        fragment.ySpeed = entity.ySpeed + Math.random() - 0.5;
                        fragment.sizeX = (int)Math.sqrt(fragment.mass);
                        fragment.sizeY = (int)Math.sqrt(fragment.mass);
                        fragment.clamp();
                        fragment.setBounds();
                        //System.out.println("Making Fragment");
                        toAdd.add(fragment);
                        //System.out.println("Added Fragment to addList");
                    }

                    //System.out.println("Removing collided entity");
                    toRemove.add(entity);
                    break; // Stop processing this entity if it's destroyed
                } else {
                    // Merge logic
                    entity.xSpeed = (entity.xSpeed * entity.mass + entity2.xSpeed * entity2.mass) / (entity.mass + entity2.mass);
                    entity.ySpeed = (entity.ySpeed * entity.mass + entity2.ySpeed * entity2.mass) / (entity.mass + entity2.mass);
                    entity.x = (entity.x * entity.mass + entity2.x * entity2.mass) / (entity.mass + entity2.mass);
                    entity.y = (entity.y * entity.mass + entity2.y * entity2.mass) / (entity.mass + entity2.mass);
                    entity.mass = entity.mass + entity2.mass;
                    entity.sizeX = (int)Math.sqrt(entity.mass);
                    entity.sizeY = (int)Math.sqrt(entity.mass);
                    toRemove.add(entity2);
                }
            } else {
                double distance = Math.sqrt(distanceSquared);
                double acceleration = G * entity2.mass / distanceSquared;

                entity.xAcceleration += acceleration * dx / distance;
                entity.yAcceleration += acceleration * dy / distance;
            }
        }

        // Physics integration
        entity.xSpeed += entity.xAcceleration * drag / 10;
        entity.ySpeed += entity.yAcceleration * drag / 10;
        entity.updateState();
        entity.acceleration = 0;
        entity.xAcceleration = 0;
        entity.yAcceleration = 0;
    }

    entityList.removeAll(toRemove);
    entityList.addAll(toAdd);
    //System.out.println("Added addList to entityList");
}
    public double min(double a, double b){
        if(a < b){
            return a;
        }
        else{
            return b;
        }
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void spawnEntity(Entity entity){
        entityList.add(entity);
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

    
    public double max(double a, double b){
        if (a>=b){
            return a;
        }
        else{
            return b;
        }
    }
}