
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
       
        

    }
    
    public void setDefaultValues(){        
        foodRemaining = 200;
        direction = "down";
        speed = 0;        
        sizeX = 16;
        sizeY = 16;   
        name = "antColony";
    }
    public void updateState(){
        foodCheckCounter++;
        if (foodCheckCounter >60){
            foodCost = antList.size();
            foodRemaining = foodRemaining-foodCost;
            if (foodRemaining>numAnts){ 
            }
            else{
                while (foodRemaining < foodCost & antList.size()>0){                    
                    removeAnt(antList.get(0));
                    foodCost = antList.size();                    
                }
                numAnts = antList.size();                
            }
            foodCheckCounter = 0;
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

}
