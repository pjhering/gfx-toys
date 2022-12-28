package gfxtoys;

import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.event.KeyEvent;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;


public class RecursiveTree extends Scene {

	private boolean showMenu = false;
    private List<Line2D> lines;

    private void branch (double x1, double y1, double length, double angle) {
        double x2 = x1 + (length * cos (angle));
        double y2 = y1 + (length * sin (angle));
        lines.add (new Line2D.Double (x1, y1, x2, y2));

        if (length > 5) {
            branch (x2, y2, length * .67, angle + 0.5);
            branch (x2, y2, length * .67, angle - 0.7);
        }
    }

    public void update (SceneManager mgr, long elapsedMillis) {
    	if (showMenu) {
    		mgr.pop ();
    	}
    }

    @Override
    public void keyPressed (KeyEvent e) {
    	if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {
    		showMenu = true;
    	}
    }

    public void render (Graphics g) {
    	g.setColor (Color.BLACK);
    	g.fillRect (0, 0, WIDTH, HEIGHT);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor (Color.WHITE);
        for (Line2D line : lines) {
            g2d.draw (line);
        }
    }

    public void activate () {
    	this.showMenu = false;
        this.lines = new ArrayList<> ();
        branch (320.0, 480.0, 150.0, -PI/2);
    }


    public void deactivate () {

    }

    public void dispose () {

    }
}