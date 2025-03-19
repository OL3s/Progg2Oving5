package com.oving5;

public class Hand extends Deck {

    public Hand() {
        super(false);
    }

    public boolean isFlush(int threshold) {

        // Check if the hand has a flush
        for(char suit : Card.getSuits()) {
            int count = 0;
            for(Card card : getCards()) {
                if(card.getSuit() == suit) {
                    count++;
                }
            }
            if (count >= threshold) {
                return true;
            }
        }

        return false;
    }

}
