package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperO {
    public Key(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/textures/Object/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
