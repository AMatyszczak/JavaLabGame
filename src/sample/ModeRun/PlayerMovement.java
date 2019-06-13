package sample.ModeRun;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.SceneMaker;

import static javafx.scene.input.KeyCode.*;

public class PlayerMovement {
    float playerXMovement = 10;
    float playerYMovement = 10;
    final MovementContext movementContext = new MovementContext();
    private Player_ModeRun player;

    public PlayerMovement(Player_ModeRun player) {
        this.player = player;
    }

    public void makeDraggable(final Sprite sprite) {
        SceneMaker.sceneGame.setOnKeyPressed(onKeyPressedEventHandler);
        SceneMaker.sceneGame.setOnKeyReleased(onKeyReleasedEventHandler);
    }

    EventHandler<KeyEvent> onKeyPressedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            if (event.getCode() == UP)
                player.velocity.add(0, -playerYMovement);
            if (event.getCode() == DOWN)
                player.velocity.add(0, playerYMovement);
            if (event.getCode() == LEFT)
                player.velocity.add(-playerYMovement, 0);
            if (event.getCode() == RIGHT)
                player.velocity.add(playerYMovement, 0);
            if (event.getCode() == KeyCode.ESCAPE && !SceneMaker.pauseMenu.isGamePaused()) {
                SceneMaker.pauseMenu.pause();
            }
        }
    };

    EventHandler<KeyEvent> onKeyReleasedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ESCAPE) {
                SceneMaker.pauseMenu.resume();
            }

        }
    };


    class MovementContext {

        double x;
        double y;

    }

}
