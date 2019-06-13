package sample.ModeRun;

import javafx.scene.Group;
import javafx.scene.Node;
import sample.Config.OtherPlayerConfig;
import sample.utils.Utils;
import sample.utils.Vector2D;

public class Follower4 extends Sprite {
    public Follower4(Group group, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(group, location, velocity, acceleration, width, height, false, OtherPlayerConfig.SPRITE_FOLLOWER_4_MAX_FORCE, OtherPlayerConfig.SPRITE_FOLLOWER_4_MAX_SPEED, OtherPlayerConfig.ACCELERATION_FOLLOWER_4_RATIO, OtherPlayerConfig.FORCE_RATIO_FOLLOWER_4);
    }

    @Override
    public Node createView() {
        return Utils.createFollower4ImageView((int) width);
    }
}
