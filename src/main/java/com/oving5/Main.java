package com.oving5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize UI components
        Button button = new Button("Click Me");
        button.setOnAction(e -> System.out.println("Button was pressed!"));

        // Set up the layout
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Game UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}