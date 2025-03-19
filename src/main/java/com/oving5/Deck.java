package com.oving5;

public class Deck {
    private final Card[] cards;

    public Deck() {
        cards = new Card[52];
        int index = 0;
        for (char suit : Card.getSuits()) {
            for (int value = 1; value <= 13; value++) {
                cards[index++] = new Card(suit, value);
            }
        }
    }

    public Card[] getCards() {
        return cards;
    }
}
