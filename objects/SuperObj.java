package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObj {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int x,y;
    public BufferedImage getImage(){
        return image;
    }
    public void setImage(BufferedImage image){
        this.image=image;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public boolean getCollision(){
        return collision;
    }
    public void setCollision(boolean collision){
        this.collision=collision;
    }
    public int getX(){ return x;}
    public int getY(){ return y;}
    public void setX(int x){ this.x=x; }
    public void setY(int y){ this.y=y; }
    public void draw(Graphics2D g2,GamePanel gp){
        int screenX=(x-gp.getPlayerX()+gp.getPlayerScreenX());
        int screenY=(y-gp.getTileSize()-gp.getPlayerY()+gp.getPlayerScreenY());
        
        /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
        player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
         để lôi player ra giữa màn hình */
        if(x+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
           x-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
           y-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
           y+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
