
public class CenterSeekingPhysicsAnt extends PhysicsAnt{

    public CenterSeekingPhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine, keyHandler);


    }
    public void setDefaultValues(){
        x = 200;
        y = 100;
        direction = "left";
        
    }

    public void updateState(){
        
        physicsCounter++;

        if(physicsCounter>1){
            
            if (y<(gameEngine.screenHeight-16)/2){  
                ySpeed++; 
            } 
            else if(y>(gameEngine.screenHeight-16)/2){
                ySpeed--;
            }            
            if(x<(gameEngine.screenWidth-16)/2){
                xSpeed++;
            }  
            else if(x>(gameEngine.screenWidth-16)/2){
                xSpeed--;                
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

        //Clamps position to within bounds of screen
        if (x < 0){
            x=0;
        }
        if (y < 0){
            y = 0;
        }
        if (x>gameEngine.screenWidth-16){
            x=gameEngine.screenWidth-16;
            xSpeed = 0;
            physicsCounter = 0;
        }
        if (y>gameEngine.screenHeight-16){
            y = gameEngine.screenHeight-16;
            //System.out.println("Final ySpeed: " + ySpeed);
            ySpeed = 0;
            physicsCounter = 0;
        }
    }

}
