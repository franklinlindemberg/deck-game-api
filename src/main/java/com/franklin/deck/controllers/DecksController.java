package com.franklin.deck.controllers;

import com.franklin.deck.entities.Deck;
import com.franklin.deck.entities.UndealtCards;
import com.franklin.deck.entities.UndealtCardsBySuit;
import com.franklin.deck.entities.Game;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.exceptions.UnauthorizedException;
import com.franklin.deck.utils.Authorization;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/games/{gameId}/decks/")
@Api(description = "Set of endpoints for manipulating Decks.")
public class DecksController extends BaseController {
    @GetMapping("/")
    public Deck get(@PathVariable int gameId,
                    @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Game game = this.gameService.getById(gameId);
        return game.getDeck();
    }

    @PutMapping("/")
    public void add(@PathVariable int gameId,
                    @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Deck deck = new Deck();
        deck.initializeCards();
        gameService.addDeck(gameId, deck);
    }

    @GetMapping("/count/undealt-cards-by-suit")
    public UndealtCardsBySuit countCardsBySuit(int gameId,
                                               @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Deck deck = this.gameService.getDeck(gameId);
        return deck.countUndealtCardsBySuit();
    }

    @GetMapping("/count/undealt-cards")
    public UndealtCards countCards(int gameId,
                                   @RequestHeader(value="token") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Deck deck = this.gameService.getDeck(gameId);
        return deck.countUndealtCards();
    }

    @PostMapping("/shuffle")
    public void shuflle(int gameId,
                        @RequestHeader(value="token") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        this.gameService.shuffleDeck(gameId);
    }
}
