package gfxtoys;

import pixel.App;
import pixel.Scene;
import java.awt.Font;

public class Main {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final Font MENU_FONT = Font.decode ("Arial-BOLD-24");
	public static final Font PROMPT_FONT = Font.decode ("Arial-BOLD-16");

	public static void main (String[] args) throws Exception {

		Scene init = new Menu ();
		App app = new App (init, "gfx-toys", WIDTH, HEIGHT);
		app.start ();
	}
}