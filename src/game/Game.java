package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private final Display display;

    private final GameMap map;

    private final Player player;

    public Game() {
        super("Game");

        display = new Display(this);
        map = new GameMap(40);
        player = new Player(this, 13.5, 10.5, 0.375, 0.07);

        setSize(1080, 720);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        map.render(g2, map.getTileSize());
        player.render(g2, map.getTileSize());
    }

    public static void main(String[] args) {
        new Game();
    }

}

