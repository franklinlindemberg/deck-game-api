package com.franklin.deck.services;

import com.franklin.deck.entities.Card;
import com.franklin.deck.entities.Deck;
import com.franklin.deck.entities.Game;
import com.franklin.deck.entities.Player;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.repositories.GameRepository;
import com.franklin.deck.utils.Shuffle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    GameRepository gameRepositoryMock;
    @Mock
    Shuffle shuffleMock;

    @InjectMocks
    GameService gameService;



    private Game game = this.setupGame(1, "Test");
    private Player player = this.setupPlayer(1, "Franklin", "Guimaraes", game);
    private List<Player> players = new ArrayList<Player>() {{ add(player); }};


    @Before
    public void setUp() throws Exception {
        game.setPlayers(players);

        when(gameRepositoryMock.findById(1)).thenReturn(Optional.of(game));
        when(gameRepositoryMock.existsById(1)).thenReturn(true);
    }

    @Test
    public void shouldCallFindByIdWhenGetById() throws NotFoundException {

        gameService.getById(1);

        verify(gameRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void shouldCallSaveWhenSaveOrUpdate() {

        gameService.saveOrUpdate(game);

        verify(gameRepositoryMock, times(1)).save(game);
    }

    @Test
    public void shouldCallDeleteByIdWhenDelete() throws NotFoundException {

        gameService.delete(1);

        verify(gameRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    public void shouldAddDeckWhenAddDeck() throws NotFoundException {
        Deck deck = new Deck();

        gameService.addDeck(1, deck);

        assertEquals(deck, game.getDeck());
        verify(gameRepositoryMock, times(1)).findById(1);
        verify(gameRepositoryMock, times(1)).save(game);
    }

    @Test
    public void shouldGetDeckWhenGetDeck() throws NotFoundException {
        Deck deck = new Deck();

        game.setDeck(deck);

        gameService.getDeck(1);

        assertEquals(deck, game.getDeck());
        verify(gameRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void shouldNotShuffleDeckWhenShuffleDeckAndDeckIsNull() throws NotFoundException {
        gameService.shuffleDeck(1);

        verify(shuffleMock, times(0)).shuffleList(null);
        verify(gameRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void shouldShuffleDeckWhenShuffleDeckAndDeckIsNotNull() throws NotFoundException {
        Deck deck = new Deck();

        game.setDeck(deck);

        gameService.shuffleDeck(1);

        verify(shuffleMock, times(1)).shuffleList(deck.getCards());
        verify(gameRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void shouldDealCardWhenDeckHasCard() throws NotFoundException {
        Deck deck = new Deck();
        deck.initializeCards();

        game.setDeck(deck);

        Card card = gameService.dealCard(1);

        assertNotNull(card);
        verify(gameRepositoryMock, times(1)).findById(1);
        verify(gameRepositoryMock, times(1)).save(game);
    }

    @Test
    public void shouldNotDealCardWhenDeckHasNoCard() throws NotFoundException {
        Deck deck = new Deck();

        game.setDeck(deck);

        Card card = gameService.dealCard(1);

        assertNull(card);
        verify(gameRepositoryMock, times(1)).findById(1);
        verify(gameRepositoryMock, times(0)).save(game);
    }

    private Game setupGame(int id, String name) {
        Game game = new Game();

        game.setId(id);
        game.setName(name);

        return game;
    }

    private Player setupPlayer(int id, String firstName, String lastName, Game game) {
        Player player = new Player();

        player.setId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setGame(game);

        return player;
    }
}