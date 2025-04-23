package entity;
import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//import main.GamePanel;
import main.KeyHandler;

import main.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler key;
    final int screenX;
    final int screenY;
   
    public Player(GamePanel gp,KeyHandler key){
        this.gp=gp;
        this.key=key;//cho phép player hỏi trạng thái từ bàn phím chứ ko trực tiếp nghe
        setDefaultValues();
        getPlayerImage();
        screenX = gp.getScreenWidth()/2-(gp.getTileSize());
        screenY = gp.getScreenHeight()/2-(gp.getTileSize());

        solidArea = new Rectangle(20*2,55*2,7*2,5*2);
    }
    public int getScreenX(){
        return screenX;
    }
    public int getScreenY(){
        return screenY;
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down"; 
    }
    public void getPlayerImage(){
        try{
            down1=ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("/res/player/down3.png"));
            down4=ImageIO.read(getClass().getResourceAsStream("/res/player/down4.png"));
            down5=ImageIO.read(getClass().getResourceAsStream("/res/player/down5.png"));
            down6=ImageIO.read(getClass().getResourceAsStream("/res/player/down6.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("/res/player/left3.png"));
            left4=ImageIO.read(getClass().getResourceAsStream("/res/player/left4.png"));
            left5=ImageIO.read(getClass().getResourceAsStream("/res/player/left5.png"));
            left6=ImageIO.read(getClass().getResourceAsStream("/res/player/left6.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("/res/player/right3.png"));
            right4=ImageIO.read(getClass().getResourceAsStream("/res/player/right4.png"));
            right5=ImageIO.read(getClass().getResourceAsStream("/res/player/right5.png"));
            right6=ImageIO.read(getClass().getResourceAsStream("/res/player/right6.png"));
            up1=ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("/res/player/up3.png"));
            up4=ImageIO.read(getClass().getResourceAsStream("/res/player/up4.png"));
            up5=ImageIO.read(getClass().getResourceAsStream("/res/player/up5.png"));
            up6=ImageIO.read(getClass().getResourceAsStream("/res/player/up6.png"));

            sdown1=ImageIO.read(getClass().getResourceAsStream("/res/player/sdown1.png"));
            sdown2=ImageIO.read(getClass().getResourceAsStream("/res/player/sdown2.png"));
            sdown3=ImageIO.read(getClass().getResourceAsStream("/res/player/sdown3.png"));
            sdown4=ImageIO.read(getClass().getResourceAsStream("/res/player/sdown4.png"));
            sup1=ImageIO.read(getClass().getResourceAsStream("/res/player/sup1.png"));
            sup2=ImageIO.read(getClass().getResourceAsStream("/res/player/sup2.png"));
            sup3=ImageIO.read(getClass().getResourceAsStream("/res/player/sup3.png"));
            sup4=ImageIO.read(getClass().getResourceAsStream("/res/player/sup4.png"));
            sright1=ImageIO.read(getClass().getResourceAsStream("/res/player/sright1.png"));
            sright2=ImageIO.read(getClass().getResourceAsStream("/res/player/sright2.png"));
            sright3=ImageIO.read(getClass().getResourceAsStream("/res/player/sright3.png"));
            sright4=ImageIO.read(getClass().getResourceAsStream("/res/player/sright4.png"));
            sleft1=ImageIO.read(getClass().getResourceAsStream("/res/player/sleft1.png"));
            sleft2=ImageIO.read(getClass().getResourceAsStream("/res/player/sleft2.png"));
            sleft3=ImageIO.read(getClass().getResourceAsStream("/res/player/sleft3.png"));
            sleft4=ImageIO.read(getClass().getResourceAsStream("/res/player/sleft4.png"));

          
        }catch( IOException e ){
            e.printStackTrace();
        }
    }
    public void update(){
        if(key.upPressed==true||key.downPressed==true||key.leftPressed==true||key.rightPressed==true){
            if(key.upPressed==true){  direction="up";} 
            if(key.downPressed==true){  direction="down";}
            if(key.leftPressed==true){  direction="left";}
            if(key.rightPressed==true){  direction="right";}
            spriteCounter++;

            collisionOn=false;
            gp.setColCheckTile(this);
            if(collisionOn==false){
                switch(direction){
                    case "up": y-=speed; break;
                    case "down": y+=speed; break;
                    case "right":x+=speed; break;
                    case "left": x-=speed; break;
                }
            }
            if(spriteCounter>5){
                if(spriteNum==1) spriteNum=2;
                else if (spriteNum==2) spriteNum=3;
                else if (spriteNum==3) spriteNum=4;
                else if (spriteNum==4) spriteNum=5;
                else if (spriteNum==5) spriteNum=6;
                else if (spriteNum==6) spriteNum=1;
                spriteCounter=0;
            }
        }
        else{
            switch(direction){
                case "up": direction="sup"; spriteNum=1; break;
                case "down": direction="sdown"; spriteNum=1; break;
                case "right": direction="sright"; spriteNum=1; break;
                case "left": direction="sleft"; spriteNum=1; break;
            }

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum==1) spriteNum=2;
                else if (spriteNum==2) spriteNum=3;
                else if (spriteNum==3) spriteNum=4;
                else if (spriteNum==4) spriteNum=1;
                spriteCounter=0;
            }
        }
        
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, 48, 48);
        BufferedImage image = null;
        switch(direction){
            case "up":
                if (spriteNum==1){ image=up1; }
                if (spriteNum==2){ image=up2; }
                if (spriteNum==3){ image=up3; }
                if (spriteNum==4){ image=up4; }
                if (spriteNum==5){ image=up5; }
                if (spriteNum==6){ image=up6; }
                break;
            case "down":
                if (spriteNum==1){ image=down1; }
                if (spriteNum==2){ image=down2; }
                if (spriteNum==3){ image=down3; }
                if (spriteNum==4){ image=down4; }
                if (spriteNum==5){ image=down5; }
                if (spriteNum==6){ image=down6; }
                break;
            case "right":
                if (spriteNum==1){ image=right1; }
                if (spriteNum==2){ image=right2; }
                if (spriteNum==3){ image=right3; }
                if (spriteNum==4){ image=right4; }
                if (spriteNum==5){ image=right5; }
                if (spriteNum==6){ image=right6; }
                break;
            case "left":
                if (spriteNum==1){ image=left1; }
                if (spriteNum==2){ image=left2; }
                if (spriteNum==3){ image=left3; }
                if (spriteNum==4){ image=left4; }
                if (spriteNum==5){ image=left5; }
                if (spriteNum==6){ image=left6; }
                break;
            case "sdown":
                if (spriteNum==1){ image=sdown1; }
                if (spriteNum==2){ image=sdown2; }
                if (spriteNum==3){ image=sdown3; }
                if (spriteNum==4){ image=sdown4; }
                break;
            case "sup":
                if (spriteNum==1){ image=sup1; }
                if (spriteNum==2){ image=sup2; }
                if (spriteNum==3){ image=sup3; }
                if (spriteNum==4){ image=sup4; }
                break;
            case "sleft":
                if (spriteNum==1){ image=sleft1; }
                if (spriteNum==2){ image=sleft2; }
                if (spriteNum==3){ image=sleft3; }
                if (spriteNum==4){ image=sleft4; }
                break;
            case "sright":
                if (spriteNum==1){ image=sright1; }
                if (spriteNum==2){ image=sright2; }
                if (spriteNum==3){ image=sright3; }
                if (spriteNum==4){ image=sright4; }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.getTileSize()*2,gp.getTileSize()*2,null);
    }
}
