import javax.swing.JFrame;

public class RunGame {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ant Simulator");

        GameEngine gameEngine = new GameEngine();
        window.add(gameEngine);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameEngine.startGameThread();
        
        
    }

}
