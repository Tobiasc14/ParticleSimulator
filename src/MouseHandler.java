import java.awt.Point;
import java.awt.event.MouseEvent;

public class MouseHandler implements java.awt.event.MouseListener {

    public Point mouseCoords;

    boolean mouseClicked;
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Clicked mouse");
        mouseClicked = true;
        mouseCoords = e.getPoint();
                
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Triggered when mouse enters game window
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Triggers when mouse leaves game window
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Triggers if mouse pressed and held?
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Triggers if mouse released after being held?
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }


}
