package main;

import objects.DoorOpen;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    /**
     * Початкове положення всіх об'єктів на першому рівні
     */
    public void setObject(){

        gp.obj[5] = new DoorOpen();
        gp.obj[5].worldX = 28 * gp.tileSize;
        gp.obj[5].worldY = 15 * gp.tileSize;

    }
}