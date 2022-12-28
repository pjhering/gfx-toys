package gfxtoys;

import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class FallingRain extends Scene {

	private boolean showMenu = false;

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

    	g.setColor (Color.RED);
    	g.setFont (MENU_FONT);
    	g.drawString ("Falling Rain", 25, 100);
    }

    public void activate () {
    	this.showMenu = false;
    }

    public void deactivate () {

    }

    public void dispose () {

    }
}