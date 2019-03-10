package com.franklin.deck.entities;

import com.franklin.deck.enums.CardTypeEnum;
import com.franklin.deck.enums.CardValueEnum;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void initializeCards() {
        cards = new ArrayList<>();

        for (CardTypeEnum type : CardTypeEnum.values()) {
            for (CardValueEnum value : CardValueEnum.values()) {
                cards.add(new Card(type, value));
            }
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void mergeDecks(Deck deck) {
        this.cards.addAll(deck.getCards());
    }

    public UndealtCardsBySuit countUndealtCardsBySuit() {
        UndealtCardsBySuit undealtCardsBySuit = new UndealtCardsBySuit();
        cards.forEach(x -> {
            undealtCardsBySuit.incrementCount(x.getType());
        });
        return undealtCardsBySuit;
    }

    public UndealtCards countUndealtCards() {
        UndealtCards undealtCards = new UndealtCards();
        cards.forEach(x -> {
            undealtCards.incrementCount(x.getType(), x.getValue());
        });
        return undealtCards;
    }
}
