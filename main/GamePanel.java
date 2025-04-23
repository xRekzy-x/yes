package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Entity;
import entity.Player;
import objects.Chest;
import objects.SuperObj;
import tile.Tile;
import tile.TileManager;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTING
    final int originalTileSize=16; // 16x16 tile
    final int scale = 3; // tăng kích thước của nhân vật từ 16x16 lên 48x48
    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol= 16;
    final int maxScreenRow= 12;
    final int screenWidth =  maxScreenCol * tileSize;//768 pixel
    final int screenHeight = maxScreenRow * tileSize;//576 pixel
    

    KeyHandler key = new KeyHandler();
    Thread gameThread; // to do sth like drawing a picture 60 times every second

    Player player = new Player(this,key);
    TileManager tile = new TileManager(this);

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //FPS
    int FPS=60;

    //WORLD SETTING
    final int maxWorldCol=50;
    final int maxWorldRow=50;
    final int worldWidth=maxWorldCol*tileSize;
    final int worldHeight=maxWorldRow*tileSize;



    public GamePanel(){
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //enalbing this can improve rendering performace
        this.addKeyListener(key);//allow input from keyboard
        this.setFocusable(true);// with this, gamepanel can be "focused" to receive input
    }
    //COLLISION
    CollisionChecker colCheck = new CollisionChecker(this);
    public void setColCheckTile(Entity entity){
        colCheck.checkTile(entity);
    }
    //OBJECT
    SuperObj obj[]=new SuperObj[50];
    public void setObjects(){
        obj[0]=new Chest();
        obj[0].setX(4*tileSize);
        obj[0].setY(4*tileSize);
    }

    //GETTER SETTER
    public int getTileSize(){
        return tileSize;
    }
    public int getScreenWidth(){ 
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }
    public int getScale(){
        return scale;
    }
    public int getMaxScreenCol(){
        return maxScreenCol;
    }
    public int getMaxScreenRow(){
        return maxScreenRow;
    }
    public int getMaxWorldCol(){
        return maxWorldCol;
    }
    public int getWorldWidth(){
        return worldWidth;
    }
    public int getMaxWorldRow(){
        return maxWorldRow;
    }
    public int getWorldHeight(){
        return worldHeight;
    }
    public int getPlayerX(){
        return player.x;
    }
    public int getPlayerY(){
        return player.y;
    }
    public int getPlayerScreenX(){
        return player.getScreenX();
    }
    public int getPlayerScreenY(){
        return player.getScreenY();
    }
    public int[][] getMapTile(){
        return tile.getMapTile();
    }
    public int[][] getOverLay(){
        return tile.getOverLay();
    }
    public Tile[] getTile(){
        return tile.getTile();
    }
    //SET UP THE GAME
    public void setupGame(){
        setObjects();
    }

    //RUN THE GAME
    public void startGameThread(){
        gameThread = new Thread(this);//passing GamePanel class to this Thread's constructor
        gameThread.start();//automatically call run method
    }

    @Override
    public void run(){//when we start the game thread, this run method will be automatically called
        
        double drawInterval= 1000000000/FPS;//time to draw 1 frame - 0,016s
        double nextDrawTime =System.nanoTime()+drawInterval;
        long lastTime=System.nanoTime();
        long timer = 0;
        int drawCount=0;
        
        while(gameThread!=null){
            long currentTime = System.nanoTime();//1 billion nanosecond = 1 second
            //UPDATE: UPDATE INFORMATION SUCH AS CHARACTER'S POSITION
            update();
            //DRAW: DRAW THE SCREEN WITH THE UPDATED INFORMATION
            repaint();//call painComponent method
          
            try {//khi chạy nó sẽ làm quá nhiều loop trong 1 giây nên phải cho nó ngủ để nó chỉ làm đúng FPS
                double remainingTime = nextDrawTime - System.nanoTime();
                if(remainingTime<0) remainingTime=0;
                Thread.sleep((long) remainingTime/1000000);//do thread.sleep chi nhan milisecond nen phai chia 
                drawCount++;
                timer+=(currentTime-lastTime);//thời gian đã trôi qua
                lastTime=currentTime;
                nextDrawTime +=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(timer >=1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){//Graphics is a class containing many functions to draw objects on screen
        super.paintComponent(g); //super ở đây là  của JPanel
        Graphics2D g2 = (Graphics2D)g; //Graphics2D extends Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management and text layout

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight()); // Clear toàn bộ nền
        
        if(key.leftPressed==true&&key.downPressed==true){ tile.draw3(g2,tile.getMapTile()); tile.draw3(g2,tile.getOverLay()); }
        else if(key.rightPressed==true&&key.upPressed==true){ tile.draw4(g2,tile.getMapTile()); tile.draw4(g2,tile.getOverLay()); }
        else if(key.rightPressed==true||key.downPressed==true){ tile.draw1(g2,tile.getMapTile()); tile.draw1(g2,tile.getOverLay()); }
        else{ tile.draw2(g2,tile.getMapTile()); tile.draw2(g2,tile.getOverLay()); }
   
        for(int i=0;i< obj.length;i++){
            if(obj[i]!= null) obj[i].draw(g2,this);
        }
        player.draw(g2);
        g2.dispose(); //saves memory
    }
}
