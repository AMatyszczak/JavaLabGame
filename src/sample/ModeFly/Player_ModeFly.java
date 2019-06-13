package sample.ModeFly;

import javafx.scene.Group;
import javafx.scene.image.Image;
import sample.game.GameController;

public class Player_ModeFly extends PlayerObject_ModeFly {
    private final static int PLAYER_MOVEMENT_SPEED = 400;
    private final static int PLAYER_INIT_HEALTH = 100;


    private int health = PLAYER_INIT_HEALTH;
    private double playerMovementSpeed = PLAYER_MOVEMENT_SPEED;
    private Image playerImage;


    public Player_ModeFly(double playerX_position, double playerY_position, Image playerImage, Group rootGroup) {
        super(playerX_position, playerY_position, playerImage, rootGroup);
        this.health = health;
        this.playerImage = playerImage;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void movePlayer_X(int direction) {
        if (!GameController.pauseMenu.isGamePaused()) {
            changeXVelocity(playerMovementSpeed * direction);
        }
    }

    public void movePlayer_Y(double direction) {
        if (!GameController.pauseMenu.isGamePaused()) {
            changeYVelocity(playerMovementSpeed * direction);
        }
    }

    public void stopPlayer_X() {
        changeXVelocity(0);
    }

    public void stopPlayer_Y() {

        changeYVelocity(200);
    }

}
