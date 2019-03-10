package com.franklin.deck.services;

import java.util.ArrayList;
import java.util.List;
import com.franklin.deck.entities.Card;
import com.franklin.deck.entities.Deck;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.repositories.GameRepository;
import com.franklin.deck.utils.Shuffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.franklin.deck.entities.Game;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    private Shuffle shuffle = new Shuffle();

    public Game getById(int id) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            return gameRepository.findById(id).get();
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public void saveOrUpdate(Game game) {
        gameRepository.save(game);
    }

    public void delete(int id) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public void existsById (int id) throws NotFoundException {
        if(!gameRepository.existsById(id)) {
            throw new NotFoundException("Game not found");
        }
    }

    public void addDeck(int id, Deck deck) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            Game game = gameRepository.findById(id).get();

            game.addDeck(deck);

            gameRepository.save(game);
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public Deck getDeck(int id) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            Game game = gameRepository.findById(id).get();

            return game.getDeck();
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public void shuffleDeck(int id) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            Game game = gameRepository.findById(id).get();

            Deck deck = game.getDeck();

            if (deck != null) {
                shuffle.shuffleList(deck.getCards());
            }

            gameRepository.save(game);
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }

    public Card dealCard(int id) throws NotFoundException {
        if (gameRepository.existsById(id)) {
            Card card = null;
            Game game = gameRepository.findById(id).get();

            Deck deck = game.getDeck();
            if (deck != null) {
                List<Card> cards = deck.getCards();

                if (!cards.isEmpty()) {
                    card =  deck.getCards().remove(0);
                    gameRepository.save(game);
                }
            }

            return card;
        }
        else {
            throw new NotFoundException("Game not found");
        }
    }
}
