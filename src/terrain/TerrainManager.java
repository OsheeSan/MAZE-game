package terrain;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TerrainManager {

    GamePanel gp;
    public Terrain[] terrains;
    public int mapTerrainNum1[][];
    public int mapTerrainNum2[][];

    public TerrainManager(GamePanel gp){
        this.gp = gp;

        terrains = new Terrain[10];
        mapTerrainNum1 = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTerrainImage();
        switchMap();
    }

    /**
     * Переходить на наступний рівень гри. Загружає нову карту.
     */
    public void switchMap(){
        switch (gp.mapNum){
            case 1:
                loadMap("/maps/lvl1.txt");
                break;
            case 2:
                loadMap("/maps/lvl2.txt");
                break;
            case 3:
                loadMap("/maps/lvl3.txt");
                break;
            case 4:
                loadMap("/maps/lvl4.txt");
                break;
        }
    }

    /**
     * Додаємо до бази текстури для гри
     */
    public void getTerrainImage(){

        try{
            terrains[1] = new Terrain();
            terrains[1].image = ImageIO.read(getClass().getResourceAsStream("/textures/Terrain/floorLab1.png"));

            terrains[2] = new Terrain();
            terrains[2].image = ImageIO.read(getClass().getResourceAsStream("/textures/Terrain/floorLab2.png"));

            terrains[3] = new Terrain();
            terrains[3].image = ImageIO.read(getClass().getResourceAsStream("/textures/Terrain/floorLab3.png"));

            terrains[4] = new Terrain();
            terrains[4].image = ImageIO.read(getClass().getResourceAsStream("/textures/Terrain/wallLab2.png"));
            terrains[4].collision = true;

            terrains[5] = new Terrain();
            terrains[5].image = ImageIO.read(getClass().getResourceAsStream("/textures/Terrain/wallLab.png"));
            terrains[5].collision = true;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Загружаємо з бази мапу в гру
     * @param filePath
     */
    public void loadMap(String filePath){

            try {
                InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;
                while (row < gp.maxWorldRow && col < gp.maxWorldCol) {
                    String line = br.readLine();

                    while (col < gp.maxWorldCol) {
                        String numbers[] = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        mapTerrainNum1[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
                br.close();
            } catch (Exception e) {

            }

    }

    /**
     * Малює світ
     * @param g2
     */
    public void draw(Graphics2D g2){
        switchMap();
            int worldCol = 0;
            int worldRow = 0;
            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
                int terrainNum =  mapTerrainNum1[worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.drawImage(terrains[terrainNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                worldCol++;

                if(worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }
            }

    }
}
