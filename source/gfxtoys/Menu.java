package gfxtoys;

import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.PROMPT_FONT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu extends Scene {

	private final String[] toys = new String[] {
		"Boids", 
        "Falling Rain",
        "Game of Life", 
        "Hilbert Curve", 
        "L-System Tree",
        "Recursive Tree", 
        "Snowflakes", 
        "Water Ripple",
        "Worley Noise"
	};
	private int selectedItem = 0;


    public void update (SceneManager mgr, long elapsedMillis) {
    	switch (selectedItem) {
    		case KeyEvent.VK_1: mgr.push (new Boids ()); break;
            case KeyEvent.VK_2: mgr.push (new FallingRain ()); break;
            case KeyEvent.VK_3: mgr.push (new GameOfLife ()); break;
            case KeyEvent.VK_4: mgr.push (new HilbertCurve ()); break;
            case KeyEvent.VK_5: mgr.push (new LSystemTree ()); break;
            case KeyEvent.VK_6: mgr.push (new RecursiveTree ()); break;
            case KeyEvent.VK_7: mgr.push (new Snowflakes ()); break;
            case KeyEvent.VK_8: mgr.push (new WaterRipple ()); break;
            case KeyEvent.VK_9: mgr.push (new WorleyNoise ()); break;
    	}
    }

    public void render (Graphics g) {
    	g.setColor (Color.BLACK);
    	g.fillRect (0, 0, WIDTH, HEIGHT);

    	g.setColor (Color.YELLOW);
    	g.setFont (PROMPT_FONT);
    	g.drawString ("Press a number to select a toy.", 25, 40);
    	g.drawString ("To return to this menu press Escape.", 25, 60);

    	g.setColor (Color.RED);
    	g.setFont (MENU_FONT);
    	for (int i = 0; i < toys.length; i++) {
    		g.drawString ((i + 1) + ": " + toys[i], 25, 100 + (i * 30));
    	}
    }

    @Override
    public void keyPressed (KeyEvent e) {
        selectedItem = e.getKeyCode ();
    }

    public void activate () {
    	selectedItem = 0;
    }

    public void deactivate () {

    }

    public void dispose () {

    }
}