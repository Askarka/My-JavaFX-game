package com.company.Old;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainWithBestPractices extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Demo");

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(1920, 1080);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<String>();
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                });
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });










    }
}
