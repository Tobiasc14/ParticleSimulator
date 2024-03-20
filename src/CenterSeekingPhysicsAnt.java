
public class CenterSeekingPhysicsAnt extends PhysicsAnt{

    public CenterSeekingPhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine, keyHandler);


    }

    public void updateState(){
        if (y<(gameEngine.screenHeight-16)/2){
            physicsCounter++;
            ySpeed = ySpeed+(physicsCounter/60);
            y = y+ySpeed;
            if(y>(gameEngine.screenHeight-16)/2){
                //System.out.println("y speed" + ySpeed);
                physicsCounter = 0;
            }
        }        
        else if(y>(gameEngine.screenHeight-16)/2){
            physicsCounter++;
            ySpeed = ySpeed-(physicsCounter/60);
            y = y+ySpeed;
            //System.out.println("Decelerating");
            if(y<(gameEngine.screenHeight-16)/2){
                physicsCounter = 0;
            }
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
        }
        if (y>gameEngine.screenHeight-16){
            y = gameEngine.screenHeight-16;
            //System.out.println("Final ySpeed: " + ySpeed);
            ySpeed = 0;
            physicsCounter = 0;
        }
    }

}
