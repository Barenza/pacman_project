package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

public class ChasingEnemy extends Enemy {
    public ChasingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();
    }
}