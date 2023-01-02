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
        "L-System Tree",
        "Recursive Tree", 
        "Snowflakes", 
        "Water Ripple",
        "Worley Noise"
	};
	private int selectedItem = 0;


    public void update (SceneManager mgr, long elapsedMillis) {
    	switch (selectedItem) {
    		case 1: mgr.push (new Boids ()); break;
            case 2: mgr.push (new FallingRain ()); break;
            case 3: mgr.push (new GameOfLife ()); break;
            case 4: mgr.push (new LSystemTree ()); break;
            case 5: mgr.push (new RecursiveTree ()); break;
            case 6: mgr.push (new Snowflakes ()); break;
            case 7: mgr.push (new WaterRipple ()); break;
            case 8: mgr.push (new WorleyNoise ()); break;
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
    	switch (e.getKeyCode ()) {
	    	case KeyEvent.VK_1: selectedItem = 1; break; // Boids
            case KeyEvent.VK_2: selectedItem = 2; break; // Falling Rain
            case KeyEvent.VK_3: selectedItem = 3; break; // Game of Life
            case KeyEvent.VK_4: selectedItem = 4; break; // L-System Tree
            case KeyEvent.VK_5: selectedItem = 5; break; // Recursive Tree
            case KeyEvent.VK_6: selectedItem = 6; break; // Snowflakes
            case KeyEvent.VK_7: selectedItem = 7; break; // Worley Noise
            case KeyEvent.VK_8: selectedItem = 8; break; // Worley Noise
    	}
    }

    public void activate () {
    	selectedItem = 0;
    }

    public void deactivate () {

    }

    public void dispose () {

    }
}