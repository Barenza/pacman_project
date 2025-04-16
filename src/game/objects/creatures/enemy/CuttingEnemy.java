package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

public class CuttingEnemy extends Enemy {
    public CuttingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();
        int vx = player.getMovingDirectionX();
        int vy = player.getMovingDirectionY();

        if (vx != 0) {
            while (game.getMap().isFree(targetX + vx, targetY)) {
                targetX += vx;
            }
        } else if (vy != 0) {
            while (game.getMap().isFree(targetX, targetY + vy)) {
                targetY += vy;
            }
        }
    }
}