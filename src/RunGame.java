import java.awt.BorderLayout;
import javax.swing.JFrame;

public class RunGame {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ant Simulator");

        GameEngine gameEngine = new GameEngine();
        gameEngine.setSize(gameEngine.screenWidth, gameEngine.screenHeight);
        
        // Use BorderLayout to allow full canvas sizing
        window.setLayout(new BorderLayout());
        window.add(gameEngine, BorderLayout.CENTER);
        window.pack();
        window.setSize(gameEngine.screenWidth, gameEngine.screenHeight);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Request focus so key input works
        gameEngine.requestFocus();

        gameEngine.startGameThread();
    }
}
