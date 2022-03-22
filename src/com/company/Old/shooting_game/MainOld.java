package com.company.Old.shooting_game;

import com.company.IntValue;
import com.company.LongValue;
import com.company.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class MainOld extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Shooting_game");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

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

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Sprite solder = new Sprite();
        solder.setImage("sprites/Voenny/_Mode-Gun/01-Idle/E_E_Gun__Idle_000.png");
        solder.setPosition(0, 1080 - 458);

        Sprite citizen = new Sprite();
        citizen.setImage("sprites/Chelovek/_Mode-Gun/01-Idle/JK_P_Gun__Idle_000.png");
        citizen.setPosition(1920 - 488, 1080 - 458);

        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        new AnimationTimer() {
            int cycles = 0;
            int changes = 0;
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                // game logic

                solder.setVelocity(0,0);
                if (input.contains("E"))
                    solder.addVelocity(-160,0);
                if (input.contains("D"))
                    solder.addVelocity(160,0);
                if (input.contains("W"))
                    solder.addVelocity(0,-50);
                if (input.contains("S"))
                    solder.addVelocity(0,50);

                solder.update(elapsedTime);


                citizen.setVelocity(0,0);
                if (input.contains("LEFT"))
                    citizen.addVelocity(-160,0);
                if (input.contains("RIGHT"))
                    citizen.addVelocity(160,0);
                if (input.contains("UP"))
                    citizen.addVelocity(0,-50);
                if (input.contains("DOWN"))
                    citizen.addVelocity(0,50);

                citizen.update(elapsedTime);
                // collision detection



                // render

                cycles++;
                changes = cycles / 4;
                solder.setImage("sprites/Voenny/_Mode-Gun/01-Idle/E_E_Gun__Idle_00" + (new Integer(changes % 10)).toString() + ".png");
                citizen.setImage("sprites/Chelovek/_Mode-Gun/01-Idle/JK_P_Gun__Idle_00" + (new Integer(changes % 10)).toString() + ".png");

                gc.clearRect(0, 0, 1920,1080);
                solder.render( gc );
                citizen.render( gc );
            }
        }.start();

        stage.show();

    }
}
