package com.oving5;
import java.awt.Color;

public class Card {
    private final char suit;
    private final int value;
    private final Color color;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
        this.color = getColor();
    }

    public char getSuit() {
        return suit;
    }

    public static char[] getSuits() {
        return new char[]{'H', 'D', 'S', 'C'};
    }


    public int getValue() {
        return value;
    }

    private Color getColor() {
        if (suit == 'H' || suit == 'D') {
            return Color.RED;
        } else {
            return Color.BLACK;
        }
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }
}
