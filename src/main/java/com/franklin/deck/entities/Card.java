package com.franklin.deck.entities;

import com.franklin.deck.enums.CardTypeEnum;
import com.franklin.deck.enums.CardValueEnum;

public class Card {

    private CardTypeEnum type;
    private CardValueEnum value;

    public Card() {

    }

    public Card(CardTypeEnum type, CardValueEnum value) {
        this.type = type;
        this.value = value;
    }

    public CardTypeEnum getType() {
        return this.type;
    }

    public CardValueEnum getValue() {
        return this.value;
    }

    public int calculateValue() {
        switch (this.value) {
            case KING:
                return 13;
            case QUEEN:
                return 12;
            case JACK:
                return 11;
            case TEN:
                return 10;
            case NINE:
                return 9;
            case EIGHT:
                return 8;
            case SEVEN:
                return 7;
            case SIX:
                return 6;
            case FIVE:
                return 5;
            case FOUR:
                return 4;
            case THREE:
                return 3;
            case TWO:
                return 2;
            case ACE:
                return 1;

        }
        return 0;
    }
}
