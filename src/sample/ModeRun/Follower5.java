package sample.ModeRun;

import javafx.scene.Group;
import javafx.scene.Node;
import sample.Config.OtherPlayerConfig;
import sample.utils.Utils;
import sample.utils.Vector2D;

public class Follower5 extends Sprite {
    public Follower5(Group group, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(group, location, velocity, acceleration, width, height, false, OtherPlayerConfig.SPRITE_FOLLOWER_5_MAX_FORCE, OtherPlayerConfig.SPRITE_FOLLOWER_5_MAX_SPEED, OtherPlayerConfig.ACCELERATION_FOLLOWER_5_RATIO, OtherPlayerConfig.FORCE_RATIO_FOLLOWER_5);
    }

    @Override
    public Node createView() {
        return Utils.createFollower5ImageView((int) width);
    }
}
