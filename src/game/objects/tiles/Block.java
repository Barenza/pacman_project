package game.objects.tiles;

import java.awt.*;

public class Block extends Tile {

    public Block(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        g.setColor(Color.BLUE);
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}
