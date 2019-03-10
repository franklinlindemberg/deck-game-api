package com.franklin.deck.entities;

import com.franklin.deck.enums.CardValueEnum;

public class UndealtSuitCards {
    private short king;
    private short queen;
    private short jack;
    private short ten;
    private short nine;
    private short eight;
    private short seven;
    private short six;
    private short five;
    private short four;
    private short three;
    private short two;
    private short ace;

    public UndealtSuitCards() {
    }

    public void incrementCount(CardValueEnum value) {
        switch (value) {
            case KING:
                this.king++;
                break;
            case QUEEN:
                this.queen++;
                break;
            case JACK:
                this.jack++;
                break;
            case TEN:
                this.ten++;
                break;
            case NINE:
                this.nine++;
                break;
            case EIGHT:
                this.eight++;
                break;
            case SEVEN:
                this.seven++;
                break;
            case SIX:
                this.six++;
                break;
            case FIVE:
                this.five++;
                break;
            case FOUR:
                this.four++;
                break;
            case THREE:
                this.three++;
                break;
            case TWO:
                this.two++;
                break;
            case ACE:
                this.ace++;
                break;
        }
    }

    public short getKing() {
        return this.king;
    }

    public short getQueen() {
        return this.queen;
    }

    public short getJack() {
        return this.jack;
    }

    public short getTen() {
        return this.ten;
    }

    public short getNine() {
        return this.nine;
    }

    public short getEight() {
        return this.eight;
    }

    public short getSeven() {
        return this.seven;
    }

    public short getSix() {
        return this.six;
    }

    public short getFive() {
        return this.five;
    }

    public short getFour() {
        return this.four;
    }

    public short getThree() {
        return this.three;
    }

    public short getTwo() {
        return this.two;
    }

    public short getAce() {
        return this.ace;
    }
}
