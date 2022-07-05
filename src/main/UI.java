package main;

import characters.Player;
import objects.Key;
import objects.Stars;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
     GamePanel gp;
     Font arial_40;
     Font arial_20;

     BufferedImage keyImg;

     BufferedImage starImg;

     BufferedImage wkeyImg;

     BufferedImage wstarImg;
     BufferedImage noKeyImg;

     BufferedImage noStarImg;

     public UI(GamePanel gp){
          this.gp = gp;

          arial_40 = new Font("Arial", Font.PLAIN, 40);
          arial_20 = new Font("Arial", Font.PLAIN, 20);

          Key key = new Key();
          keyImg = key.image;

          Stars star = new Stars();
          starImg = star.image;
          try {
               noStarImg  = ImageIO.read(getClass().getResourceAsStream("/textures/Object/starNone.png"));
               wstarImg  = ImageIO.read(getClass().getResourceAsStream("/textures/Object/wood2.png"));
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
          try {
               noKeyImg  = ImageIO.read(getClass().getResourceAsStream("/textures/Object/keyNone.png"));
               wkeyImg  = ImageIO.read(getClass().getResourceAsStream("/textures/Object/wood1.png"));
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }

     /**
      * Показує протягом проходження гри, статус зірок та ключа
      * @param g2
      */
     public void draw(Graphics2D g2){
          g2.setFont(arial_20);
          g2.setColor(Color.black);
          if (gp.playState == gp.gameState) {
               g2.drawImage(wstarImg, gp.tileSize / 2 + gp.tileSize+5, gp.tileSize / 2, gp.tileSize*4-5, gp.tileSize, null);
               g2.drawString("lvl -" +gp.mapNum, gp.tileSize / 2 + gp.tileSize*3-20, gp.tileSize+5);
               if (gp.mapNum == 2 || gp.mapNum == 3 || gp.mapNum == 4) {
                    g2.drawImage(wkeyImg, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
                    if (gp.player.keyIndex == 1){
                         g2.drawImage(keyImg, gp.tileSize/4*3-5, gp.tileSize/4*3-4, gp.tileSize/10*8, gp.tileSize/10*8, null);
                    } else {
                         g2.drawImage(noKeyImg, gp.tileSize/4*3-5, gp.tileSize/4*3-4, gp.tileSize/10*8, gp.tileSize/10*8, null);
                    }

               }
               if (gp.mapNum == 3 || gp.mapNum == 4) {
                    g2.drawImage(wstarImg, gp.tileSize / 2, gp.tileSize / 2 + gp.tileSize+5, gp.tileSize*5, gp.tileSize, null);

                    for (int i = 0; i <= 4; i++){
                         g2.drawImage(noStarImg, gp.tileSize/4*3-5 + i*gp.tileSize, gp.tileSize/4*3-4 + gp.tileSize+5, gp.tileSize/10*8, gp.tileSize/10*8, null);
                    }
                    for (int i = 0; i <= gp.player.starIndex-1; i++){
                         g2.drawImage(starImg, gp.tileSize/4*3-5 + i*gp.tileSize, gp.tileSize/4*3-4 + gp.tileSize+5, gp.tileSize/10*8, gp.tileSize/10*8, null);
                    }


               }
               if (gp.collisionChecker.checkObject(gp.player, true) != 999 && gp.obj[gp.collisionChecker.checkObject(gp.player, true)] != null && gp.obj[gp.collisionChecker.checkObject(gp.player, true)].name.equals("DoorClose")) {
                    g2.setFont(arial_20);
                    g2.drawString(" - You have to find a key!", 45, gp.screenHeight / 2);
               }
          }
          if (gp.gameState == gp.gameOverState){
               drawGameOverScreen(g2);
          }
     }

     /**
      * Викидаємо в кінці гри, вікно, що гравець закінчив проходити гру "Лабіринт" та показує кількість зірок зібраних гравцем
      * @param g2
      */
     public void drawGameOverScreen(Graphics2D g2){
          g2.setColor(new Color(0,0,0,150));
          g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
          g2.setFont(arial_40);
          g2.setColor(Color.black);
          g2.drawString("GAME OVER", 45, gp.screenHeight / 2);
          g2.setColor(Color.white);
          g2.drawString("GAME OVER", 45-4, gp.screenHeight / 2 -4);
          g2.drawImage(wstarImg, gp.screenWidth/2-20, gp.screenHeight / 2-gp.tileSize, gp.tileSize*8, gp.tileSize*2, null);
          g2.drawImage(starImg, gp.screenWidth/2+20, gp.screenHeight / 2-gp.tileSize+20, gp.tileSize, gp.tileSize, null);
          g2.setFont(arial_40);
          g2.setColor(Color.black);
          g2.drawString("- " + gp.player.stars + "/10", gp.screenWidth/2+120, gp.screenHeight / 2-gp.tileSize+60);
     }
}
