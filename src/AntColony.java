
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AntColony extends Entity{

    GameEngine gameEngine;
    ArrayList<Ant> antList = new ArrayList<Ant>();
    BufferedImage image;
    int foodRemaining,foodCost, numAnts;
    int foodCheckCounter = 0;


    public AntColony(GameEngine gameEngine, int numAnts){
        this.gameEngine = gameEngine;
        this.numAnts = numAnts;
        setDefaultValues();
        clamp();        

    }
    
    public void setDefaultValues(){        
        foodRemaining = 200;
        direction = "down";
        speed = 0;        
        sizeX = 14;
        sizeY = 14;   
        name = "antColony";
    }
    public void updateState(){
        foodCheckCounter++;
        if (foodCheckCounter >60){
            foodCost = antList.size();
            foodRemaining = foodRemaining-foodCost;
            if(foodRemaining <0){
                foodRemaining = 0;
            }            
            if (foodRemaining>numAnts){ 
            }
            else{
                if (foodRemaining < foodCost & antList.size()>0){                    
                    removeAnt(antList.get(0));
                    foodCost = antList.size();                    
                }
                numAnts = antList.size();                
            }
            foodCheckCounter = 0;
            System.out.println("Food Remaining in Colony: " + foodRemaining);
        }

        

    }
   

    public void addAnt(Ant ant){
        if(ant != null){
            antList.add(ant);
        }
        
    }
    public void removeAnt(Ant ant){
        if(ant != null){
            antList.remove(ant);
            
            gameEngine.removeEntity(ant);
        }
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
