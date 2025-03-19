package com.oving5;

import com.oving5.backend.Card;
import com.oving5.backend.Deck;
import com.oving5.backend.Hand;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    // Declare deck as a field
    private Deck deck;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the deck and shuffle it
        deck = new Deck(true);
        deck.sortShuffle();

        // Create an empty hand
        Hand hand = new Hand();

        // Create a container to display the cards
        HBox cardDisplay = new HBox(10); // Horizontal box with 10px spacing
        cardDisplay.setAlignment(Pos.CENTER);

        // Label to show the deck card count
        Label deckCountLabel = new Label("Deck Count: " + deck.getCards().size());
        deckCountLabel.setFont(Font.font(16));

        // Slider for threshold
        Slider thresholdSlider = new Slider(1, 10, 3); // Min: 1, Max: 5, Default: 3
        thresholdSlider.setShowTickLabels(true);
        thresholdSlider.setShowTickMarks(true);
        thresholdSlider.setMajorTickUnit(1);
        thresholdSlider.setSnapToTicks(true);

        // Label for the slider
        Label thresholdLabel = new Label("Threshold:");
        thresholdLabel.setFont(Font.font(16));

        // Combine the slider and its label in an HBox
        HBox sliderBox = new HBox(10, thresholdLabel, thresholdSlider);
        sliderBox.setAlignment(Pos.CENTER);

        // Label to display hand check results
        Label handCheckResultsLabel = new Label("Hand Check Results:");
        handCheckResultsLabel.setFont(Font.font(16));

        // Button to draw a card
        Button drawButton = new Button("Draw Card");
        drawButton.setOnAction(e -> {
            if (!deck.getCards().isEmpty()) {
                Card newCard = deck.drawTop();
                hand.addCard(newCard);
                StackPane newCardPane = createCardPane(newCard);
                cardDisplay.getChildren().add(newCardPane);
                deckCountLabel.setText("Deck Count: " + deck.getCards().size());
            }
        });

        // Button to reset the deck
        Button resetDeckButton = new Button("Reset Deck");
        resetDeckButton.setOnAction(e -> {
            deck = new Deck(true);
            deck.sortShuffle();
            deckCountLabel.setText("Deck Count: " + deck.getCards().size());
        });

        // Button to reset the hand
        Button resetHandButton = new Button("Reset Hand");
        resetHandButton.setOnAction(e -> {
            hand.getCards().clear();
            cardDisplay.getChildren().clear();
            handCheckResultsLabel.setText("Hand Check Results:");
        });

        // Button to check hand conditions
        Button checkButton = new Button("Check Hand");
        checkButton.setOnAction(e -> {
            if (hand.getCards().isEmpty()) {
                handCheckResultsLabel.setText("Hand Check Results: Hand is empty");
                return;
            }
            int threshold = (int) thresholdSlider.getValue();
            boolean isFlush = hand.isFlush(threshold);
            boolean isStraight = hand.isStraight(threshold);

            // Update the results label
            StringBuilder result = new StringBuilder("Hand Check Results:\n");
            result.append("Flush (").append(threshold).append("): ").append(isFlush).append("\n");
            result.append("Straight (").append(threshold).append("): ").append(isStraight);
            handCheckResultsLabel.setText(result.toString());
        });

        // Button to sort the hand by suit
        Button sortSuitButton = new Button("Sort by Suit");
        sortSuitButton.setOnAction(e -> {
            hand.sortSuit(); // Sort the hand by suit
            cardDisplay.getChildren().clear(); // Clear the current display
            for (Card card : hand.getCards()) {
                StackPane cardPane = createCardPane(card); // Create a new card pane for each card
                cardDisplay.getChildren().add(cardPane); // Add the sorted cards back to the display
            }
        });

        // Button to sort the hand by value
        Button sortValueButton = new Button("Sort by Value");
        sortValueButton.setOnAction(e -> {
            hand.sortValue(); // Sort the hand by value
            cardDisplay.getChildren().clear(); // Clear the current display
            for (Card card : hand.getCards()) {
                StackPane cardPane = createCardPane(card); // Create a new card pane for each card
                cardDisplay.getChildren().add(cardPane); // Add the sorted cards back to the display
            }
        });

        // Combine the buttons in an HBox
        HBox buttonBox = new HBox(10, drawButton, resetDeckButton, resetHandButton, checkButton, sortSuitButton, sortValueButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Set up the layout
        VBox layout = new VBox(20); // Vertical box with 20px spacing
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(deckCountLabel, cardDisplay, buttonBox, sliderBox, handCheckResultsLabel);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 800, 600);
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
    
        // Create the value text for the top-left corner
        Text topLeftValue = new Text(value);
        topLeftValue.setFont(Font.font(14));
        topLeftValue.setFill(fxColor); // Set font color to match the card's color
        topLeftValue.setTranslateX(-35); // Position in the top-left corner
        topLeftValue.setTranslateY(-60);
    
        // Create the value text for the bottom-right corner
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
        cardPane.getChildren().addAll(topLeftValue, suitText, bottomRightValue);
        return cardPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}