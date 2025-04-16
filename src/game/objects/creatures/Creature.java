package game.objects.creatures;

import game.Game;
import game.GameMap;
import game.objects.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Creature extends GameObject {
    protected final Game game;
    protected double centerX;
    protected double centerY;
    protected final double radius;
    protected final double speed;
    protected Color color;

    protected int preferredDirectionX;
    protected int preferredDirectionY;
    protected int movingDirectionX;
    protected int movingDirectionY;

    private final double initialX;
    private final double initialY;

    public Creature(Game game, double centerX, double centerY, double radius, double speed, Color color) {
        this.game = game;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.speed = speed;
        this.color = color;

        initialX = centerX;
        initialY = centerY;
    }


    public void reset() {
        centerX = initialX;
        centerY = initialY;
        preferredDirectionX = 0;
        preferredDirectionY = 0;
        movingDirectionX = 0;
        movingDirectionY = 0;
    }

    private void tickMovingDirection() {
        if (movingDirectionX == 0 && movingDirectionY == 0) {
            movingDirectionX = preferredDirectionX;
            movingDirectionY = preferredDirectionY;
        } else if (movingDirectionX != 0 && preferredDirectionX != 0) {
            movingDirectionX = preferredDirectionX;
        } else if (movingDirectionY != 0 && preferredDirectionY != 0) {
            movingDirectionY = preferredDirectionY;
        }
    }

    private void snapX() {
        centerX = (int) centerX + 0.5;
        movingDirectionX = 0;
    }

    private void snapY() {
        centerY = (int) centerY + 0.5;
        movingDirectionY = 0;
    }

    private void tickWallCollisions() {
        GameMap map = game.getMap();

        if (movingDirectionX == 1 && !map.isFree((int) (centerX + 0.5), (int) centerY)
                || movingDirectionX == -1 && !map.isFree((int) (centerX - 0.5), (int) centerY)) {
            snapX();
        } else if (movingDirectionY == 1 && !map.isFree((int) centerX, (int) (centerY + 0.5))
                || movingDirectionY == -1 && !map.isFree((int) centerX, (int) (centerY - 0.5))) {
            snapY();
        }
    }

    protected abstract void tickPreferredDirection();

    private void tickTurn(boolean crossedCenterX, boolean crossedCenterY) {
        boolean turnXToY = crossedCenterX && movingDirectionX != 0 && preferredDirectionY != 0 && game.getMap().isFree((int) centerX, (int) (centerY + preferredDirectionY));
        boolean turnYToX = crossedCenterY && movingDirectionY != 0 && preferredDirectionX != 0 && game.getMap().isFree((int) (centerX + preferredDirectionX), (int) centerY);
        if (turnXToY) {
            snapX();
            movingDirectionY = preferredDirectionY;
        } else if (turnYToX) {
            snapY();
            movingDirectionX = preferredDirectionX;
        }
    }

    public void tick() {
        tickMovingDirection();

        double newX = centerX + movingDirectionX * speed;
        double newY = centerY + movingDirectionY * speed;

        boolean crossedCenterX = Math.abs((centerX - 0.5) % 1.0 - (newX - 0.5) % 1.0) > 0.5;
        boolean crossedCenterY = Math.abs((centerY - 0.5) % 1.0 - (newY - 0.5) % 1.0) > 0.5;

        centerX = newX;
        centerY = newY;

        if (crossedCenterX || crossedCenterY) {
            tickPreferredDirection();
            tickTurn(crossedCenterX, crossedCenterY);
        }
        tickWallCollisions();
    }

    protected void renderEyes(Graphics2D g, double centerXOnScreen, double centerYOnScreen, double radiusOnScreen, double aimX, double aimY) {
        double eyeRad = radiusOnScreen / 3.0;
        double eyeCenterY = centerYOnScreen - radiusOnScreen / 3.0;
        double leftEyeCenterX = centerXOnScreen - radiusOnScreen / 2.0;
        double rightEyeCenterX = centerXOnScreen + radiusOnScreen / 2.0;

        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(leftEyeCenterX - eyeRad, eyeCenterY - eyeRad, eyeRad * 2.0, eyeRad * 2.0));
        g.fill(new Ellipse2D.Double(rightEyeCenterX - eyeRad, eyeCenterY - eyeRad, eyeRad * 2.0, eyeRad * 2.0));

        double dx = aimX - centerX;
        double dy = aimY - centerY;
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            dx /= length;
            dy /= length;
        }

        double pupilY = eyeCenterY - eyeRad / 2.0 + dy * 0.4 * eyeRad;

        g.setColor(Color.BLACK);
        g.fill(new Ellipse2D.Double(leftEyeCenterX + dx * 0.4 * eyeRad - eyeRad / 2.0, pupilY, eyeRad, eyeRad));
        g.fill(new Ellipse2D.Double(rightEyeCenterX + dx * 0.4 * eyeRad - eyeRad / 2.0, pupilY, eyeRad, eyeRad));
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

    public int getMovingDirectionX() {
        return movingDirectionX;
    }

    public int getMovingDirectionY() {
        return movingDirectionY;
    }
}