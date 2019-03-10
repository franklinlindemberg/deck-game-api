package com.franklin.deck.controllers;

import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.exceptions.UnauthorizedException;
import com.franklin.deck.utils.Authorization;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import com.franklin.deck.entities.Game;

@RestController
@RequestMapping("/v1/games/")
@Api(description = "Set of endpoints for manipulating Games.")
public class GameController extends BaseController {

    @GetMapping("/{id}")
    private Game get(@PathVariable("id") int id,
                     @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        return gameService.getById(id);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") int id,
                        @RequestHeader(value="token", defaultValue="deck") String token) throws NotFoundException, UnauthorizedException {
        Authorization.validate(token);
        gameService.delete(id);
    }

    @PutMapping("/")
    private int save(@RequestBody Game game,
                     @RequestHeader(value="token", defaultValue="deck") String token) throws BadRequestException, UnauthorizedException {
        Authorization.validate(token);
        game.validatePayload();
        gameService.saveOrUpdate(game);
        return game.getId();
    }
}