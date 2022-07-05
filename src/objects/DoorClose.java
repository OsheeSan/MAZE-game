package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorClose extends SuperO{
    public DoorClose(){
        name = "DoorClose";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/textures/Object/doorClose.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
