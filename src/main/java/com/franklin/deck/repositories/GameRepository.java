package com.franklin.deck.repositories;

import com.franklin.deck.entities.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {}