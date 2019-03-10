package com.franklin.deck.services;

import com.franklin.deck.entities.Card;
import com.franklin.deck.entities.Player;
import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player getById(int gameId, int playerId) throws NotFoundException {
        if (playerRepository.existsById(playerId)) {
            Player player = playerRepository.findById(playerId).get();
            if (player.getGame().getId() != gameId) {
                throw new NotFoundException("Player not found");
            }
            return player;
        }
        else {
            throw new NotFoundException("Player not found");
        }
    }

    public void saveOrUpdate(Player player) throws BadRequestException {
        player.validatePayload();
        playerRepository.save(player);
    }

    public void delete(int gameId, int playerId) throws NotFoundException {
        if (playerRepository.existsById(playerId)) {
            Player player = playerRepository.findById(playerId).get();
            if (player.getGame().getId() != gameId) {
                throw new NotFoundException("Player not found");
            }
            playerRepository.delete(player);
        }
        else {
            throw new NotFoundException("Player not found");
        }
    }

    public void dealCard(int gameId, int playerId, Card card) throws NotFoundException {
        if (playerRepository.existsById(playerId)) {
            Player player = playerRepository.findById(playerId).get();
            if (player.getGame().getId() != gameId) {
                throw new NotFoundException("Player not found");
            }

            player.addCard(card);

            playerRepository.save(player);
        }
        else {
            throw new NotFoundException("Player not found");
        }
    }

    public List<Card> getCards(int gameId, int playerId) throws NotFoundException {
        if (playerRepository.existsById(playerId)) {
            Player player = playerRepository.findById(playerId).get();
            if (player.getGame().getId() != gameId) {
                throw new NotFoundException("Player not found");
            }

            return player.getCards();
        }
        else {
            throw new NotFoundException("Player not found");
        }
    }
}
