package com.oving5;

import com.oving5.backend.Card;
import com.oving5.backend.Deck;
import com.oving5.backend.Hand;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a deck and shuffle it
        Deck deck = new Deck(true);
        deck.sortShuffle();

        // Create a hand and draw 5 cards
        Hand hand = new Hand();
        hand.addCard(deck.drawTop());

        // Create a container to display the cards
        HBox cardDisplay = new HBox(10); // Horizontal box with 10px spacing
        cardDisplay.setAlignment(Pos.CENTER);

        // Display the cards in the hand
        for (Card card : hand.getCards()) {
            StackPane cardPane = createCardPane(card);
            cardDisplay.getChildren().add(cardPane);
        }

        // Create a button to draw a new card
        Button drawButton = new Button("Draw Card");
        drawButton.setOnAction(e -> {
            if (!deck.getCards().isEmpty()) {
                Card newCard = deck.drawTop();
                hand.addCard(newCard);
                StackPane newCardPane = createCardPane(newCard);
                cardDisplay.getChildren().add(newCardPane);
            }
        });

        // Set up the layout
        VBox layout = new VBox(20); // Vertical box with 20px spacing
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(cardDisplay, drawButton);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane createCardPane(Card card) {
        // Create a container for the card
        StackPane cardPane = new StackPane();
        cardPane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");
        cardPane.setPrefSize(100, 150);
    
        // Convert java.awt.Color to javafx.scene.paint.Color
        java.awt.Color awtColor = card.getColor();
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(
            awtColor.getRed(),
            awtColor.getGreen(),
            awtColor.getBlue()
        );
    
        // Set the border color using the card's color
        cardPane.setStyle(
            String.format("-fx-border-color: rgb(%d, %d, %d); -fx-border-width: 2; -fx-background-color: white;",
                awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue())
        );
    
        // Use suitToString and valueToString for card display
        String suit = Card.suitToString(card.getSuit());
        String value = Card.valueToString(card.getValue());
    
        // Create the value text for the corners
        Text topLeftValue = new Text(value);
        topLeftValue.setFont(Font.font(14));
        topLeftValue.setFill(fxColor); // Set font color to match the card's color
        topLeftValue.setTranslateX(-35); // Position in the top-left corner
        topLeftValue.setTranslateY(-60);
    
        Text bottomRightValue = new Text(value);
        bottomRightValue.setFont(Font.font(14));
        bottomRightValue.setFill(fxColor); // Set font color to match the card's color
        bottomRightValue.setTranslateX(35); // Position in the bottom-right corner
        bottomRightValue.setTranslateY(60);
    
        // Create the suit text for the center
        Text suitText = new Text(suit);
        suitText.setFont(Font.font(40)); // Larger font for the suit
        suitText.setFill(fxColor); // Set font color to match the card's color
    
        // Add all elements to the card pane
        cardPane.getChildren().addAll(topLeftValue, bottomRightValue, suitText);
        return cardPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}