/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.map;
import com.map.business.iMap;
import com.map.business.tileType;
import java.util.Random;

/**
 *
 * @author Jake
 */
public class Map implements iMap {
    private tileType map [][];
    public Map(){
        map = new tileType [5][5];
    }
    public boolean setMapSize(int x, int y){
        this.map = new tileType [x][y];
        return true;
    }
    public void generate(){
        Random randomGenerator = new Random();
        int randomX = randomGenerator.nextInt(map.length);
        int randomY = randomGenerator.nextInt(map[0].length);
        map[randomX][randomY] = tileType.TREASURE;
        
        for(int i = 0; i<= map.length - 1; i++){
            for(int j = 0; j<= map[0].length - 1; j++){
                map[i][j] = tileType.UNKNOWN;
            }
        }
        recursiveDivisor();
    }
    public tileType getTileType(int x, int y){
        return tileType.UNKNOWN;
    }
    /* Recursive Divisor Algorithm for Maze Generation */
    public void recursiveDivisor(){
        /* Test */
    }
}
