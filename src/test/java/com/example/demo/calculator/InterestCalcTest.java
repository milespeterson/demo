package com.example.demo.calculator;

import com.example.demo.pojo.Card;
import com.example.demo.pojo.CardType;
import com.example.demo.pojo.Person;
import com.example.demo.pojo.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterestCalcTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void calculateInterestOnCard() {

        Card testCard = new Card();
        testCard.setCardType(CardType.VISA);
        testCard.setBalance(100);

        assertEquals(10, InterestCalc.calculateInterestOnCard(testCard));
    }

    @Test
    void calculateInterestOnWallet() {
        List<Card> visaCards = createCards(3, 50, 20, CardType.VISA);
        List<Card> masterCards = createCards(2, 21, 50, CardType.MC);
        List<Card> discoverCards = createCards(4, 1000, 5, CardType.DISCOVER);

        long interest = 0L;

        interest += InterestCalc.calculateInterestOnWallet(new Wallet(visaCards));
        interest += InterestCalc.calculateInterestOnWallet(new Wallet(masterCards));
        interest += InterestCalc.calculateInterestOnWallet(new Wallet(discoverCards));

        assertEquals(65, interest);
    }

    @Test
    void calculateInterestOnPerson() {
        List<Card> visaCards1 = createCards(1, 150, 2, CardType.VISA);
        List<Card> visaCards2 = createCards(3, 120, 70, CardType.VISA);
        List<Card> masterCards1 = createCards(5, 210, 7, CardType.MC);
        List<Card> masterCards2 = createCards(1, 50, 80, CardType.MC);
        List<Card> discoverCards1 = createCards(3, 10, 500, CardType.DISCOVER);
        List<Card> discoverCards2 = createCards(2, 190, 540, CardType.DISCOVER);

        List<Card> allCards1 = new ArrayList<>();
        allCards1.addAll(visaCards1);
        allCards1.addAll(masterCards1);
        allCards1.addAll(discoverCards1);
        Wallet wallet1 = new Wallet(allCards1);

        List<Card> allCards2 = new ArrayList<>();
        allCards2.addAll(visaCards2);
        allCards2.addAll(masterCards2);
        allCards2.addAll(discoverCards2);
        Wallet wallet2 = new Wallet(allCards2);

        List<Wallet> wallets = new ArrayList<>();
        wallets.add(wallet1);
        wallets.add(wallet2);

        Person person = new Person(wallets);

        assertEquals(150, InterestCalc.calculateInterestOnPerson(person));
    }

    @Test
    void caseOne () {

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(100, CardType.VISA));
        cards.add(new Card(100, CardType.MC));
        cards.add(new Card(100, CardType.DISCOVER));

        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet(cards));

        Person person = new Person(wallets);

        assertEquals(10, InterestCalc.calculateInterestOnCard(person.getWallets().get(0).getCards().get(0)));
        assertEquals(5, InterestCalc.calculateInterestOnCard(person.getWallets().get(0).getCards().get(1)));
        assertEquals(1, InterestCalc.calculateInterestOnCard(person.getWallets().get(0).getCards().get(2)));
        assertEquals(16, InterestCalc.calculateInterestOnPerson(person));
    }

    @Test
    void caseTwo () {
        List<Card> cards1 = new ArrayList<>();
        List<Card> cards2 = new ArrayList<>();

        cards1.add(new Card(100, CardType.VISA));
        cards1.add(new Card(100, CardType.DISCOVER));

        cards2.add(new Card(100, CardType.MC));

        Wallet wallet1 = new Wallet(cards1);
        Wallet wallet2 = new Wallet(cards2);

        List<Wallet> wallets = new ArrayList<>();
        wallets.add(wallet1);
        wallets.add(wallet2);

        Person person = new Person(wallets);

        assertEquals(11, InterestCalc.calculateInterestOnWallet(person.getWallets().get(0)));
        assertEquals(5, InterestCalc.calculateInterestOnWallet(person.getWallets().get(1)));
        assertEquals(16, InterestCalc.calculateInterestOnPerson(person));
    }

    @Test
    void caseThree () {
        Card mcforWallet1_1 = new Card(100, CardType.MC);
        Card mcforWallet1_2 = new Card(100, CardType.MC);

        Card mcforWallet2_1 = new Card(100, CardType.VISA);
        Card mcforWallet2_2 = new Card(100, CardType.MC);

        List<Card> cards1 = new ArrayList<>();
        cards1.add(mcforWallet1_1);
        cards1.add(mcforWallet1_2);

        List<Card> cards2 = new ArrayList<>();
        cards2.add(mcforWallet2_1);
        cards2.add(mcforWallet2_2);

        Wallet wallet1 = new Wallet(cards1);
        Wallet wallet2 = new Wallet(cards2);

        List<Wallet> wallets1 = new ArrayList<>();
        wallets1.add(wallet1);

        List<Wallet> wallets2 = new ArrayList<>();
        wallets2.add(wallet2);

        Person person1 = new Person(wallets1);
        Person person2 = new Person(wallets2);

        assertEquals(10, InterestCalc.calculateInterestOnWallet(person1.getWallets().get(0)));
        assertEquals(15, InterestCalc.calculateInterestOnWallet(person2.getWallets().get(0)));
        assertEquals(10, InterestCalc.calculateInterestOnPerson(person1));
        assertEquals(15, InterestCalc.calculateInterestOnPerson(person2));
    }

    List<Card> createCards(int numberOfCards, int startingBalance, int incrementAmount, CardType cardType) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < numberOfCards; i++) {
            Card newCard = new Card();
            newCard.setCardType(cardType);
            newCard.setBalance(startingBalance + ((long) incrementAmount * i));

            cards.add(newCard);
        }

        return cards;
    }
}