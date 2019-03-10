package com.franklin.deck.enums;


public enum CardTypeEnum {
    HEART("Heart"),
    SPADE("Spade"),
    CLUB("Club"),
    DIAMOND("Diamond");

    private final String text;

    /**
     * @param text
     */
    CardTypeEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}