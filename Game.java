import java.util.*;
import java.awt.*;
import java.math.*;

public class Game{

    private int bombs;
    private int rows;
    private int columns;
    public static Game instance;
    private char[][] gamePanel;

    public Game(String difficulty){

        instance = this;
        if(difficulty == "Leicht"){
            rows = 9;
            columns = 9;
            bombs = 10;
        }else if(difficulty == "Mittel"){
            rows = 16;
            columns = 16;
            bombs = 40;
        }else if (difficulty == "Schwer"){
            rows = 16;
            columns = 30;
            bombs = 99;
        } 

        gamepanel = new char[rows][columns];
        
    }

    public void calculateBombs(){
    
        for(int i = 0; i < bombs; i++){

            int x = Math.random()*rows;
            int y = Math.random()*columns;
            
            while(gamePanel[x][y] == 'X'){
    
                x = Math.random()*rows;
                y = Math.random()*columns;
            }
    
            gamePanel[x][y] = 'X';
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                if(gamePanel[i][j] != 'X')
                    gamePanel[i][j] = '0';

            }
        }

    }

    public void calculateNumbers(){



        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                int bombcounter = 0;

                if(gamePanel[i][j] != 'X'){

                    if(i>0){
                        if(j>0){
                            if(gamepanel[i-1][j-1] == 'X')
                                bombcounter++;
                        }

                        if(gamepanel[i-1][j] == 'X')
                            bombcounter++;

                        if(j< rows-1){
                            if(gamepanel[i-1][j+1] == 'X')
                                bombcounter++;
                        }
                    }

                    if(j>0){
                        if(gamepanel[i][j-1] == 'X')
                            bombcounter++;
                    }
                    if(j< rows-1){
                        if(gamepanel[i][j+1] == 'X')
                            bombcounter++;
                    }

                    if(i < columns-1){
                        if(j>0){
                            if(gamepanel[i+1][j-1] == 'X')
                                bombcounter++;
                        }

                        if(gamepanel[i+1][j] == 'X')
                            bombcounter++;

                        if(j< rows-1){
                            if(gamepanel[i+1][j+1] == 'X')
                                bombcounter++;
                        }

                    }

                }
            }
        }

    }

    public void createButtons(){

    }

    public void getClickEvent(){

    }

    public void showPanel(){

    }

    public void showRemainingBombs(){

    }

    public void showWinScreen(){

    }

    public void showLoseScreen(){

    }

    public void restartGame(){

    }
}