public class CollisionChecker {


    GameEngine gameEngine;


    public CollisionChecker(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }
    public void checkAllCollisions(Entity entity){
        checkCollisionAllEntities(entity);

    }
    public void checkCollisionAllEntities(Entity entity){
        //Checks for entity-entity collisions
        if (entity != null){
            entity.hittingSomething = false;
            move(entity);
            for(int i = 0; i < gameEngine.entityList.size(); i++){
                if(gameEngine.entityList.get(i) != null){
                    if (entity.equals(gameEngine.entityList.get(i))){    
                    }
                    else if(entity.ellipse.intersects(gameEngine.entityList.get(i).ellipse.getBounds())){
                        entity.hittingSomething = true;     
                        //System.out.println("Collision Detected! " + gameEngine.entityList[i].name + " is hitting " + entity.name);           
                    }
                }
                
            }
            undoMove(entity);
        } 
        
    }
    
   

    
    public void move(Entity entity){
        switch(entity.direction){
            case "up": entity.y-= entity.speed; break;
            case "down": entity.y+= entity.speed; break;
            case "right": entity.x+= entity.speed; break;
            case "left": entity.x-= entity.speed; break;
            case "upLeft": entity.x-=entity.speed; entity.y -= entity.speed; break;
            case "downLeft": entity.x-=entity.speed; entity.y += entity.speed; break;
            case "upRight": entity.x+=entity.speed; entity.y -= entity.speed; break;
            case "downRight": entity.x+=entity.speed; entity.y += entity.speed; break;
        }
        entity.setBounds();
    }
    public void undoMove(Entity entity){
        switch(entity.direction){
            case "up": entity.y+= entity.speed; break;
            case "down": entity.y-= entity.speed; break;
            case "right": entity.x-= entity.speed; break;
            case "left": entity.x+= entity.speed; break;
            case "upLeft": entity.x+=entity.speed; entity.y += entity.speed; break;
            case "downLeft": entity.x+=entity.speed; entity.y -= entity.speed; break;
            case "upRight": entity.x-=entity.speed; entity.y += entity.speed; break;
            case "downRight": entity.x-=entity.speed; entity.y -= entity.speed; break;
        }

        entity.setBounds();
    }
}
