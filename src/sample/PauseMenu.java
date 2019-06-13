package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.game.GameController;

public class PauseMenu {
    private Group groupRoot;
    private Stage popupStage;

    private boolean isGamePaused = true;
    private GameController gameController;

    public PauseMenu(String type, Group groupRoot, double windowWidth, double windowHeight, GameController gameController) {
        this.groupRoot = groupRoot;
        this.gameController = gameController;

        VBox pauseRoot = new VBox(5);
        pauseRoot.getChildren().add(new Label("Game Paused"));
        pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setPadding(new Insets(20));
        addMenuButtons(pauseRoot);

        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(SceneMaker.stageMain);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
        isGamePaused = true;


    }

    private void addMenuButtons(VBox pauseRoot) {
        Button mainMenu = new Button("Main Menu");
        pauseRoot.getChildren().add(mainMenu);
        mainMenu.setOnAction(event -> {
            SceneMaker.enterSceneMain();
            groupRoot.setEffect(null);
            popupStage.hide();
        });

        Button resume = new Button("Resume");
        pauseRoot.getChildren().add(resume);
        resume.setOnAction(event -> {
            resume();
        });

        pauseRoot.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                resume();
            }

        });


    }

    public void resume() {
        setGamePaused(false);
        groupRoot.setEffect(null);
        popupStage.hide();
        gameController.resumeAllSprites();

    }

    public void pause() {
        setGamePaused(true);
        groupRoot.setEffect(new GaussianBlur());
        popupStage.show();
        gameController.stopAllSprites();
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }

}
