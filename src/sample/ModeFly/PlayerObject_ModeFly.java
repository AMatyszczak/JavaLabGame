package sample.ModeFly;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Config.GameConfig;


public class PlayerObject_ModeFly extends ImageView {

    private static final double PLAYER_VELOCITY_CAP = 300;
    private static final double PLAYER_VEOLOCITY_TICK = 25;

    private double playerX_position;
    private double playerY_position;

    private final LongProperty lastUpdateTimeX = new SimpleLongProperty();
    private final LongProperty lastUpdateTimeY = new SimpleLongProperty();

    private final DoubleProperty rectangleXVelocity = new SimpleDoubleProperty();
    private final DoubleProperty rectangleYVelocity = new SimpleDoubleProperty();


    public PlayerObject_ModeFly(double playerX_position, double playerY_position, Image image, Group rootGroup) {

        this.playerX_position = playerX_position;
        this.playerY_position = playerY_position;
        this.setImage(image);
        changeYVelocity(200);

        rootGroup.getChildren().add(this);
    }

    protected void changeXVelocity(double velocity) {

        rectangleXVelocity.setValue(velocity);
        PlayerObject_ModeFly rootClass = this;

        final AnimationTimer rectangleAnimationX = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTimeX.get() > 0) {
                    final double elapsedSeconds = (timestamp - lastUpdateTimeX.get()) / 1_000_000_000.0;
                    final double deltaX = elapsedSeconds * rectangleXVelocity.get();
                    final double oldX = rootClass.getTranslateX();
                    final double newX = Math.max(0, Math.min(GameConfig.WINDOW_WIDTH - 100, oldX + deltaX));
                    rootClass.setTranslateX(newX);
                }
                lastUpdateTimeX.set(timestamp);
            }
        };
        rectangleAnimationX.start();
    }

    protected void changeYVelocity(double velocity) {
        rectangleYVelocity.setValue(velocity);
        PlayerObject_ModeFly rootClass = this;

        final AnimationTimer rectangleAnimation = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (lastUpdateTimeY.get() > 0) {
                    final double elapsedSeconds = (timestamp - lastUpdateTimeY.get()) / 1_000_000_000.0;
                    final double deltaY = elapsedSeconds * rectangleYVelocity.get();
                    final double oldY = rootClass.getTranslateY();
                    final double newY = Math.max(0, Math.min(GameConfig.WINDOW_HEIGHT - 100, oldY + deltaY));
                    rootClass.setTranslateY(newY);
                }
                lastUpdateTimeY.set(timestamp);
            }
        };
        rectangleAnimation.start();

    }


}

