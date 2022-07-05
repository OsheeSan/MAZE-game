package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Stars extends SuperO{
    public Stars(){
        name = "Star";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/textures/Object/Star.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
