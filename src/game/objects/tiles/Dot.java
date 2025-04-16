package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Dot extends Tile {

    private final double radius;

    protected Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public Dot(int x, int y) {
        this(x, y, 0.125);
    }


    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = getCenterX() * tileSize;
        double centerYOnScreen = getCenterY() * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2;
        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }

    public double getCenterX() {
        return x + 0.5;
    }

    public double getCenterY() {
        return y + 0.5;
    }
}
