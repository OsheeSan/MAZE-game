package characters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Characters {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up, down, left, right, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int animationCounter = 0;
    public int animationNum = 1;

    public boolean moving = false;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}
