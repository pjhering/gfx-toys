package gfxtoys;

import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.lang.Math.random;

public class GameOfLife extends Scene {

	private boolean showMenu = false;
    private final int CELL_WIDTH = 2;
    private final int CELL_HEIGHT = 2;
    private final int ROWS = HEIGHT / CELL_HEIGHT;
    private final int COLS = WIDTH / CELL_WIDTH;

    private int[][] back, front;
    long time = 0;

    public void update (SceneManager mgr, long elapsedMillis) {
    	if (showMenu) {
    		mgr.pop ();
    	}
        else {
            time += elapsedMillis;
            while (time > 100) {
                time -= 100;
                nextGeneration ();
            }
        }
    }

    private void nextGeneration () {
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                int n = liveNeighbors (x, y);
                if (front[x][y] == 1 && (n < 2 || n > 3)) {
                    back[x][y] = 0;
                }
                else if (front[x][y] == 0 && n == 3) {
                    back[x][y] = 1;
                }
            }
        }
        flipBackToFront ();
    }

    private int liveNeighbors (int x, int y) {
        int tally = 0;
        for (int a = -1; a <= 1; a++) {
            int ax = (x + a + COLS) % COLS;
            for (int b = -1; b <= 1; b++) {
                int by = (y + b + ROWS) % ROWS;
                if (ax == x && by == y) continue;
                else if (front[ax][by] == 1) {
                    tally += 1;
                }
            }
        }
        return tally;
    }

    private void flipBackToFront () {
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                front[x][y] = back[x][y];
            }
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {
    	if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {
    		showMenu = true;
    	}
        else if (e.getKeyCode () == KeyEvent.VK_ENTER) {
            activate ();
        }
    }

    public void render (Graphics g) {
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                g.setColor (front[x][y] == 1 ? Color.WHITE : Color.BLACK);
                g.fillRect (x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

    public void activate () {
    	this.showMenu = false;
        back = new int[COLS][ROWS];
        front = new int[COLS][ROWS];
        time = 0;

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                front[x][y] = random () > 0.8 ? 1 : 0;
            }
        }
    }

    public void deactivate () {

    }

    public void dispose () {

    }
}