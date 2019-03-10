package com.franklin.deck.entities;

import com.franklin.deck.enums.CardTypeEnum;
import com.franklin.deck.enums.CardValueEnum;

public class UndealtCards {
    private UndealtSuitCards heart;
    private UndealtSuitCards spade;
    private UndealtSuitCards club;
    private UndealtSuitCards diamond;

    public UndealtCards() {
        this.heart = new UndealtSuitCards();
        this.spade = new UndealtSuitCards();
        this.club = new UndealtSuitCards();
        this.diamond = new UndealtSuitCards();
    }

    public void incrementCount(CardTypeEnum type, CardValueEnum value) {
        switch (type) {
            case HEART:
                this.heart.incrementCount(value);
                break;
            case SPADE:
                this.spade.incrementCount(value);
                break;
            case CLUB:
                this.club.incrementCount(value);
                break;
            case DIAMOND:
                this.diamond.incrementCount(value);
                break;
        }
    }

    public UndealtSuitCards getHeart() {
        return this.heart;
    }

    public UndealtSuitCards getSpade() {
        return this.spade;
    }

    public UndealtSuitCards getClub() {
        return this.club;
    }

    public UndealtSuitCards getDiamond() {
        return this.diamond;
    }
}
