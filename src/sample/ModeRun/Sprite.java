package sample.ModeRun;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import sample.Config.OtherPlayerConfig;
import sample.SceneMaker;
import sample.utils.Utils;
import sample.utils.Vector2D;


public abstract class Sprite extends Region {
    public Vector2D location;
    public Vector2D velocity;
    public Vector2D acceleration;

    private Vector2D tempLocation;
    private Vector2D tempVelocity;
    private Vector2D tempAcceleration;

    private double maxForce = 0;
    private double maxSpeed = 0;
    private double accelerationSpeedUp = 0;
    private double forceSpeedUp = 0;

    private Node view;

    public double width;
    private double height;
    private double centerX;
    private double centerY;
    private double radius;

    private double angle;

    private boolean isGamePaused = false;

    private Group group;

    public Sprite(Group group, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height, boolean displayOnTop, double maxForce, double maxSpeed, double accelerationSpeedUp, double forceSpeedUp) {

        this.group = group;

        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;
        this.view = createView();

        this.maxForce = maxForce;
        this.maxSpeed = maxSpeed;
        this.accelerationSpeedUp = accelerationSpeedUp;
        this.forceSpeedUp = forceSpeedUp;
        setPrefSize(width, height);

        getChildren().add(view);
        group.getChildren().add(this);

        if (displayOnTop) {
            final AnimationTimer rectangleAnimationX = new AnimationTimer() {
                @Override
                public void handle(long timestamp) {
                    lowerVelocity();

                }
            };
            rectangleAnimationX.start();
        }


    }

    public abstract Node createView();

    private void applyForce(Vector2D force) {

        acceleration.add(force);
    }

    private void lowerVelocity() {
        if (!isGamePaused && velocity != null)
            velocity.div(1.05);
    }

    public void move() {
        if (!isGamePaused && view != null && velocity != null) {
            velocity.add(acceleration);
            velocity.limit(maxSpeed);
            location.add(velocity);
            if (location.x < width / 2)
                location.set(width / 2, location.y);
            if (location.x > SceneMaker.sceneGame.getWidth() - width / 2)
                location.set(SceneMaker.sceneGame.getWidth() - width / 2, location.y);
            if (location.y < height / 2)
                location.set(location.x, height / 2);
            if (location.y > SceneMaker.sceneGame.getHeight() - height / 2)
                location.set(location.x, SceneMaker.sceneGame.getHeight() - height / 2);
            angle = velocity.heading2D();
            acceleration.multiply(0);
        }

    }

    public void seek(Vector2D target) {
        if (!isGamePaused && location != null) {
            Vector2D desired = Vector2D.subtract(target, location);
            double d = desired.magnitude();
            desired.normalize();


            if (d < OtherPlayerConfig.SPRITE_SLOW_DOWN_DISTANCE) {
                double m = Utils.map(d, 0, OtherPlayerConfig.SPRITE_SLOW_DOWN_DISTANCE, 0, maxSpeed);
                desired.multiply(m + accelerationSpeedUp);
            } else {
                desired.multiply(maxSpeed + accelerationSpeedUp);
            }

            Vector2D steer = Vector2D.subtract(desired, velocity);
            steer.limit(maxForce);

            applyForce(steer);
        }


    }

    public void runAway(Vector2D target) {
        if (!isGamePaused && location != null) {

            Vector2D desired = Vector2D.subtract(location, target);
            double d = desired.magnitude();
            desired.normalize();

            if (d < OtherPlayerConfig.SPRITE_SLOW_DOWN_DISTANCE) {
                double m = Utils.map(d, 0, OtherPlayerConfig.SPRITE_SLOW_DOWN_DISTANCE, 0, maxSpeed);
                desired.multiply(m + accelerationSpeedUp);
            } else {
                desired.multiply(maxSpeed + accelerationSpeedUp);
            }

            Vector2D steer = Vector2D.subtract(desired, velocity);
            steer.limit(maxForce);

            applyForce(steer);
        }
    }

    public void runAway_Follower5(Vector2D target) {
        if (!isGamePaused && location != null) {

            Vector2D desired = Vector2D.subtract(location, target);
            double d = desired.magnitude();
            desired.normalize();

            if (d > OtherPlayerConfig.FOLLOWER_5_RUN_SEEK_DISTANCE) {
                desired.multiply(-(maxSpeed + accelerationSpeedUp));
            } else {
                desired.multiply(maxSpeed + accelerationSpeedUp);
            }

            Vector2D steer = Vector2D.subtract(desired, velocity);
            steer.limit(maxForce);

            applyForce(steer);
        }
    }

    public void display() {
        if (location != null) {
            double relocateX = location.x - centerX;
            double relocateY = location.y - centerY;
            relocate(relocateX, relocateY);

            setRotate(Math.toDegrees(angle));
        }

    }

    public void pause() {
        isGamePaused = true;
        tempAcceleration = acceleration;
        tempLocation = location;
        tempVelocity = velocity;

    }

    public void resume() {
        isGamePaused = false;
        acceleration = tempAcceleration;
        location = tempLocation;
        velocity = tempVelocity;

    }

    public void removeSprite() {
        group.getChildren().remove(this);
        this.toBack();
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setAccelerationSpeedUp(double accelerationSpeedUp) {
        this.accelerationSpeedUp = accelerationSpeedUp;
    }
}
