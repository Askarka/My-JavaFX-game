package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

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

        com.company.Sprite solder = new com.company.Sprite();
        solder.setImage("sprites/Chelovek/_Mode-Gun/01-Idle/JK_P_Gun__Idle_000.png");
        solder.setPosition(0, 1080 - 458);

        com.company.Sprite citizen = new com.company.Sprite();
        citizen.setImage("sprites/Chelovek/_Mode-Gun/01-Idle/JK_P_Gun__Idle_000.png");
        citizen.setPosition(1920 - 488, 1080 - 458);

        com.company.Sprite background = new com.company.Sprite();
        background.setImage("sprites/Fony/game_background_4/game_background_4.png");
        background.setPosition(0, 0);

        com.company.Sprite soldersBullet = new com.company.Sprite();
        soldersBullet.setImage("sprites/bullets/small_bullet.png");
        soldersBullet.setPosition(solder.getPositionX()+455, solder.getPositionY() + 270);

        com.company.Sprite citizensBullet = new com.company.Sprite();
        citizensBullet.setImage("sprites/bullets/large_bullet.png");
        citizensBullet.setPosition(citizen.getPositionX()-70, citizen.getPositionY() + 290);

        com.company.LongValue lastNanoTime = new com.company.LongValue( System.nanoTime() );

        new AnimationTimer() {
            boolean expl1 = false;
            boolean expl2 = false;
            int p = 0;
            int cycles = 0;
            int changes = 0;
            boolean sb = true;
            boolean cb = true;
            String currentBackground = "sprites/Fony/game_background_4/game_background_4.png";
            ArrayList<String> bg = new ArrayList<>();
            public void handle(long currentNanoTime) {
                bg.add("sprites/Fony/game_background_1/game_background_1.png");
                bg.add("sprites/Fony/game_background_2/game_background_2.png");
                bg.add("sprites/Fony/game_background_3/game_background_3. 2.png");
                bg.add("sprites/Fony/game_background_4/game_background_4.png");


                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                // game logic

                solder.setVelocity(0,0);
                if (input.contains("A")) {
                    solder.addVelocity(-250, 0);
                    soldersBullet.setPosition(solder.getPositionX() + 455, solder.getPositionY() + 270);
                }
                if (input.contains("D")) {
                    solder.addVelocity(250, 0);
                    soldersBullet.setPosition(solder.getPositionX() + 455, solder.getPositionY() + 270);
                }
                if (input.contains("W")) {
                    solder.addVelocity(0, -195);
                    soldersBullet.setPosition(solder.getPositionX() + 455, solder.getPositionY() + 270);
                }
                if (input.contains("S")) {
                    solder.addVelocity(0, 195);
                    soldersBullet.setPosition(solder.getPositionX() + 455, solder.getPositionY() + 270);
                }

                if (input.contains("SPACE")){
                    soldersBullet.setVelocity(600, 0);
                } else {
                    soldersBullet.setVelocity(0, 0);
                    soldersBullet.setPosition(solder.getPositionX() + 455, solder.getPositionY() + 270);
                }

                if (input.contains("F")) {
                    expl1 = true;
                }
                    //



                soldersBullet.update(elapsedTime);
                solder.update(elapsedTime);


                citizen.setVelocity(0,0);
                if (input.contains("LEFT")) {
                    citizen.addVelocity(-250, 0);
                    citizensBullet.setPosition(citizen.getPositionX() - 70, citizen.getPositionY() + 290);
                }
                if (input.contains("RIGHT")) {
                    citizen.addVelocity(250, 0);
                    citizensBullet.setPosition(citizen.getPositionX() - 70, citizen.getPositionY() + 290);
                }
                if (input.contains("UP")) {
                    citizen.addVelocity(0, -195);
                    citizensBullet.setPosition(citizen.getPositionX() - 70, citizen.getPositionY() + 290);
                }
                if (input.contains("DOWN")) {
                    citizen.addVelocity(0, 195);
                    citizensBullet.setPosition(citizen.getPositionX() - 70, citizen.getPositionY() + 290);
                }
                if (input.contains("CONTROL")) {
                    citizensBullet.setVelocity(-600, 0);
                } else {
                    citizensBullet.setVelocity(0, 0);
                    citizensBullet.setPosition(citizen.getPositionX() - 70, citizen.getPositionY() + 290);
                }
                if (input.contains("SHIFT")) {
                    expl2 = true;
                }
                    //


                if (input.contains("N")) {
                    p++;
                    background.setImage(bg.get(p % 4));
                }
                if (input.contains("F11")) {
                    stage.setFullScreen(true);
                }
                citizensBullet.update(elapsedTime);
                citizen.update(elapsedTime);
                // collision detection and ...



                // render

                cycles++;
                changes = cycles / 4;
                solder.setImage("sprites/Voenny/_Mode-Gun/01-Idle/E_E_Gun__Idle_00" + (new Integer(changes % 10)).toString() + ".png");
                citizen.setImage("sprites/Chelovek/_Mode-Gun/01-Idle/JK_P_Gun__Idle_00" + (new Integer(changes % 10)).toString() + ".png");

                if (expl2){
                    citizen.setImage("sprites/explosion/explosion0" + (new Integer(changes % 9)).toString() + ".png");
                }

                if (solder.intersects(citizensBullet) || expl1){
                    solder.setImage("sprites/explosion/explosion0" + (new Integer(changes % 9)).toString() + ".png");
                }

                if (citizen.intersects(soldersBullet)) {
                    citizen.setImage("sprites/Chelovek/06-Die/JK_P__Die_00" + (new Integer(changes % 10)).toString() + ".png");
                }

                if (solder.intersects(citizensBullet)) {
                    solder.setImage("sprites/Voenny/06-Die/E_E__Die_00" + (new Integer(changes % 10)).toString() + ".png");
                }


                gc.clearRect(0, 0, 1920,1080);
                background.render( gc );
                solder.render( gc );
                citizen.render( gc );
                if(sb && soldersBullet.getVelocityX() != 0)
                soldersBullet.render( gc);
                if(cb && citizensBullet.getVelocityX() != 0)
                citizensBullet.render( gc );

            }
        }.start();

        stage.show();

    }
}