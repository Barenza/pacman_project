package game.objects.creatures;

import game.Game;
import game.objects.GameObject;

import java.awt.*;

public abstract class Creature extends GameObject {

    protected final Game game;

    protected double centerX;

    protected double centerY;

    protected final double radius;

    protected final double speed;

    protected Color color;


    public Creature(Game game, double centerX, double centerY, double radius, double speed, Color color) {
        this.game = game;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.speed = speed;
        this.color = color;
    }


    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }
}
