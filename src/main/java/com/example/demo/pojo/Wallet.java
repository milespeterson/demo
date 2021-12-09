package com.example.demo.pojo;

import java.util.List;

public class Wallet {
    private List<Card> cards;

    public Wallet() {}

    public Wallet(List<Card> cards) {
        this.setCards(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
