package com.example.demo.pojo;

public class Card {

    private long balance;

    private CardType cardType;

    public Card() {}

    public Card(long balance, CardType cardType) {
        this.setBalance(balance);
        this.setCardType(cardType);
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
