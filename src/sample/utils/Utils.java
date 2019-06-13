package sample.utils;

import javafx.scene.image.ImageView;
import sample.Config.GameConfig;
import sample.SceneMaker;

public class Utils {
    public static double map(double value, double currentRangeStart, double currentRangeStop, double targetRangeStart, double targetRangeStop) {
        return targetRangeStart + (targetRangeStop - targetRangeStart) * ((value - currentRangeStart) / (currentRangeStop - currentRangeStart));
    }

    public static ImageView createFollower2ImageView(double size) {

        ImageView imageView = new ImageView(GameConfig.ENEMY_2_IMAGE_PATH);
        imageView.toBack();
        return imageView;
    }

    public static ImageView createFollower3ImageView(double size) {

        ImageView imageView = new ImageView(GameConfig.ENEMY_3_IMAGE_PATH);
        imageView.toBack();
        return imageView;
    }

    public static ImageView createFollower4ImageView(double size) {

        ImageView imageView = new ImageView(GameConfig.ENEMY_4_IMAGE_PATH);
        imageView.toBack();
        return imageView;
    }

    public static ImageView createFollower5ImageView(double size) {

        ImageView imageView = new ImageView(GameConfig.ENEMY_5_IMAGE_PATH);
        imageView.toBack();
        return imageView;
    }

    public static ImageView createPlayImageView(double size) {
        ImageView imageView = new ImageView(GameConfig.PLAY_IMAGE_PATH);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        return imageView;
    }

    public static ImageView createTutorialImageView(double size)
    {
        ImageView imageView = new ImageView(GameConfig.TUTORIAL_IMAGE_PATH);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        return imageView;
    }

    public static ImageView createResolution_1_ImageView()
    {
        ImageView imageView = new ImageView(GameConfig.RESOLUTION_BUTTON_1_PATH);

        imageView.setOnMouseClicked(event -> {

            SceneMaker.changeWindowSize(820,600);
        });
        return imageView;
    }
    public static ImageView createResolution_2_ImageView()
    {
        ImageView imageView = new ImageView(GameConfig.RESOLUTION_BUTTON_2_PATH);

        imageView.setOnMouseClicked(event -> {

            SceneMaker.changeWindowSize(1280,720);
        });
        return imageView;
    }
    public static ImageView createResolution_3_ImageView()
    {
        ImageView imageView = new ImageView(GameConfig.RESOLUTION_BUTTON_3_PATH);

        imageView.setOnMouseClicked(event -> {

            SceneMaker.changeWindowSize(1600,900);
        });
        return imageView;
    }
    public static ImageView createResolution_4_ImageView()
    {
        ImageView imageView = new ImageView(GameConfig.RESOLUTION_BUTTON_4_PATH);

        imageView.setOnMouseClicked(event -> {
            SceneMaker.changeWindowSize(1920,1080);
        });
        return imageView;
    }

}
