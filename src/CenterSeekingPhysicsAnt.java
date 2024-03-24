
public class CenterSeekingPhysicsAnt extends PhysicsAnt{

    
    
    public CenterSeekingPhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine, keyHandler);


    }
    public void setDefaultValues(){
        x = 200;
        y = 100;
        direction = "left";        
        sizeX = 25;
        sizeY = 25;
        setBounds();
        
    }

    public void updateState(){
        
        physicsCounter++;

        if(physicsCounter>1){
            
            if (y<(gameEngine.screenHeight-16)/2){  
                ySpeed=ySpeed+1; 
            } 
            else if(y>(gameEngine.screenHeight-16)/2){
                ySpeed = ySpeed-1;
            }            
            if(x<(gameEngine.screenWidth-16)/2){
                xSpeed= xSpeed+1;
            }  
            else if(x>(gameEngine.screenWidth-16)/2){
                xSpeed=xSpeed-1;                
            }            
            x = x+xSpeed; 
            y = y+ySpeed;        
            physicsCounter=0;
        } 

        spriteCounter++;

        if(spriteCounter>15){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

        //Clamps position
        clamp();
        hitbox.setBounds(x, y, sizeX, sizeY);
    }

    public void clamp(){
        if (x < 0){
            x=0;
        }
        if (y < 0){
            y = 0;
        }
        if (x>gameEngine.screenWidth-sizeX){
            x=gameEngine.screenWidth-sizeX;
        }
        if (y>gameEngine.screenHeight-sizeY){
            y = gameEngine.screenHeight-sizeY;
        }
    }

}
