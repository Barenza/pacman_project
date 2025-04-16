package game.objects.creatures;

import game.Game;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Player extends Creature {

    public Player(Game game, double centerX, double centerY, double radius, double speed) {
        super(game, centerX, centerY, radius, speed, Color.YELLOW);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {

        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radius * 2.0;

        g.setColor(color);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }
}
