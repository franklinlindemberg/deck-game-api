package com.franklin.deck.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.franklin.deck.entities.Deck;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class DeckConverter implements AttributeConverter<Deck, String> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Deck deck) {

        String deckJson = null;
        try {
            deckJson = objectMapper.writeValueAsString(deck);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON writing error");
        }

        return deckJson;
    }

    @Override
    public Deck convertToEntityAttribute(String deckJSON) {

        Deck deck = null;
        try {
            deck = objectMapper.readValue(deckJSON, Deck.class);
        } catch (final IOException e) {
            System.out.println("JSON reading error");
        }

        return deck;
    }
}
