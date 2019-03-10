package com.franklin.deck.entities;

import com.franklin.deck.enums.CardTypeEnum;

public class UndealtCardsBySuit {
    private short heart;
    private short spade;
    private short club;
    private short diamond;

    public UndealtCardsBySuit() {
    }

    public void incrementCount(CardTypeEnum type) {
        switch (type) {
            case HEART:
                this.heart++;
                break;
            case SPADE:
                this.spade++;
                break;
            case CLUB:
                this.club++;
                break;
            case DIAMOND:
                this.diamond++;
                break;
        }
    }

    public short getHeart() {
        return this.heart;
    }

    public short getSpade() {
        return this.spade;
    }

    public short getClub() {
        return this.club;
    }

    public short getDiamond() {
        return this.diamond;
    }
}
