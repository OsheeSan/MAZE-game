package characters;

import main.GamePanel;
import main.KeyInput;
import objects.DoorClose;
import objects.DoorOpen;
import objects.Key;
import objects.Stars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Characters {

    GamePanel gp;
    KeyInput ki;

    public final int screenX;
    public final int screenY;

    public int a = 2;
    public int k = 1;
    public int starIndex = 0;
    public int keyIndex = 0;

    public int stars = 0;


    public final int defaultScreenX;
    public final int defaultScreenY;

    /**
     *Базові налаштування гравця
     */
    public Player(GamePanel gp, KeyInput ki) {
        this.gp = gp;
        this.ki = ki;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        defaultScreenX = screenX;
        defaultScreenY = screenY;

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Початкові координати гравця на мапі
     */
    public void setDefaultValues() {
        worldX = gp.tileSize * 14;
        worldY = gp.tileSize * 14;
        speed = 4;
        direction = "down";
    }

    /**
     * Докачуємо в базу малюнки нашого гравця(щоб був ефект, що він рухається)
     */
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/up2.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/up.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/down2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/down.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/left2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/left.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/right2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/textures/Characters/Player/right.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Відновлює кожен раз положення гравця відносно натисканою кнопки руху
     */
    public void update() {
        if (ki.downPressed == false && ki.leftPressed == false && ki.rightPressed == false && ki.upPressed == false) {
            moving = false;
        }
        if (ki.upPressed == true) {
            collisionOn = false;
            direction = "up";
            gp.collisionChecker.checkTerrain(this);
            gp.collisionChecker.checkObject(this, true);
            if(collisionOn == false)
                worldY -= speed;
            moving = true;
        }
        if (ki.downPressed == true) {
            collisionOn = false;
            direction = "down";
            gp.collisionChecker.checkTerrain(this);
            gp.collisionChecker.checkObject(this, true);
            if(collisionOn == false)
                worldY += speed;
            moving = true;
        }
        if (ki.rightPressed == true) {
            collisionOn = false;
            direction = "right";
            gp.collisionChecker.checkTerrain(this);
            gp.collisionChecker.checkObject(this, true);
            if(collisionOn == false)
                worldX += speed;
            moving = true;
        }
        if (ki.leftPressed == true) {
            collisionOn = false;
            direction = "left";
            gp.collisionChecker.checkTerrain(this);
            gp.collisionChecker.checkObject(this, true);
            if(collisionOn == false)
                worldX -= speed;
            moving = true;
        }

        //Check object collision
        int objIndex = gp.collisionChecker.checkObject(this, true);
        pickUpObj(objIndex);

        animationCounter++;
        if (animationCounter > 10) {
            if (animationNum == 1) {
                animationNum = 2;
            }
            else if (animationNum == 2) {
                animationNum = 1;
            }
            animationCounter = 0;
        }
    }

    /**
     * Створено статичне положення об'єктів, їх підбирання та перехід на новий рівень
     * @param i
     */
    public void pickUpObj(int i) {
        //Початкові положення об'єктів на кожному рівні окремо
        if(gp.mapNum == 2 && a == 2){
            for(int j = 0; j < gp.obj.length; j++){
                gp.obj[j] = null;
            }

            gp.obj[0] = new DoorClose();
            gp.obj[0].worldX = 27 * gp.tileSize;
            gp.obj[0].worldY = 15 * gp.tileSize;

            gp.obj[1] = new Key();
            gp.obj[1].worldX = 17 * gp.tileSize;
            gp.obj[1].worldY = 20 * gp.tileSize;
            a = 3;
        }

        if(gp.mapNum == 3 && a == 3){
            for(int j = 0; j < gp.obj.length; j++){
                gp.obj[j] = null;
            }

            gp.obj[0] = new DoorClose();
            gp.obj[0].worldX = 29 * gp.tileSize;
            gp.obj[0].worldY = 21 * gp.tileSize;

            gp.obj[1] = new Key();
            gp.obj[1].worldX = 18 * gp.tileSize;
            gp.obj[1].worldY = 23 * gp.tileSize;

            gp.obj[2] = new Stars();
            gp.obj[2].worldX = 14 * gp.tileSize;
            gp.obj[2].worldY = 10 * gp.tileSize;

            gp.obj[3] = new Stars();
            gp.obj[3].worldX = 12 * gp.tileSize;
            gp.obj[3].worldY = 14 * gp.tileSize;

            gp.obj[4] = new Stars();
            gp.obj[4].worldX = 27 * gp.tileSize;
            gp.obj[4].worldY = 14 * gp.tileSize;

            gp.obj[5] = new Stars();
            gp.obj[5].worldX = 22 * gp.tileSize;
            gp.obj[5].worldY = 18 * gp.tileSize;

            gp.obj[6] = new Stars();
            gp.obj[6].worldX = 13 * gp.tileSize;
            gp.obj[6].worldY = 23 * gp.tileSize;
            a = 4;
        }

        if(gp.mapNum == 4 && a == 4) {
            for (int j = 0; j < gp.obj.length; j++) {
                gp.obj[j] = null;
            }
            gp.obj[0] = new DoorClose();
            gp.obj[0].worldX = 30 * gp.tileSize;
            gp.obj[0].worldY = 10 * gp.tileSize;

            gp.obj[1] = new Stars();
            gp.obj[1].worldX = 10 * gp.tileSize;
            gp.obj[1].worldY = 28 * gp.tileSize;

            gp.obj[2] = new Stars();
            gp.obj[2].worldX = 29 * gp.tileSize;
            gp.obj[2].worldY = 24 * gp.tileSize;

            gp.obj[3] = new Stars();
            gp.obj[3].worldX = 18 * gp.tileSize;
            gp.obj[3].worldY = 18 * gp.tileSize;

            gp.obj[4] = new Stars();
            gp.obj[4].worldX = 27 * gp.tileSize;
            gp.obj[4].worldY = 10 * gp.tileSize;

            gp.obj[5] = new Stars();
            gp.obj[5].worldX = 24 * gp.tileSize;
            gp.obj[5].worldY = 25 * gp.tileSize;

            gp.obj[6] = new Key();
            gp.obj[6].worldX = 29 * gp.tileSize;
            gp.obj[6].worldY = 29 * gp.tileSize;
            a = 5;
        }

        if (i != 999 && gp.obj[i] != null) {

            //Підбір об'єктів
            if (gp.obj[i] != null && gp.obj[i].name.equals("Star")) {
                gp.playSE(1);
                gp.obj[i] = null;
                starIndex++;
                stars++;
            }

            if(gp.obj[i] != null && gp.obj[i].name.equals("Key")){
                gp.playSE(1);
                gp.obj[i] = null;
                keyIndex++;
            }


            //Умови для відкривання дверей
            if(gp.mapNum == 2 && keyIndex == 1 && k == 1){
                gp.playSE(2);
                gp.obj[0] = null;

                gp.obj[0] = new DoorOpen();
                gp.obj[0].worldX = 27 * gp.tileSize;
                gp.obj[0].worldY = 15 * gp.tileSize;
                k = 2;
            }

            if(gp.mapNum == 3 && keyIndex == 1 && k == 2){
                gp.playSE(2);
                gp.obj[0] = null;

                gp.obj[0] = new DoorOpen();
                gp.obj[0].worldX = 29 * gp.tileSize;
                gp.obj[0].worldY = 21 * gp.tileSize;
                k = 3;
            }

            if(gp.mapNum == 4 && keyIndex == 1 && k == 3){
                gp.playSE(2);
                gp.obj[0] = null;

                gp.obj[0] = new DoorOpen();
                gp.obj[0].worldX = 30 * gp.tileSize;
                gp.obj[0].worldY = 10 * gp.tileSize;
                k = 4;
            }

            //Перехід на наступний рівень
            if(gp.mapNum == 1 && gp.obj[i] != null && gp.obj[i].name.equals("DoorOpen")){
                gp.playSE(4);
                gp.obj[i] = null;

                //Player's position on the map lvl2
                worldX = 9 * gp.tileSize;
                worldY = 14 * gp.tileSize;

                keyIndex = 0;
                starIndex = 0;

                gp.mapNum = 2;

            } else if(gp.mapNum == 2 && gp.obj[i] != null && gp.obj[i].name.equals("DoorOpen")){
                gp.playSE(4);
                gp.obj[0] = null;

                //Player's position on the map lvl3
                worldX = 9 * gp.tileSize;
                worldY = 10 * gp.tileSize;

                keyIndex = 0;
                starIndex = 0;

                gp.mapNum = 3;
            } else if(gp.mapNum == 3 && gp.obj[i] != null && gp.obj[i].name.equals("DoorOpen")){
                gp.playSE(4);
                gp.obj[0] = null;

                //Player's position on the map lvl4
                worldX = 10 * gp.tileSize;
                worldY = 18 * gp.tileSize;

                keyIndex = 0;
                starIndex = 0;

                gp.mapNum = 4;
            } else if (gp.mapNum == 4 && gp.obj[i] != null && gp.obj[i].name.equals("DoorOpen")){
                gp.gameState = gp.gameOverState;
            }

        }
    }

    /**
     * Промальовка гравця на карті
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (moving == true) {
                    if (animationNum == 1) {
                        image = up1;
                    }
                    if (animationNum == 2) {
                        image = up2;
                    }
                } else {
                    image = up;
                }
                break;
            case "down":
                if (moving == true) {
                    if (animationNum == 1) {
                        image = down1;
                    }
                    if (animationNum == 2) {
                        image = down2;
                    }
                } else {
                    image = down;
                }
                break;
            case "left":
                if (moving == true) {
                    if (animationNum == 1) {
                        image = left1;
                    }
                    if (animationNum == 2) {
                        image = left2;
                    }
                } else {
                    image = left;
                }
                break;
            case "right":
                if (moving == true) {
                    if (animationNum == 1) {
                        image = right1;
                    }
                    if (animationNum == 2) {
                        image = right2;
                    }
                } else {
                    image = right;
                }
                break;
        }

        if(gp.mapNum == 1)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        else if(gp.mapNum == 2)
            g2.drawImage(image, defaultScreenX, defaultScreenY, gp.tileSize, gp.tileSize, null);
        else if(gp.mapNum == 3)
            g2.drawImage(image, defaultScreenX, defaultScreenY, gp.tileSize, gp.tileSize, null);
        else if(gp.mapNum == 4)
            g2.drawImage(image, defaultScreenX, defaultScreenY, gp.tileSize, gp.tileSize, null);
    }

}




