package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorOpen extends SuperO{
    public DoorOpen(){
        name = "DoorOpen";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/textures/Object/doorOpen.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
