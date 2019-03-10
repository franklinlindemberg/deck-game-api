package com.franklin.deck.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.utils.CardConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(description = "Class representing a player.")
@Table(name = "players")
public class Player {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @ApiModelProperty(example = "Franklin", required = true)
    private String firstName;

    @NotEmpty
    @ApiModelProperty(example = "Guimaraes", required = true)
    private String lastName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="game_id", nullable = false)
    private Game game;

    @JsonIgnore
    @Convert(converter = CardConverter.class)
    @Column(columnDefinition="TEXT")
    private List<Card> cards;

    private int totalValue;

    public Player() {
        cards = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getTotalValue() {
        return this.totalValue;
    }

    public void addCard(Card card) {
        this.totalValue += card.calculateValue();
        cards.add(card);
    }

    public void validatePayload() throws BadRequestException {
        if (this.firstName == null || this.firstName.isEmpty()) {
            throw new BadRequestException("Invalid firstName");
        }

        if (this.lastName == null || this.lastName.isEmpty()) {
            throw new BadRequestException("Invalid firstName");
        }
    }
}
