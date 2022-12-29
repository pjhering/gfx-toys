package gfxtoys;

import static gfxtoys.Main.MENU_FONT;
import static gfxtoys.Main.WIDTH;
import static gfxtoys.Main.HEIGHT;
import pixel.Scene;
import pixel.SceneManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

public class Snowflakes extends Scene {

    static class Flake {
        private static final Color GRAY1 = new Color (192, 192, 192);
        private static final Color GRAY2 = new Color (208, 208, 208);
        private static final Color GRAY3 = new Color (224, 224, 224);
        private static final Color GRAY4 = new Color (240, 240, 240);
        private static final Color GRAY5 = new Color (255, 255, 255);

        double x;
        double y;
        double diameter;
        double deltaY;
        Color color;

        Flake (double x, double y, double diameter, double deltaY, Color color) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.deltaY = deltaY;
            this.color = color;
        }

        static Flake flake1 () {
            return new Flake (random () * WIDTH, random () * HEIGHT, 2.0, 1.0, GRAY1);
        }

        static Flake flake2 () {
            return new Flake (random () * WIDTH, random () * HEIGHT, 4.0, 1.5, GRAY2);
        }

        static Flake flake3 () {
            return new Flake (random () * WIDTH, random () * HEIGHT, 6.0, 2.0, GRAY3);
        }

        static Flake flake4 () {
            return new Flake (random () * WIDTH, random () * HEIGHT, 8.0, 3.0, GRAY4);
        }

        static Flake flake5 () {
            return new Flake (random () * WIDTH, random () * HEIGHT, 10.0, 4.0, GRAY5);
        }

        void update () {
            this.y += deltaY;
            if (this.y > HEIGHT) {
                this.x = random () * WIDTH;
                this.y = 0;
            }
        }

        void draw (Graphics g) {
            g.setColor (this.color);
            g.fillOval ((int)round(x), (int)round(y), (int)round(diameter), (int)round(diameter));
        }
    }

    private boolean showMenu = false;
    private List<Flake> snowflakes;

    public void update (SceneManager mgr, long elapsedMillis) {
        if (showMenu) {
            mgr.pop ();
        }
        for (Flake flake : snowflakes) {
            flake.update ();
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {
            showMenu = true;
        }
    }

    public void render (Graphics g) {
        g.setColor (Color.DARK_GRAY);
        g.fillRect (0, 0, WIDTH, HEIGHT);
        for (Flake flake : snowflakes) {
            flake.draw (g);
        }
    }

    public void activate () {
        this.showMenu = false;
        this.snowflakes = new ArrayList<> ();
        for (int i = 0; i < 40; i++) {
            snowflakes.add (Flake.flake1 ());
            snowflakes.add (Flake.flake2 ());
            snowflakes.add (Flake.flake3 ());
            snowflakes.add (Flake.flake4 ());
            snowflakes.add (Flake.flake5 ());
        }
    }

    public void deactivate () {

    }

    public void dispose () {

    }
}