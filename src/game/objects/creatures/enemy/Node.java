package game.objects.creatures.enemy;

import game.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {
    private final int x;
    private final int y;
    private final Node previous;

    private final int distanceFromStart;
    private final int minDistanceToGoal;

    public Node(int x, int y, Node previous, int distanceFromStart, int goalX, int goalY) {
        this.x = x;
        this.y = y;
        this.previous = previous;
        this.distanceFromStart = distanceFromStart;
        minDistanceToGoal = Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    public List<Node> neighbors(GameMap map, int goalX, int goalY) {
        List<Node> neighbors = new ArrayList<>();

        if (map.isFree(x - 1, y)) {
            neighbors.add(new Node(x - 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x + 1, y)) {
            neighbors.add(new Node(x + 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x, y - 1)) {
            neighbors.add(new Node(x, y - 1, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x, y + 1)) {
            neighbors.add(new Node(x, y + 1, this, distanceFromStart + 1, goalX, goalY));
        }

        return neighbors;
    }

    public Node initialDirection() {
        if (distanceFromStart <= 1) {
            return this;
        }

        return previous.initialDirection();
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(distanceFromStart + minDistanceToGoal, other.distanceFromStart + other.minDistanceToGoal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}