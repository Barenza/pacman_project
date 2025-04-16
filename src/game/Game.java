package game;

import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {

    private final Display display;

    private final GameMap map;

    private final Player player;

    public Game() {
        super("Game");

        display = new Display(this);
        map = new GameMap(40);
        player = new Player(this, 13.5, 10.5, 0.375, 0.07);
        addKeyListener(player);

        setSize(1096, 760);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        startGameLoop();

    }

    private void startGameLoop() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            tick();
            display.repaint();
            }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);

        }


    private void tick() {
        player.tick();
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

