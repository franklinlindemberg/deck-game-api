package com.franklin.deck.repositories;

import com.franklin.deck.entities.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {}