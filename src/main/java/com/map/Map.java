/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.map;
import com.map.business.MapCreator;
import com.map.business.tileType;

/**
 *
 * @author Jake
 */
public class Map extends MapCreator {
    public Map(){
        super.mapName = "Random Map";
        super.map = new tileType [5][5];
    }
    @Override
    public void generate(){
        for(int i = 0; i<= map.length - 1; i++){
            for (int j = 0; j<= map[0].length - 1; j++){
                int b = randomGenerator.nextInt(100);
                if(b%2 == 0){  map[i][j] = tileType.GRASS; } else{   map[i][j] = tileType.SEA; }
            }
        }
        setWinningTile(1);
    }
    @Override
    public boolean setWinningTile(int n){
        int x,y;
        if(n > 0){
            x = randomGenerator.nextInt(map.length);
            y = randomGenerator.nextInt(map[0].length);
            do{
               for(int i = 0; i<= map.length - 1; i++){
                   for(int j = 0; j<= map[0].length - 1; j++){
                       map[x][y] = tileType.TREASURE;
                   }
               }
               n--;
            }while(n > 0);
            return true;
        }
        return false;
    }
}
