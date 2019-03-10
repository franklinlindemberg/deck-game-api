package com.franklin.deck.controllers;

import com.franklin.deck.services.GameService;
import com.franklin.deck.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    protected GameService gameService;
    protected PlayerService playerService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
