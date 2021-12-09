package com.example.demo.calculator;

import com.example.demo.pojo.Card;
import com.example.demo.pojo.CardType;
import com.example.demo.pojo.Person;
import com.example.demo.pojo.Wallet;

public class InterestCalc {

    public static long calculateInterestOnCard(Card card) {
        long returnMe = card.getBalance();

        if (card.getCardType().equals(CardType.VISA)) {
            returnMe *= 0.1;
        } else if (card.getCardType().equals(CardType.MC)) {
            returnMe *= 0.05;
        } else if (card.getCardType().equals(CardType.DISCOVER)) {
            returnMe *= 0.01;
        }

        return returnMe;
    }

    public static long calculateInterestOnWallet(Wallet wallet) {
        long returnMe = 0l;

        for (Card card : wallet.getCards()) {
            if (card.getCardType().equals(CardType.VISA)) {
                returnMe += calculateInterestOnCard(card);
            } else if (card.getCardType().equals(CardType.MC)) {
                returnMe += calculateInterestOnCard(card);
            } else if (card.getCardType().equals(CardType.DISCOVER)) {
                returnMe += calculateInterestOnCard(card);
            }
        }

        return returnMe;
    }

    public static long calculateInterestOnPerson(Person person) {
        long returnMe = 0l;

        for (Wallet wallet : person.getWallets()) {
            returnMe += calculateInterestOnWallet(wallet);
        }

        return returnMe;
    }
}