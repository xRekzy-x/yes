package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
       
        int entityTop = entity.y+entity.solidArea.y;
        int entityDown = entityTop+entity.solidArea.height;
        int entityLeft = entity.x+entity.solidArea.x;
        int entityRight = entityLeft+entity.solidArea.width;

        int blockTop = entityTop/gp.getTileSize();
        int blockDown = entityDown/gp.getTileSize();
        int blockLeft = entityLeft/gp.getTileSize();
        int blockRight = entityRight/gp.getTileSize();

        int tileNum1, tileNum2;
        int mapTile[][] = gp.getMapTile();
        int overlay[][] = gp.getOverLay();
        Tile tile[] = gp.getTile();
        switch(entity.direction){
            case "up":
                blockTop=(entityTop-entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockLeft];
                tileNum2= mapTile[blockTop][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockTop][blockLeft];
                tileNum2= overlay[blockTop][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                break;    
            case "down":
                blockDown=(entityDown+entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockDown][blockLeft];
                tileNum2=mapTile[blockDown][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                break; 
            case "left":
                blockLeft=(entityLeft-entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockLeft];
                tileNum2=mapTile[blockDown][blockLeft];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                break; 
            case "right":
                blockRight=(entityRight+entity.speed)/gp.getTileSize();//lấy vị trí block trước mặt
                tileNum1= mapTile[blockTop][blockRight];
                tileNum2=mapTile[blockDown][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                tileNum1= overlay[blockDown][blockLeft];
                tileNum2= overlay[blockDown][blockRight];
                if(tile[tileNum1].collision==true||tile[tileNum2].collision==true){
                    entity.setCollisionOn(true);
                }
                break; 
        }
    }
}
