package com.franklin.deck.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.utils.DeckConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(description = "Class representing a game.")
@Table(name = "games")
public class Game {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(example = "Poker", required=true)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "game")
    private List<Player> players;

    @JsonIgnore
    @Convert(converter = DeckConverter.class)
    @Column(columnDefinition="TEXT")
    private Deck deck;

    public Game() {
        this.players = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void addDeck(Deck deck) {
        if (this.getDeck() == null) {
            this.setDeck(deck);
        } else {
            this.deck.mergeDecks(deck);
        }
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void validatePayload() throws BadRequestException {
        if (this.name == null || this.name.isEmpty()) {
            throw new BadRequestException("Invalid name");
        }
    }
}
