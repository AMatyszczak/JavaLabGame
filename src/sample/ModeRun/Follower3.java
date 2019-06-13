package sample.ModeRun;

import javafx.scene.Group;
import javafx.scene.Node;
import sample.Config.OtherPlayerConfig;
import sample.utils.Utils;
import sample.utils.Vector2D;

public class Follower3 extends Sprite {
    public Follower3(Group group, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(group, location, velocity, acceleration, width, height, false, OtherPlayerConfig.SPRITE_FOLLOWER_3_MAX_FORCE, OtherPlayerConfig.SPRITE_FOLLOWER_3_MAX_SPEED, OtherPlayerConfig.ACCELERATION_FOLLOWER_3_RATIO, OtherPlayerConfig.FORCE_RATIO_FOLLOWER_3);
    }

    @Override
    public Node createView() {
        return Utils.createFollower3ImageView((int) width);
    }


}
