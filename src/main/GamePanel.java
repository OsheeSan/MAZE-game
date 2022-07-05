package main;

import characters.Player;
import objects.SuperO;
import terrain.Terrain;
import terrain.TerrainManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //786px
    public final int screenHeight = tileSize * maxScreenRow; //576px

    //WORLD SETTINGS
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public int mapNum = 1;

    //FPS
    int fps = 60;

    TerrainManager terrainM = new TerrainManager(this);

    KeyInput key = new KeyInput();
    Sound sound = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;


    public Player player = new Player(this, key);
    public SuperO obj[] = new SuperO[30];

    //Player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;

    //Game state

    public int gameState;
    public final int playState = 1;
    public final int gameOverState = 2;


    /**
     * Налаштування екрану гри
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    /**
     * Запуск перших налаштувань гри
     */
    public void setupGame(){
       aSetter.setObject();
       playMusic();
       gameState = playState;
    }

    /**
     * Запускає нашу гру
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Оновлює положення гравця на мапі
     */
    public void update(){
        if (gameState == playState)
        player.update();
    }

    /**
     * Вимальовує всі об'єкти на мапі, саму мапу та граіця
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
            terrainM.draw(g2);

                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].draw(g2, this);
                    }
            }

            player.draw(g2);

                //UI
            ui.draw(g2);

            System.out.println(collisionChecker.checkObject(player, true));

            g2.dispose();

    }

    /**
     * Запускає основну музику
     */
    public void playMusic(){
        sound.setMainMusic();
        sound.play();
        sound.loop();
    }

    /**
     * Зупиняє музику
     */
    public void stopMusic(){
        sound.stop();
    }

    /**
     * Запускає звукові ефекти на об'єкти
     * @param i
     */
    public void playSE(int i){
        sound.setFile(i);
        sound.play();

    }
}
