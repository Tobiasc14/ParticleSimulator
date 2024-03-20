import java.awt.image.BufferedImage;

public class Entity {

    public int x, y, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage upLeft1, upLeft2, downRight1, downRight2, downLeft1, downLeft2, upRight1, upRight2;
    public String direction;
    public int directionChangeCounter = 0;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    

}
