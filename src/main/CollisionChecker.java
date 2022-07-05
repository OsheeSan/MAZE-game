package main;

import characters.Characters;
import characters.Player;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Перевірка на колізію гравця з стінами, коробками.
     * @param character
     */
    public void checkTerrain(Player character){
        int entityLeftWorldX = character.worldX + character.solidArea.x;
        int entityRightWorldX = character.worldX + character.solidArea.x + character.solidArea.width;

        int entityTopWorldY = character.worldY + character.solidArea.y;
        int entityBottomWorldY = character.worldY + character.solidArea.y + character.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;

        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (character.direction){
            case "up":
                entityTopRow = (entityTopWorldY - character.speed) / gp.tileSize;
                tileNum1 = gp.terrainM.mapTerrainNum1[entityLeftCol][entityTopRow];
                tileNum2 = gp.terrainM.mapTerrainNum1[entityRightCol][entityTopRow];

                if(gp.terrainM.terrains[tileNum1].collision == true || gp.terrainM.terrains[tileNum2].collision == true){
                    character.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + character.speed) / gp.tileSize;
                tileNum1 = gp.terrainM.mapTerrainNum1[entityLeftCol][entityBottomRow];
                tileNum2 = gp.terrainM.mapTerrainNum1[entityRightCol][entityBottomRow];

                if(gp.terrainM.terrains[tileNum1].collision == true || gp.terrainM.terrains[tileNum2].collision == true){
                    character.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - character.speed) / gp.tileSize;
                tileNum1 = gp.terrainM.mapTerrainNum1[entityLeftCol][entityTopRow];
                tileNum2 = gp.terrainM.mapTerrainNum1[entityLeftCol][entityBottomRow];

                if(gp.terrainM.terrains[tileNum1].collision == true || gp.terrainM.terrains[tileNum2].collision == true){
                    character.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + character.speed) / gp.tileSize;
                tileNum1 = gp.terrainM.mapTerrainNum1[entityRightCol][entityTopRow];
                tileNum2 = gp.terrainM.mapTerrainNum1[entityRightCol][entityBottomRow];

                if(gp.terrainM.terrains[tileNum1].collision == true || gp.terrainM.terrains[tileNum2].collision == true){
                    character.collisionOn = true;
                }
                break;
        }
    }

    /**
     * Перевірка на колізію гравця з об'єктами(двері, ключ, зірки).
     * @param characters
     * @param player
     * @return
     */
    public int checkObject(Characters characters, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //get entity's solid area position
                characters.solidArea.x = characters.worldX + characters.solidArea.x;
                characters.solidArea.y = characters.worldY + characters.solidArea.y;

                //get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (characters.direction){
                    case "up":
                        characters.solidArea.y -= characters.speed;
                        if(characters.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                characters.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        characters.solidArea.y += characters.speed;
                        if(characters.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                characters.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        characters.solidArea.x -= characters.speed;
                        if(characters.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                characters.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        characters.solidArea.x += characters.speed;
                        if(characters.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                characters.collisionOn = true;
                            }

                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                characters.solidArea.x = characters.solidAreaDefaultX;
                characters.solidArea.y = characters.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }

        return index;
    }
}

