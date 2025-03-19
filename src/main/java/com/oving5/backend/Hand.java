package com.oving5;

import java.util.ArrayList;

public class Hand extends Deck {

    public Hand() {
        super(false);
    }

    public int getHandSize() {
        return getCards().size();
    }

    public void setHandFill(ArrayList<Card> cards) {
        getCards().addAll(cards);
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

    public boolean isStraight(int threshold) {

        for(Card cards : getCards()) {
            int count = 0;
            for(Card card : getCards()) {
                if(cards.getValue() == card.getValue() + 1) {
                    count++;
                }
            }
            if(count >= threshold) {
                return true;
            }
        }

        return false;
    }



    public int getCountSpesificValue(int value) {
        return (int) getCards().stream()
                .filter(card -> card.getValue() == value)
                .count();
    }

    public int getCountSpesificSuit(char suit) {
        return (int) getCards().stream()
                .filter(card -> card.getSuit() == suit)
                .count();
    }

    public ArrayList<Card> getSpesificSuit(char suit) {
        ArrayList<Card> cards = new ArrayList<>();
        getCards().stream()
                .filter(card -> card.getSuit() == suit)
                .forEach(c -> cards.add(c));
        return cards;
    }

    public void printSpesificSuit(char suit) {
        StringBuilder printString = new StringBuilder();
        getCards().stream()
                .filter(card -> card.getSuit() == suit)
                .forEach(c -> printString.append(c.toString()).append(", "));
    }



}
