package sample.ModeRun;

import javafx.scene.Group;
import javafx.scene.Scene;
import sample.utils.Vector2D;

public class PlayerController_ModeRun {
    private final static int MOVE_PLAYER_UP = -1;
    private final static int MOVE_PLAYER_DOWN = 1;
    private final static int MOVE_PLAYER_LEFT = -1;
    private final static int MOVE_PLAYER_RIGHT = 1;

    private final static int PLAYER_INIT_POSITION_X = 10;
    private final static int PLAYER_INIT_POSITION_Y = 10;


    private boolean isPlayerAlive;
    private Player_ModeRun playerModeRun;
    private Scene sceneRoot;
    private Group groupRoot;
    private PlayerMovement playerMovement;
    private Vector2D mouseLocation = new Vector2D(0, 0);

    public PlayerController_ModeRun(Scene scene, Group group) {
        this.sceneRoot = scene;
        this.groupRoot = group;


        isPlayerAlive = true;
        playerModeRun = new Player_ModeRun(groupRoot, new Vector2D(50, 50), new Vector2D(0, 0), new Vector2D(0, 0), 25, 25);
        this.playerMovement = new PlayerMovement(playerModeRun);
        playerMovement.makeDraggable(playerModeRun);

    }


    public void setControls_ModeRun() {

    }

    public Player_ModeRun getPlayerModeRun() {
        return playerModeRun;
    }

    public void setPlayerModeRun(Player_ModeRun playerModeRun) {
        this.playerModeRun = playerModeRun;
    }


}
