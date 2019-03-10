package com.franklin.deck.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.franklin.deck.entities.Card;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardConverter implements AttributeConverter<List<Card>, String> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Card> cards) {

        String cardsJson = null;
        try {
            cardsJson = objectMapper.writeValueAsString(cards);
        } catch (final JsonProcessingException e) {
            System.out.println("JSON writing error");
        }

        return cardsJson;
    }

    @Override
    public List<Card> convertToEntityAttribute(String cardsJSON) {

        List<Card> cards = null;
        try {
            cards = objectMapper.readValue(cardsJSON, ArrayList.class);
        } catch (final IOException e) {
            System.out.println("JSON reading error");
        }

        return cards;
    }
}
