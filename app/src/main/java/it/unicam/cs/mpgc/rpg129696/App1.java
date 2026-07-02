package it.unicam.cs.mpgc.rpg129696;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App1 extends Application {



    @Override
    public void start(Stage stage) {
        Image bg = new Image(getClass().getResourceAsStream("/images/menu.png"));
        BackgroundImage backgroundImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );

        Button startBtn = createButton("INIZIA PARTITA");
        Button loadBtn  = createButton("CARICA PARTITA");
        Button exitBtn  = createButton("ESCI");

        exitBtn.setOnAction(e -> stage.close());
        startBtn.setOnAction(e -> System.out.println("Start Game!"));
        loadBtn.setOnAction(e -> System.out.println("Load Game!"));

        VBox menu = new VBox(18, startBtn, loadBtn, exitBtn);
        menu.setAlignment(Pos.CENTER);
        menu.setTranslateY(60);
        menu.setTranslateX(120); // spostato a destra

        StackPane root = new StackPane(menu);
        root.setBackground(new Background(backgroundImage));

        Scene scene = new Scene(root, 1024, 576);
        stage.setTitle("Toxic Rain");
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text) {
        Button btn = new Button(text);

        String normal = """
                -fx-background-color: linear-gradient(to bottom, #1a1a1a, #0d0d0d);
                -fx-text-fill: #7fff00;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-font-family: 'Impact';
                -fx-pref-width: 220px;
                -fx-pref-height: 45px;
                -fx-border-color: #4a8c00;
                -fx-border-width: 2px;
                -fx-border-radius: 4px;
                -fx-background-radius: 4px;
                -fx-cursor: hand;
                """;

        String hover = """
                -fx-background-color: linear-gradient(to bottom, #2a4a00, #1a3000);
                -fx-text-fill: #ccff00;
                -fx-font-size: 19px;
                -fx-font-weight: bold;
                -fx-font-family: 'Impact';
                -fx-pref-width: 220px;
                -fx-pref-height: 45px;
                -fx-border-color: #7fff00;
                -fx-border-width: 2px;
                -fx-border-radius: 4px;
                -fx-background-radius: 4px;
                -fx-cursor: hand;
                """;

        String pressed = """
                -fx-background-color: linear-gradient(to bottom, #0d0d0d, #1a1a1a);
                -fx-text-fill: #ffffff;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-font-family: 'Impact';
                -fx-pref-width: 220px;
                -fx-pref-height: 45px;
                -fx-border-color: #ffffff;
                -fx-border-width: 2px;
                -fx-border-radius: 4px;
                -fx-background-radius: 4px;
                -fx-cursor: hand;
                """;

        DropShadow glow = new DropShadow();
        glow.setColor(Color.web("#7fff00"));
        glow.setRadius(15);
        glow.setSpread(0.3);

        btn.setStyle(normal);
        btn.setOnMouseEntered(e -> {
            btn.setStyle(hover);
            btn.setEffect(glow);
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle(normal);
            btn.setEffect(null);
        });
        btn.setOnMousePressed(e -> btn.setStyle(pressed));
        btn.setOnMouseReleased(e -> btn.setStyle(hover));

        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}