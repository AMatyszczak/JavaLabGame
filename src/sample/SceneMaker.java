package sample;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Config.GameConfig;
import sample.Config.Person;
import sample.ModeRun.PlayerController_ModeRun;
import sample.game.GameController;
import sample.game.GameLoop;
import sample.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SceneMaker {

    public static Stage stageMain = GameLoop.stageMain;
    public static GameController gameController;
    private static PlayerController_ModeRun playerControllerModeRun;
    public static PauseMenu pauseMenu;
    private static Group groupMain = new Group();
    public static Scene sceneMain = new Scene(groupMain, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    private static Group groupGame = new Group();
    public static Scene sceneGame = new Scene(groupGame, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    private static Group groupModeChooser = new Group();
    private static Scene sceneMenu = new Scene(groupModeChooser, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    private static Group groupOptions = new Group();
    private static Scene sceneOptions = new Scene(groupOptions, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

    public static void changeWindowSize(int width, int height)
    {
        GameConfig.WINDOW_HEIGHT = height;
        GameConfig.WINDOW_WIDTH= width;
        stageMain.setWidth(width);
        stageMain.setHeight(height);
        Scene scene = new Scene(setUpMainMenuScene());
        stageMain.setScene(scene);
        stageMain.show();



    }

    public static void setUpSceneGame() {

        Image backgroundImage = new Image(GameConfig.BACKGROUND_GAME_IMAGE_PATH);
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        imageView.setX(250);
        imageView.setY(150);
        imageView.toBack();
        groupGame.getChildren().add(imageView);
        setUpPlayerUI();
    }

    private static void setUpPlayerUI() {
        groupGame.getChildren().add(setUpPlayerCurrency());
        groupGame.getChildren().addAll(setUpPlayerCollectives());
    }

    private static Label setUpPlayerCurrency() {
        Label playerCurrencyLabel = new Label("0");
        playerCurrencyLabel.setLayoutX(GameConfig.WINDOW_WIDTH - 100);
        playerCurrencyLabel.setLayoutY(50);
        playerCurrencyLabel.setScaleX(3);
        playerCurrencyLabel.setScaleY(3);
        playerCurrencyLabel.toFront();
        playerCurrencyLabel.textProperty().bind(GameController.playerCurrency.asString());
        return playerCurrencyLabel;
    }

    private static Label setUpPlayerCollectives() {
        Label playerCollectiveLabel = new Label("0");
        playerCollectiveLabel.setLayoutX(GameConfig.WINDOW_WIDTH - 200);
        playerCollectiveLabel.setLayoutY(50);
        playerCollectiveLabel.setScaleX(3);
        playerCollectiveLabel.setScaleY(3);
        playerCollectiveLabel.toFront();
        playerCollectiveLabel.textProperty().bind(GameController.playerCollectives.asString());
        return playerCollectiveLabel;
    }

    public static void enterSceneMain() {
        playerControllerModeRun = new PlayerController_ModeRun(sceneGame, groupGame);
        gameController = new GameController(groupGame, playerControllerModeRun.getPlayerModeRun());
        playerControllerModeRun.setControls_ModeRun();
        setUpSceneMain();

    }

    private static BorderPane setUpMainMenuScene() {
        Image backgroundImage = new Image(GameConfig.BACKGROUND_MAIN_IMAGE_PATH);
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        imageView.toBack();

        BorderPane border = new BorderPane();
        border.getChildren().add(imageView);
        border.setCenter(addGridPane());
        return border;
    }

    private static VBox addTableView() {
        TableView tableView = new TableView();

        TableColumn<String, Person> column1 = new TableColumn<>("Imie");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<String, Person> column2 = new TableColumn<>("punkty");
        column2.setCellValueFactory(new PropertyValueFactory<>("points"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        ArrayList<Person> people = loadPeople();
        for (Person person : people) {
            tableView.getItems().add(person);
        }

        return new VBox(tableView);
    }

    private static GridPane addResolutionButtons()
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        grid.add(Utils.createResolution_1_ImageView(),0,0);
        grid.add(Utils.createResolution_2_ImageView(),0,1);
        grid.add(Utils.createResolution_3_ImageView(),0,2);
        grid.add(Utils.createResolution_4_ImageView(),0,3);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    private static ArrayList<Person> loadPeople() {
        ArrayList<Person> personList = new ArrayList<>(0);

        String nameLine;
        String pointsLine;
        try {
            FileReader fstream = new FileReader("out.txt");
            BufferedReader in = new BufferedReader(fstream);
            nameLine = in.readLine();
            while (nameLine != null) {

                pointsLine = in.readLine();
                Person person = new Person();
                person.setName(nameLine);
                person.setPoints(pointsLine);
                personList.add(person);
                System.out.println(person.getName() + " , " + person.getPoints());
                nameLine = in.readLine();
            }
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return personList;
    }

    private static GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        ImageView playImageView = Utils.createPlayImageView(300);
        playImageView.setOnMouseClicked(event -> {

            SceneMaker.setUpSceneGame();
            SceneMaker.enterSceneGame();
            pauseMenu.setGamePaused(false);
        });
        ImageView tutorialImageView = Utils.createTutorialImageView(300);

        grid.add(tutorialImageView,0,0);

        grid.add(playImageView, 1, 1);

        grid.add(addTableView(), 3, 0);

        grid.add(addResolutionButtons(), 1,0);


        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    private static void setUpSceneMain() {

        changeWindowSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        Scene scene = new Scene(setUpMainMenuScene());
        stageMain.setScene(scene);
        stageMain.show();

    }

    private static void enterSceneGame() {

        stageMain.setScene(sceneGame);
    }
}
