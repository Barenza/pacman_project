package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;
import java.util.Random;

public class RandomEnemy extends Enemy {
    private final Random random;

    public RandomEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
        random = new Random();
    }

    @Override
    protected void tickTarget() {
        if ((int) centerX == targetX && (int) centerY == targetY) {
            int nextTargetX = random.nextInt(game.getMap().getWidth());
            int nextTargetY = random.nextInt(game.getMap().getHeight());

            if (!(game.getMap().isFree(nextTargetX, nextTargetY))) {
                return;
            }

            targetX = nextTargetX;
            targetY = nextTargetY;
        }
    }
}