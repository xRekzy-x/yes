package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest extends SuperObj {
    public Chest(){
        super.setName("Chest");
        try{
            super.setImage(ImageIO.read(getClass().getResourceAsStream("/res/obj/chest.png")));
        }catch(IOException e){ e.printStackTrace(); }
    }
    
}
