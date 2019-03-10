package com.franklin.deck.controllers;

import java.util.Comparator;
import java.util.List;

import com.franklin.deck.entities.Card;
import com.franklin.deck.entities.Game;
import com.franklin.deck.entities.Player;
import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.exceptions.InvalidActionException;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.exceptions.UnauthorizedException;
import com.franklin.deck.utils.Authorization;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/games/{gameId}/players/")
@Api(description = "Set of endpoints formmanipulating Players.")
public class PlayerController extends BaseController {

    @GetMapping("/")
    private List<Player> getAll(@PathVariable("gameId") int gameId,
                                @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Game game = gameService.getById(gameId);

        List<Player> players = game.getPlayers();
        players.sort(Comparator.comparingInt(Player::getTotalValue).reversed());

        return players;
    }

    @GetMapping("/{playerId}")
    private Player get(@PathVariable("gameId") int gameId,
                       @PathVariable("playerId") int id,
                       @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        return playerService.getById(gameId, id);
    }

    @DeleteMapping("/{playerId}")
    private void delete(@PathVariable("gameId") int gameId,
                        @PathVariable("playerId") int id,
                        @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        playerService.delete(gameId, id);
    }

    @PutMapping("/")
    private int save(@PathVariable("gameId") int gameId,
                     @RequestBody Player player,
                     @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, BadRequestException, UnauthorizedException {
        Authorization.validate(token);
        Game game = gameService.getById(gameId);

        player.setGame(game);

        playerService.saveOrUpdate(player);
        return player.getId();
    }


    @GetMapping("/{playerId}/cards")
    public List<Card> getCards(@PathVariable int gameId,
                               @PathVariable int playerId,
                               @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        return this.playerService.getCards(gameId, playerId);
    }
    @PostMapping("/{playerId}/deal")
    public void dealCard(@PathVariable int gameId,
                         @PathVariable int playerId,
                         @RequestHeader(value="token", defaultValue="deck") String token) throws InvalidActionException, NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        Card card = this.gameService.dealCard(gameId);

        if (card == null) {
            throw new InvalidActionException("Game deck is empty");
        }

        this.playerService.dealCard(gameId, playerId, card);
    }

}
