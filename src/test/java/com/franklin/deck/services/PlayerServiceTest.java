package com.franklin.deck.services;

import com.franklin.deck.entities.Card;
import com.franklin.deck.entities.Game;
import com.franklin.deck.entities.Player;
import com.franklin.deck.enums.CardTypeEnum;
import com.franklin.deck.enums.CardValueEnum;
import com.franklin.deck.exceptions.BadRequestException;
import com.franklin.deck.exceptions.NotFoundException;
import com.franklin.deck.repositories.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    PlayerRepository playerRepositoryMock;

    @InjectMocks
    PlayerService playerService;

    private Game game = this.setupGame(1, "Test");
    private Player player = this.setupPlayer(1, "Franklin", "Guimaraes", game);
    private List<Player> players = new ArrayList<Player>() {{ add(player); }};
    private Card card = new Card(CardTypeEnum.CLUB, CardValueEnum.ACE);


    @Before
    public void setUp() throws Exception {
        game.setPlayers(players);

        when(playerRepositoryMock.findById(1)).thenReturn(Optional.of(player));
        when(playerRepositoryMock.existsById(1)).thenReturn(true);
    }

    @Test
    public void shouldCallFindByIdWhenGetById() throws NotFoundException {

        playerService.getById(1, 1);

        verify(playerRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void shouldCallSaveWhenSaveOrUpdate() throws BadRequestException {

        playerService.saveOrUpdate(player);

        verify(playerRepositoryMock, times(1)).save(player);
    }

    @Test
    public void shouldCallDeleteByIdWhenDelete() throws NotFoundException {

        playerService.delete(1, 1);

        verify(playerRepositoryMock, times(1)).delete(player);
    }

    @Test
    public void shouldAddCardWhenDealCard() throws NotFoundException {

        playerService.dealCard(1, 1, card);

        assertEquals(1, player.getCards().size());
        assertEquals(card, player.getCards().get(0));
        verify(playerRepositoryMock, times(1)).findById(1);
        verify(playerRepositoryMock, times(1)).save(player);
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