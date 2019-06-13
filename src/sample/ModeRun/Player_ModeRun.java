package sample.ModeRun;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Config.OtherPlayerConfig;
import sample.utils.Vector2D;

public class Player_ModeRun extends Sprite {
    private int player_Health = 3;

    public Player_ModeRun(Group group, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(group, location, velocity, acceleration, width, height, true, OtherPlayerConfig.SPRITE_PLAYER_MAX_FORCE, OtherPlayerConfig.SPRITE_PLAYER_MAX_SPEED, OtherPlayerConfig.ACCELERATION_PLAYER_RATIO, OtherPlayerConfig.FORCE_RATIO_PLAYER);
    }

    @Override
    public Node createView() {
        double radius = width / 2;

        Circle circle = new Circle(radius);

        circle.setCenterX(radius);
        circle.setCenterY(radius);

        circle.setStroke(Color.WHITE);
        circle.setFill(Color.WHITE.deriveColor(1, 1, 1, 0.3));

        return circle;
    }
}
