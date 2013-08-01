package com.geneca.codewars.commands;

import com.geneca.codewars.data.GameCommandObject;
import com.geneca.codewars.data.GameStatusObject;

/**
 * Expected interface for commands which want to play the game
 */
public interface GameCommandInterface {

	public GameCommandObject takeTurn(GameStatusObject gameStatus);
}
