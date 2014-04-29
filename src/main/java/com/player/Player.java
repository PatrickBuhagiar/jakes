/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.player;
import com.map.Map;
import com.map.TableMapRender;
import com.map.business.tileType;
import com.pest.suite.Game;
import com.position.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Object;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
public class Player {
    private int id;
    private Position currentPosition;
    private Map displayMap;
    private Game currentGame;
    private Position startPosition;
    private TableMapRender tmr;
    
    public Player(int ID, Game game) throws FileNotFoundException{
        id = ID;
        currentGame = game;
        startPosition = generateStart();
        currentPosition = startPosition;
        displayMap = new Map();
        displayMap.setMapSize(currentGame.getSize(), currentGame.getSize());
        displayMap.setUnknown();
        uncoverTile(currentPosition.getX(),currentPosition.getY());
    }
    
    public void ResetPosition(){
        currentPosition = startPosition;
    }
    
    private Position generateStart(){
        Random randomGenerator = new Random();
        
        int X=0,Y=0;
        
        do{            
            X = randomGenerator.nextInt(currentGame.getSize() - 1);
            Y = randomGenerator.nextInt(currentGame.getSize() - 1);
        }while(currentGame.getMap().getTileType(X, Y) != tileType.GRASS);
        Position currentPosition = new Position();
        currentPosition.setPosition(X, Y);
        return currentPosition;
    }
    public boolean movePlayer(char c) throws FileNotFoundException{
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        boolean r = false;
            switch(c){
                case 'w':
                    if(!currentPosition.OutOfBounds(x,y-1, currentGame)){
                        currentPosition.setPosition(x,y-1);
                        r = true;
                        uncoverTile(currentPosition.getX(),currentPosition.getY());
                    } else {
                     System.out.println("Out of Bounds Please Re-Enter");
                        r = false;
                    }
                    break;
                case 'a':
                    if(!currentPosition.OutOfBounds(x-1,y, currentGame)){
                        currentPosition.setPosition(x-1,y);
                        uncoverTile(x-1,y);
                        r = true;
                    } else {
                        System.out.println("Out of Bounds Please Re-Enter");
                        r = false;
                    }
                    break;
                case 's':
                    if(!currentPosition.OutOfBounds(x,y+1, currentGame)){
                        currentPosition.setPosition(x,y+1);
                        uncoverTile(x,y+1);
                        r = true;
                    } else {
                        System.out.println("Out of Bounds Please Re-Enter");
                        r = false;
                    }
                    break;
                case 'd':
                    if(!currentPosition.OutOfBounds(x+1,y, currentGame)){
                        currentPosition.setPosition(x+1,y);
                        uncoverTile(x+1,y);
                        r = true;
                    } else {
                        System.out.println("Out of Bounds Please Re-Enter");
                        r = false;
                    }
                    break;
            }
            return r;
    }
    
    public int getID(){
        return id;
    }
    
    public void uncoverTile(int x, int y) throws FileNotFoundException{
        if(!currentPosition.OutOfBounds(x,y, currentGame)){
            displayMap.setTileType(x, y, currentGame.getMap().getTileType(x, y));
        }
        getRender();
    }
    
    public char checkStatus(){
        tileType currentTileValue = 
        currentGame.getMap().getTileType(currentPosition.getX(), currentPosition.getY());
        char result;
        switch(currentTileValue){
            case SEA:
                result = 'L';
                break;
            case TREASURE:
                result = 'W';
                break;
            case GRASS:
                result = 'C';
                break;
            default:
                result = 'C';
        }
        return result;
    }
    
    public void getRender() throws FileNotFoundException{
        tmr = new TableMapRender(displayMap.getMap(),"","");
        String code = tmr.generateCode();
        for(int i = 0; i<= displayMap.getMap().length - 1; i++){
            for(int j = 0; j<= displayMap.getMap()[0].length - 1; j++){
                // System.out.println(displayMap.getMap()[i][j].name());
            }
        }
        System.out.println(code);
        try {
            String file = "Player " + this.getID() + ".html";
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(code);
            writer.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

