package sample.ModeFly;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import sample.Config.GameConfig;
import sample.SceneMaker;

import static javafx.scene.input.KeyCode.*;

public class PlayerController_ModeFly {
    private final static int MOVE_PLAYER_UP = -1;
    private final static int MOVE_PLAYER_DOWN = 1;
    private final static int MOVE_PLAYER_LEFT = -1;
    private final static int MOVE_PLAYER_RIGHT = 1;

    private final static int PLAYER_INIT_POSITION_X = 10;
    private final static int PLAYER_INIT_POSITION_Y = 10;


    private boolean isPlayerAlive;
    public static Player_ModeFly playerModeFly;
    private Scene sceneRoot;
    private Group groupRoot;

    public PlayerController_ModeFly(Scene scene, Group group) {
        this.sceneRoot = scene;
        this.groupRoot = group;

        isPlayerAlive = true;
        playerModeFly = new Player_ModeFly(50, 50, new Image(GameConfig.PLAYER_IMAGE_PATH), groupRoot);
    }

    public void setControls_ModeRun() {
        sceneRoot.setOnKeyPressed((event) ->
        {
            if (isPlayerAlive) {
                if (event.getCode() == UP)
                    playerModeFly.movePlayer_Y(MOVE_PLAYER_UP);
                if (event.getCode() == DOWN)
                    playerModeFly.movePlayer_Y(MOVE_PLAYER_DOWN);
                if (event.getCode() == LEFT)
                    playerModeFly.movePlayer_X(MOVE_PLAYER_LEFT);
                if (event.getCode() == RIGHT)
                    playerModeFly.movePlayer_X(MOVE_PLAYER_RIGHT);

                if (!SceneMaker.pauseMenu.isGamePaused()) {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        SceneMaker.pauseMenu.pause();
                    }
                }
            }
        });

        sceneRoot.setOnKeyReleased((event) ->
        {
            if (isPlayerAlive) {
                if (event.getCode() == UP || event.getCode() == DOWN)
                    playerModeFly.stopPlayer_Y();
                if (event.getCode() == LEFT || event.getCode() == RIGHT)
                    playerModeFly.stopPlayer_X();
            }
        });


    }


}
