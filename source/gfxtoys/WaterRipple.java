package gfxtoys;

import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class WaterRipple extends Scene {

	private boolean showMenu = false;
    private int cols, rows;
    private double[][] current, previous;
    private final double damping = 0.999;

    public void update (SceneManager mgr, long elapsedMillis) {
    	if (showMenu) {
    		mgr.pop ();
    	}
        else {

            for (int i = 0; i < 15; i++) {
                int rx = (int) min (cols - 1, round (random () * cols));
                int ry = (int) min (rows - 1, round (random () * rows));
                previous[rx][ry] = 255;
            }

            for (int x = 1; x < cols - 1; x++) {
                for (int y = 1; y < rows - 1; y++) {
                    double sum = (previous[x - 1][y] +
                               previous[x + 1][y] +
                               previous[x][y - 1] +
                               previous[x][y + 1]) / 2;
                    current[x][y] = (sum - current[x][y]) * damping;
                }
            }
            double[][] temp = previous;
            previous = current;
            current = temp;
        }
    }

    public void render (Graphics g) {
        g.setColor (Color.BLACK);
        g.fillRect (0, 0, WIDTH, HEIGHT);

        for (int x = 1; x < cols - 1; x++) {
            for (int y = 1; y < rows - 1; y++) {
                int c = (int) round (min (255, max (0, current[x][y])));
                // System.out.println (c);
                g.setColor (new Color (c, c, c));
                g.fillRect (x, y, 1, 1);
            }
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {
    	if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {
    		showMenu = true;
    	}
    }

    public void activate () {
    	this.showMenu = false;
        cols = WIDTH;
        rows = HEIGHT;
        current = new double[cols][rows];
        previous = new double[cols][rows];
    }

    public void deactivate () {
    }

    public void dispose () {

    }
}