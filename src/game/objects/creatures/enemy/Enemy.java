package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Creature;
import game.objects.creatures.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public abstract class Enemy extends Creature {
    protected Player player;

    protected int targetX;
    protected int targetY;

    public Enemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, centerX, centerY, radius, speed, color);
        this.player = player;
        targetX = (int) centerX;
        targetY = (int) centerY;
    }

    private Node shortestDirectionTo(int goalX, int goalY) {
        Node startNode = new Node((int) centerX, (int) centerY, null, 0, goalX, goalY);

        Queue<Node> queue = new PriorityQueue<>(Node::compareTo);
        queue.add(startNode);

        Set<Node> visited = new HashSet<>();

        Node currentNode;
        while ((currentNode = queue.poll()) != null) {
            if (visited.contains(currentNode)) {
                continue;
            }

            if (currentNode.getX() == goalX && currentNode.getY() == goalY) {
                return currentNode.initialDirection();
            }

            visited.add(currentNode);
            queue.addAll(currentNode.neighbors(game.getMap(), goalX, goalY));
        }

        return null;
    }

    protected abstract void tickTarget();

    @Override
    public void tickPreferredDirection() {
        tickTarget();

        Node aim = shortestDirectionTo(targetX, targetY);
        if (aim != null) {
            preferredDirectionX = Integer.signum(aim.getX() - (int) centerX);
            preferredDirectionY = Integer.signum(aim.getY() - (int) centerY);
        }
    }

    private void tickPlayerCollision() {
        double dx = player.getCenterX() - centerX;
        double dy = player.getCenterY() - centerY;
        double r = player.getRadius() + radius;

        if (dx * dx + dy * dy < r * r) {
            game.lose();
        }
    }

    @Override
    public void tick() {
        super.tick();
        tickPlayerCollision();
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double sizeOnScreen = radiusOnScreen * 2.0;

        g.setColor(color);
        g.fill(new Rectangle2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, sizeOnScreen, sizeOnScreen));

        renderEyes(g, centerXOnScreen, centerYOnScreen, radiusOnScreen, targetX + 0.5, targetY + 0.5);
    }
}