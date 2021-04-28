import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;
import java.awt.color.*;

public class Game {

    private boolean lost;
    private int bombs;
    private int markedbombs;
    private int rows;
    private int columns;
    private int markedTiles;
    private JLabel bombsremaining;
    private JButton restart;
    private JButton buttons[][];
    private JButton WLButton = new JButton(); 
    JFrame Gameframe;
    private char[][] gamePanel;
    private Random rnd = new Random();

    public Game(JFrame frame, String difficulty) {
        
        markedbombs = 0;
        markedTiles = 0;
        this.Gameframe = frame;
        if (difficulty.contains("0")) {
            rows = 9;
            columns = 9;
            bombs = 10;

        } else if (difficulty.contains("1")) {
            rows = 15;
            columns = 15;
            bombs = 35;

        } else if (difficulty.contains("2")) {
            rows = 19;
            columns = 19;
            bombs = 80;

        } else {
            rows = 9;
            columns = 9;
            bombs = 10;

        }

        lost = false;
        buttons = new JButton[rows][columns];
        restart = new JButton();
        bombsremaining = new JLabel();
        gamePanel = new char[rows][columns];
        calculateBombs();
        calculateNumbers();
        createButtons();

    }

    public void calculateBombs() {

        for (int i = 0; i < bombs; i++) {

            int x = rnd.nextInt(rows);
            int y = rnd.nextInt(columns);

            while (gamePanel[x][y] == 'X') {

                x = rnd.nextInt(rows);
                y = rnd.nextInt(columns);
            }

            gamePanel[x][y] = 'X';
        
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (gamePanel[i][j] != 'X')
                    gamePanel[i][j] = ' ';

            }
        }

        
    }

    public void calculateNumbers() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (gamePanel[i][j] != 'X') {

                    int bombcounter = 0;

                    if (i > 0) {
                        if (j > 0 && gamePanel[i - 1][j - 1] == 'X') {
                            bombcounter++;
                        }
                        if (gamePanel[i - 1][j] == 'X')
                            bombcounter++;
                        if (j < rows - 1 && gamePanel[i - 1][j + 1] == 'X') {
                            bombcounter++;
                        }
                    }

                    if (j > 0 && gamePanel[i][j - 1] == 'X') {
                        bombcounter++;
                    }
                    if (j < rows - 1 && gamePanel[i][j + 1] == 'X') {
                        bombcounter++;
                    }

                    if (i < columns - 1) {
                        if (j > 0 && gamePanel[i + 1][j - 1] == 'X') {
                            bombcounter++;
                        }
                        if (gamePanel[i + 1][j] == 'X')
                            bombcounter++;
                        if (j < rows - 1 && gamePanel[i + 1][j + 1] == 'X') {
                            bombcounter++;
                        }
                    }

                    gamePanel[i][j] = (char)(bombcounter + '0');
                }
            }
        }  
    }

    public void createButtons() {      

        Gameframe.setVisible(false);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j] = new JButton();
                JButton temp = buttons[i][j];

            temp.addMouseListener(new MouseAdapter(){
            
            boolean pressed;

            @Override
            public void mousePressed(MouseEvent e) {
                temp.getModel().setArmed(true);
                temp.getModel().setPressed(true);
                bombsremaining.setBounds(18, 5, 220, 40);
                restart.setBounds(Gameframe.getWidth() - 235, 7 , 200, 30);
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                temp.getModel().setArmed(false);
                temp.getModel().setPressed(false);


                if(markedbombs == 0 & markedTiles == 0){
                    char tile = getTile(temp);
                    while(tile == 'X'){
                        restartEvent();
                        tile = getTile(temp);
                    }
                }

                if (pressed && !lost) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        if(temp.getBackground() == Color.blue && bombs - markedbombs > 0){
                            temp.setBackground(Color.red);
                            markedbombs++; 
                            bombsremaining.setText("Übrige Bomben: "+ Integer.toString(bombs - markedbombs));
                            markedTiles++;
                        }else if(temp.getBackground() == Color.red){
                            temp.setBackground(Color.blue);
                            markedbombs--;
                            bombsremaining.setText("Übrige Bomben: "+ Integer.toString(bombs - markedbombs));
                            markedTiles--;
                        }
                    }
                    else {
                        if(temp.getBackground() != Color.red){
                            char tile = getTile(temp);
                            if(tile != 'X' && temp.getBackground() != Color.gray){
                                if(tile != '0'){
                                    temp.setText(Character.toString(tile));
                                }else{
                                    temp.setText("");
                                }

                                
                                temp.setBackground(Color.gray);
                                markedTiles++;
                            }else if(tile == 'X'){
                                solveGame();
                                lost = true;
                                showLoseScreen();
                            }            
                        }
                    }
                }

               pressed = false;
               bombsremaining.setBounds(18, 5, 220, 40);
               restart.setBounds(Gameframe.getWidth() - 235, 7 , 200, 30);

               if(markedTiles == rows*columns)
                    showWinScreen();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                bombsremaining.setBounds(18, 5, 220, 40);
                restart.setBounds(Gameframe.getWidth() - 235, 7 , 200, 30);
                pressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bombsremaining.setBounds(18, 5, 220, 40);
                restart.setBounds(Gameframe.getWidth() - 235, 7 , 200, 30);
                pressed = true;
            } 
        });
                
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j].setBounds(16 + (i*50), 45 + (j*50) ,50,50);
                buttons[i][j].setBackground(Color.blue);
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setVisible(true);
                buttons[i][j].getModel().setPressed(true);
                Gameframe.add(buttons[i][j]);
            }
        }

        Gameframe.setVisible(true);
        buttons[rows-1][columns-1].setBounds(16 + ((rows-1)*50), 45 + ((columns-1)*50) ,50,50);
        buttons[rows-1][columns-1].setEnabled(true);

        bombsremaining.setBackground(Color.red);
        bombsremaining.setForeground(Color.black);
        java.awt.Font font = new java.awt.Font("Consolas", java.awt.Font.PLAIN, 20);
        bombsremaining.setFont(font);
        bombsremaining.setText("Übrige Bomben: "+ Integer.toString(bombs - markedbombs));
        bombsremaining.setBounds(18, 5, 220, 40);
        Gameframe.add(bombsremaining);
        bombsremaining.setEnabled(true);
        bombsremaining.setVisible(true);

        restart.setBackground(Color.lightGray);
        restart.setForeground(Color.black);
        restart.setText("Neustart");
        restart.setBounds(Gameframe.getWidth() - 235, 7 , 200, 30);
        Gameframe.add(restart);
        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                restartEvent();
            }

        });
        restart.setEnabled(true);
        restart.setVisible(true);
    }

    public void solveGame(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(gamePanel[i][j] == 'X'){

                 buttons[i][j].setBackground(Color.red);
                    
                }
            }
        }
    }

    public void showWinScreen() {

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j].setVisible(false);
            }
        }

        java.awt.Font font = new java.awt.Font("Algerian", java.awt.Font.PLAIN, 20);

        WLButton.setFont(font);
        WLButton.setBounds(0, 45, Gameframe.getWidth(), Gameframe.getHeight()- 45);
        WLButton.setText("You did an amazing job! kappa xD ");
        WLButton.setEnabled(false);
        Gameframe.add(WLButton);
        WLButton.setVisible(true);
    }

    public void showLoseScreen(){
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j].setVisible(false);
            }
        }

        java.awt.Font font = new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 25);

        WLButton.setFont(font);
        WLButton.setText("You are a loser! ε> ");
        WLButton.setBounds(0, 45, Gameframe.getWidth(), Gameframe.getHeight()- 45);
        WLButton.setEnabled(false);
        Gameframe.add(WLButton);
        WLButton.setVisible(true);
    }

    public void restartEvent() {

        markedTiles = 0;
        markedbombs = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                gamePanel[i][j] = ' ';
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.blue);
                buttons[i][j].setVisible(true);
            }
        }
        WLButton.setVisible(false);
        lost = false;
        calculateBombs();
        calculateNumbers();
        
    }

    public char getTile(JButton temp){

        char tile = '0'; 

        for(int i = 0; i < rows; i++){
            if((int)temp.getLocation().getX() == ((50*i)+16)){
                for(int j = 0; j < columns; j++){
                    if((int)temp.getLocation().getY() == ((50*j)+45)){
                        tile = gamePanel[i][j];         
                    }
                }
            }    
        }
        return tile;

    }
}
