package tiles;

import java.awt.*;

public class Dot extends Tile {

    private final double radius;

    public Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public Dot(int x, int y) {
        this(x, y, 0.125);
    }


    @Override
    public void render(Graphics2D g, int tileSize) {

    }
}
