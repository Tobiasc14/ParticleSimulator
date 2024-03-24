
public class CenterSeekingPhysicsAnt extends PhysicsAnt{

    
    
    public CenterSeekingPhysicsAnt(GameEngine gameEngine, KeyHandler keyHandler){
        super(gameEngine, keyHandler);


    }
    public void setDefaultValues(){
        x = 200;
        y = 100;
        speed = 2;
        direction = "left";        
        sizeX = 25;
        sizeY = 25;
        setBounds();
        
    }
    //is called in the update state, which is called by the physics ant parent class
    public void move(){
        if(speed !=0 & !hittingSomething){
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
        }
         
    }

    

}
