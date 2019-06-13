package sample.game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Config.GameConfig;
import sample.Config.OtherPlayerConfig;
import sample.ModeRun.*;
import sample.PauseMenu;
import sample.SceneMaker;
import sample.utils.Vector2D;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    public static IntegerProperty playerCurrency = new SimpleIntegerProperty(0);
    public static IntegerProperty playerCollectives = new SimpleIntegerProperty(0);
    public static PauseMenu pauseMenu;
    private static Random random = new Random();
    public Player_ModeRun player;
    private Group groupRoot;
    private List<Follower2> allFollower2;
    private List<Follower3> allFollower3;
    private List<Follower4> allFollower4;
    private List<Follower5> allFollower5;
    private ArrayList<AnimationTimer> animators = new ArrayList<AnimationTimer>();

    private Boolean isGameEnded;
    private Boolean isSpawningOn;
    private Stage popupStage;

    public GameController(Group groupRoot, Player_ModeRun player) {
        this.groupRoot = groupRoot;
        this.player = player;
        isGameEnded = false;
        isSpawningOn = false;
        playerCurrency = new SimpleIntegerProperty(0);
        playerCollectives = new SimpleIntegerProperty(0);

        allFollower2 = new ArrayList<>(0);
        allFollower3 = new ArrayList<>(0);
        allFollower4 = new ArrayList<>(0);
        allFollower5 = new ArrayList<>(0);

        pauseMenu = new PauseMenu("LUL", groupRoot, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT, this);
        SceneMaker.pauseMenu = pauseMenu;

    }

    private void addFollower_2_ByTime() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(OtherPlayerConfig.FOLLOWER_2_SPAWNTIME), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addFollower_2();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    private void addFollower_3_ByTime() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(OtherPlayerConfig.FOLLOWER_3_SPAWNTIME), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addFollower_3();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    private void addFollower_4_ByTime() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(OtherPlayerConfig.FOLLOWER_4_SPAWNTIME), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addFollower_4();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    private void addFollower_5_ByTime() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(OtherPlayerConfig.FOLLOWER_5_SPAWNTIME), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                addFollower_5();
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    public void playGame() {
        if (!pauseMenu.isGamePaused() && !isGameEnded) {
            player.move();
            if (!allFollower2.isEmpty())
                moveFollowers2();
            if (!allFollower3.isEmpty())
                moveFollowers3();
            if (!allFollower4.isEmpty())
                moveFollowers4();
            if (!allFollower5.isEmpty())
                moveFollowers5();

            player.toFront();
            player.display();
            checkCollisions();
            if (!isSpawningOn) {
                isSpawningOn = true;
                spawnFollowers();
            }
        }


    }

    private void moveFollowers2() {
        allFollower2.forEach(Sprite::move);
        allFollower2.forEach(Sprite::toFront);
        allFollower2.forEach(Sprite::display);
        allFollower2.forEach(follower2 -> {
            follower2.seek(player.getLocation());
        });
    }

    private void moveFollowers3() {
        allFollower3.forEach(Sprite::move);
        allFollower3.forEach(Sprite::toFront);
        allFollower3.forEach(Sprite::display);
        allFollower3.forEach(allFollower3 -> {
            allFollower3.runAway(player.getLocation());
        });
    }

    private void moveFollowers4() {
        allFollower4.forEach(Sprite::move);
        allFollower4.forEach(Sprite::toFront);
        allFollower4.forEach(Sprite::display);
    }

    private void moveFollowers5() {
        allFollower5.forEach(Sprite::move);
        allFollower5.forEach(Sprite::toFront);
        allFollower5.forEach(Sprite::display);
        allFollower5.forEach(allFollower5 -> {
            allFollower5.runAway_Follower5(player.getLocation());
        });
    }

    private void addFollower_2() {
        if(!pauseMenu.isGamePaused())
        {
            double x = random.nextDouble() * GameConfig.WINDOW_WIDTH;
            double y = random.nextDouble() * GameConfig.WINDOW_HEIGHT;

            double width = 50;
            double height = width / 2.0;

            double accelerationValue = random.nextDouble() * OtherPlayerConfig.ACCELERATION_FOLLOWER_2_RATIO;

            Vector2D location = new Vector2D(x, y);
            Vector2D velocity = new Vector2D(0, 0);
            Vector2D acceleration = new Vector2D(0, 0);

            Follower2 follower2 = new Follower2(groupRoot, location, velocity, acceleration, width, height);
            follower2.setAccelerationSpeedUp(accelerationValue);

            allFollower2.add(follower2);
        }

    }

    private void addFollower_3() {
        if(!pauseMenu.isGamePaused()) {
            double x = random.nextDouble() * GameConfig.WINDOW_WIDTH;
            double y = random.nextDouble() * GameConfig.WINDOW_HEIGHT;

            double width = 50;
            double height = width / 2.0;

            double accelerationValue = random.nextDouble() * OtherPlayerConfig.ACCELERATION_FOLLOWER_3_RATIO;

            Vector2D location = new Vector2D(x, y);
            Vector2D velocity = new Vector2D(0, 0);
            Vector2D acceleration = new Vector2D(0, 0);

            Follower3 follower3 = new Follower3(groupRoot, location, velocity, acceleration, width, height);
            follower3.setAccelerationSpeedUp(accelerationValue);

            allFollower3.add(follower3);
        }
    }

    private void addFollower_4() {
        if(!pauseMenu.isGamePaused()) {

            double x = random.nextDouble() * GameConfig.WINDOW_WIDTH;
            double y = random.nextDouble() * GameConfig.WINDOW_HEIGHT;

            double width = 50;
            double height = width / 2.0;

            double accelerationValue = random.nextDouble() * OtherPlayerConfig.ACCELERATION_FOLLOWER_4_RATIO;

            Vector2D location = new Vector2D(x, y);
            Vector2D velocity = new Vector2D(0, 0);
            Vector2D acceleration = new Vector2D(0, 0);

            Follower4 follower4 = new Follower4(groupRoot, location, velocity, acceleration, width, height);
            follower4.setAccelerationSpeedUp(accelerationValue);

            allFollower4.add(follower4);
        }

    }

    private void addFollower_5() {
        if(!pauseMenu.isGamePaused()) {
            double x = random.nextDouble() * GameConfig.WINDOW_WIDTH;
            double y = random.nextDouble() * GameConfig.WINDOW_HEIGHT;

            double width = 50;
            double height = width / 2.0;

            double accelerationValue = random.nextDouble() * OtherPlayerConfig.ACCELERATION_FOLLOWER_5_RATIO;

            Vector2D location = new Vector2D(x, y);
            Vector2D velocity = new Vector2D(0, 0);
            Vector2D acceleration = new Vector2D(0, 0);

            Follower5 follower5 = new Follower5(groupRoot, location, velocity, acceleration, width, height);
            follower5.setAccelerationSpeedUp(accelerationValue);

            allFollower5.add(follower5);
        }
    }

    private void spawnFollowers() {
        if (!pauseMenu.isGamePaused() && isSpawningOn) {
            addFollower_2_ByTime();
            addFollower_3_ByTime();
            addFollower_4_ByTime();
            addFollower_5_ByTime();
        }

    }

    public void stopAllSprites() {
        player.pause();
        for (Follower2 follower2 : allFollower2) {
            follower2.pause();
        }
        for (Follower3 follower3 : allFollower3) {
            follower3.pause();
        }
        for (Follower4 follower4 : allFollower4) {
            follower4.pause();
        }
        for (Follower5 follower5 : allFollower5) {
            follower5.pause();
        }
    }

    public void resumeAllSprites() {
        player.resume();
        for (Follower2 follower2 : allFollower2) {
            follower2.resume();
        }
        for (Follower3 follower3 : allFollower3) {
            follower3.resume();
        }
        for (Follower4 follower4 : allFollower4) {
            follower4.resume();
        }
        for (Follower5 follower5 : allFollower5) {
            follower5.resume();
        }
    }

    private void checkCollisions() {
        for (Follower2 follower2 : allFollower2) {
            if (follower2.getBoundsInParent().intersects(player.getBoundsInParent())) {

                follower2.removeSprite();
                changePlayerStats(2);
                follower2.pause();
                allFollower2.remove(follower2);
            } else {
                //System.out.println("NO COLLISION Follower2");
            }
        }


        for (Follower3 follower3 : allFollower3) {
            if (follower3.getBoundsInParent().intersects(player.getBoundsInParent())) {
                follower3.removeSprite();

                changePlayerStats(3);
                follower3.pause();
                allFollower3.remove(follower3);
            } else {
                // System.out.println("NO COLLISION Follower3");
            }
        }

        for (Follower4 follower4 : allFollower4) {
            if (follower4.getBoundsInParent().intersects(player.getBoundsInParent())) {
                follower4.removeSprite();
                changePlayerStats(4);
                follower4.pause();
                allFollower4.remove(follower4);
            } else {
                // System.out.println("NO COLLISION Follower3");
            }
        }

        for (Follower5 follower5 : allFollower5) {
            if (follower5.getBoundsInParent().intersects(player.getBoundsInParent())) {
                follower5.removeSprite();
                changePlayerStats(5);
                follower5.pause();
                allFollower5.remove(follower5);
            } else {
                // System.out.println("NO COLLISION Follower3");
            }
        }
    }

    void changePlayerStats(int currency) {
        playerCurrency.setValue(playerCurrency.getValue() + currency);
        playerCollectives.setValue(playerCollectives.getValue() + 1);
        if (playerCollectives.getValue() >= OtherPlayerConfig.COLLECTIVES_CUP) {
            endGame();
        }
    }

    void endGame() {
        isGameEnded = true;
        VBox pauseRoot = new VBox(5);
        pauseRoot.getChildren().add(new Label("Game Over\n Zebrałeś: " + playerCurrency.getValue() + " punktów"));
        pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setPadding(new Insets(20));

        popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(SceneMaker.stageMain);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));

        pauseRoot.getChildren().add(new Label("\n\nPodaj imie"));
        TextField textField = new TextField("");
        pauseRoot.getChildren().add(textField);
        Button mainMenu = new Button("Zapisz");
        pauseRoot.getChildren().add(mainMenu);
        mainMenu.setOnAction(event -> {
            saveGame(textField.getText(), playerCurrency.getValue());
            SceneMaker.enterSceneMain();
            groupRoot.setEffect(null);
            popupStage.hide();
        });
        popupStage.show();
    }

    private void saveGame(String name, int currency) {
        try {
            // Create file
            FileWriter fstream = new FileWriter("out.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);

            out.write(name + '\n' + currency);
            out.newLine();

            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
