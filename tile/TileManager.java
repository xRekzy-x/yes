package tile;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
public class TileManager {
    GamePanel gp;
    Tile[] tile;
    private int mapTile[][];
    private int overlay[][];
    public TileManager(GamePanel gp){
        this.gp=gp;
        tile= new Tile[50];
        getTileImage();
        
        mapTile = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];//row-col
        overlay = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];
        
        loadmap("/res/map/map1.txt",mapTile);
        loadmap("/res/map/map2.txt",overlay);
        
    }

    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/000.png"));
            tile[1]=new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree.png"));
            tile[2]=new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/third.png"));
            tile[3]=new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass.png"));
            tile[4]=new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/farmland.png"));
            tile[5]=new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/smalltree.png"));
            tile[5].collision=true;
            tile[6]=new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree1.png"));
            tile[6].collision=true;
            tile[7]=new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree2.png"));
            tile[7].collision=true;
            tile[8]=new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree3.png"));
            tile[8].collision=true;
            tile[9]=new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree4.png"));
            tile[9].collision=true;
            tile[10]=new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree5.png"));
            tile[10].collision=true;
            tile[11]=new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree6.png"));
            tile[11].collision=true;
            tile[12]=new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree7.png"));
            tile[12].collision=true;
            tile[13]=new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree8.png"));
            tile[13].collision=true;
            tile[14]=new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bigtree9.png"));
            tile[14].collision=true;
            tile[15]=new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water1.png"));
            tile[15].collision=true;
            tile[16]=new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water2.png"));
            tile[16].collision=true;
            tile[17]=new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water3.png"));
            tile[17].collision=true;
            tile[18]=new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_rock.png"));
            tile[18].collision=true;
            tile[19]=new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water_leaf.png"));
            tile[19].collision=true;
            tile[20]=new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangTop1.png"));
            tile[21]=new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangTop2.png"));
            tile[22]=new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangTop3.png"));
            tile[23]=new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangMid1.png"));
            tile[24]=new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangMid2.png"));
            tile[25]=new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangMid3.png"));
            tile[26]=new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangDown1.png"));
            tile[27]=new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangDown2.png"));
            tile[28]=new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bridgeNgangDown3.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadmap(String filepath,int mapTile[][]){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.getMaxWorldCol()&&row<gp.getMaxWorldRow()){
                String line = br.readLine();//String line sẽ chứa 1 dòng của map
                while(col<gp.getMaxWorldCol()){
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[row][col] = num;
                    col++;
                }
                if(col==gp.getMaxWorldCol()){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){}
    }
    public void draw1(Graphics2D g2, int mapTile[][]){
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        for(int i=0;i<gp.getMaxWorldRow();i++){
            for(int j=0;j<gp.getMaxWorldCol();j++){
                worldX=j*gp.getTileSize();
                worldY=i*gp.getTileSize();
                screenX=(worldX-gp.getPlayerX()+gp.getPlayerScreenX());
                screenY=(worldY-gp.getTileSize()-gp.getPlayerY()+gp.getPlayerScreenY());
                
                /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
                player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 để lôi player ra giữa màn hình */
                int num= mapTile[i][j];
                if(worldX+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
                   worldX-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
                   worldY-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
                   worldY+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
                    g2.drawImage(tile[num].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
                
                 
                   
            }
        }
    }
    public void draw2(Graphics2D g2,int mapTile[][]){
        int screenX;
        int screenY;
        int worldX;
        int worldY;
        for(int i=gp.getMaxWorldRow()-1;i>=0;i--){
            for(int j=gp.getMaxWorldCol()-1;j>=0;j--){
                worldX=j*gp.getTileSize();
                worldY=i*gp.getTileSize();
                screenX=(worldX-gp.getPlayerX()+gp.getPlayerScreenX());
                screenY=(worldY-gp.getTileSize()-gp.getPlayerY()+gp.getPlayerScreenY());
                /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
                player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                 để lôi player ra giữa màn hình */
                int num= mapTile[i][j];
                if(worldX+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
                   worldX-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
                   worldY-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
                   worldY+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
                    g2.drawImage(tile[num].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
        }
    }
}
            public void draw3(Graphics2D g2,int mapTile[][]){
                int screenX;
                int screenY;
                int worldX;
                int worldY;
                for(int i=0;i<gp.getMaxWorldRow();i++){
                    for(int j=gp.getMaxWorldCol()-1;j>=0;j--){
                        worldX=j*gp.getTileSize();
                        worldY=i*gp.getTileSize();
                        screenX=(worldX-gp.getPlayerX()+gp.getPlayerScreenX());
                        screenY=(worldY-gp.getTileSize()-gp.getPlayerY()+gp.getPlayerScreenY());
                        /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
                        player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                         để lôi player ra giữa màn hình */
                        int num= mapTile[i][j];
                        if(worldX+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
                           worldX-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
                           worldY-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
                           worldY+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
                            g2.drawImage(tile[num].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                }     
            }
        }
    }
            public void draw4(Graphics2D g2,int mapTile[][]){
                int screenX;
                int screenY;
                int worldX;
                int worldY;
                for(int i=gp.getMaxWorldRow()-1;i>=0;i--){
                    for(int j=0;j<gp.getMaxWorldCol();j++){
                        worldX=j*gp.getTileSize();
                        worldY=i*gp.getTileSize();
                        screenX=(worldX-gp.getPlayerX()+gp.getPlayerScreenX());
                        screenY=(worldY-gp.getTileSize()-gp.getPlayerY()+gp.getPlayerScreenY());
                        /*giải thích 2 dòng trên: player đang ở vị trí random thì mình cho 
                        player về góc trái trên cùng của màn hình rồi sau đó +screenX screenY
                        để lôi player ra giữa màn hình */
                        int num= mapTile[i][j];
                        if(worldX+2*gp.getTileSize()>gp.getPlayerX()-gp.getPlayerScreenX()&&
                        worldX-2*gp.getTileSize()<gp.getPlayerX()+gp.getPlayerScreenX()&&
                        worldY-3*gp.getTileSize()<gp.getPlayerY()+gp.getPlayerScreenY()&&
                        worldY+2*gp.getTileSize()>gp.getPlayerY()-gp.getPlayerScreenY()){
                            g2.drawImage(tile[num].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                }     
            }
        }
    }
            public int[][] getMapTile(){ return mapTile; }
            public int[][] getOverLay(){ return overlay; }
            public Tile[] getTile(){ return tile; }
    
}
