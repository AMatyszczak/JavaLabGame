package sample.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import sample.SceneMaker;

public class GameLoop extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stageMain;
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) {

        stageMain = primaryStage;
        stageMain.setResizable(false);
        primaryStage.setTitle("Java Project");

//        SceneMaker.setUpSceneMain();
        SceneMaker.setUpSceneGame();
        SceneMaker.enterSceneMain();

        primaryStage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (SceneMaker.gameController != null)
                    SceneMaker.gameController.playGame();
            }
        };
        gameLoop.start();

    }

}
