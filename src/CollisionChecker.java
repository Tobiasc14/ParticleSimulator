public class CollisionChecker {


    GameEngine gameEngine;


    public CollisionChecker(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    public void playerCheckCollision(Entity entity){
        entity.hittingSomething = false;
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
        for(int i = 0; i < gameEngine.entityList.length; i++){
            if(entity.hitbox.intersects(gameEngine.entityList[i].hitbox)){
                entity.hittingSomething = true;
                
                               
            }           
        }
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



    public void entityCheckCollision(Entity entity){
        entity.hittingSomething = false;
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
        for(int i = 0; i < gameEngine.entityList.length; i++){
            if (entity.equals(gameEngine.entityList[i])){

            }
            else if(entity.hitbox.intersects(gameEngine.entityList[i].hitbox)){
                entity.hittingSomething = true;
                
                               
            }           
        }
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
