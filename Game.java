import java.util.*;
import java.awt.*;

public class Game {

    private int bombs;
    private int rows;
    private int columns;
    public static Game instance;
    private char[][] gamePanel;
    private Random rnd = new Random();

    public Game(String difficulty) {

        instance = this;
        if (difficulty.equals("Leicht")) {
            rows = 9;
            columns = 9;
            bombs = 10;
        } else if (difficulty.equals("Mittel")) {
            rows = 16;
            columns = 16;
            bombs = 40;
        } else if (difficulty.equals("Schwer")) {
            rows = 16;
            columns = 30;
            bombs = 99;
        }

        gamePanel = new char[rows][columns];

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
                    gamePanel[i][j] = '0';

            }
        }

    }

    public void calculateNumbers() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                int bombcounter = 0;

                if (gamePanel[i][j] != 'X') {

                    if (i > 0) {
                        if (j > 0) {
                            if (gamePanel[i - 1][j - 1] == 'X')
                                bombcounter++;
                        }

                        if (gamePanel[i - 1][j] == 'X')
                            bombcounter++;

                        if (j < rows - 1) {
                            if (gamePanel[i - 1][j + 1] == 'X')
                                bombcounter++;
                        }
                    }

                    if (j > 0) {
                        if (gamePanel[i][j - 1] == 'X')
                            bombcounter++;
                    }
                    if (j < rows - 1) {
                        if (gamePanel[i][j + 1] == 'X')
                            bombcounter++;
                    }

                    if (i < columns - 1) {
                        if (j > 0) {
                            if (gamePanel[i + 1][j - 1] == 'X')
                                bombcounter++;
                        }

                        if (gamePanel[i + 1][j] == 'X')
                            bombcounter++;

                        if (j < rows - 1) {
                            if (gamePanel[i + 1][j + 1] == 'X')
                                bombcounter++;
                        }

                    }

                    gamePanel[i][j] = (char) bombcounter;

                }
            }
        }

    }

    public void createButtons() {
        // create the necessary amaount of buttons in order
    }

    public void getClickEvent() {
        // get the click event of a button and do actions, left click = activate(use
        // showPanel), right click = marker
    }

    public void showPanel() {
        // show whats beneath a button -> bomb = lost game
    }

    public void showRemainingBombs() {
        // Counter of how many bombs are remaining
    }

    public void showWinScreen() {
        // Show the WinScreen
    }

    public void showLoseScreen() {
        // show the LoseScreen
    }

    public void restartGame() {
        // Restart the game(reinitalize the bombs and other fields, make buttons gray
        // again)
    }
}