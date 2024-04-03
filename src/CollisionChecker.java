public class CollisionChecker {


    GameEngine gameEngine;


    public CollisionChecker(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }
    public void checkAllCollisions(Entity entity){
        checkCollisionAllEntities(entity);
        checkCollisionsAllObjects(entity);

    }
    public void checkCollisionAllEntities(Entity entity){
        //Checks for entity-entity collisions
        if (entity != null){
            entity.hittingSomething = false;
            move(entity);
            for(int i = 0; i < gameEngine.entityList.length; i++){
                if(gameEngine.entityList[i] != null){
                    if (entity.equals(gameEngine.entityList[i])){    
                    }
                    else if(entity.hitbox.intersects(gameEngine.entityList[i].hitbox)){
                        entity.hittingSomething = true;     
                        //System.out.println("Collision Detected! " + gameEngine.entityList[i].name + " is hitting " + entity.name);           
                    }
                }
                
            }
            undoMove(entity);
        } 
        
    }
    public void checkCollisionsAllObjects(Entity entity){
        if (entity != null){
            move(entity);
            for(int i = 0; i < gameEngine.foodList.size(); i++){
                if (entity.hitbox.intersects(gameEngine.foodList.get(i).hitbox)){
                    
                    switch(entity.name){
                        
                        case "redAnt":
                            ((RedAntColony) gameEngine.entityList[0]).foodRemaining = ((RedAntColony) gameEngine.entityList[0]).foodRemaining + gameEngine.foodList.get(i).foodValue;
                            gameEngine.foodList.remove(i);
                            break;
                        case "blueAnt":
                            ((BlueAntColony) gameEngine.entityList[1]).foodRemaining = ((BlueAntColony) gameEngine.entityList[1]).foodRemaining + gameEngine.foodList.get(i).foodValue;
                            gameEngine.foodList.remove(i);
                            break;
                        case "greenAnt":
                            ((GreenAntColony) gameEngine.entityList[2]).foodRemaining = ((GreenAntColony) gameEngine.entityList[2]).foodRemaining + gameEngine.foodList.get(i).foodValue; 
                            gameEngine.foodList.remove(i);
                            break;
                    
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
