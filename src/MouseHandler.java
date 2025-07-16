import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements java.awt.event.MouseListener, MouseMotionListener {

    public Point mouseCoords;

    boolean mouseClicked;
    boolean mousePressed;
    boolean mouseDragged;
    

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("Clicked mouse");
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
        mousePressed = true;
        mouseCoords = e.getPoint();
        //System.out.println("Pressed mouse");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Triggers if mouse released after being held?
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
        mousePressed = false;
        mouseDragged = false;
        mouseCoords = e.getPoint();
        //System.out.println("Released mouse");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Fires whenever mouse is clicked AND moved
        //throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
        
        mouseCoords = e.getPoint();
        //System.out.println("Dragged mouse");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Fires when mouse moves
        //throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
        //System.out.println("Moved mouse");
    }


}
