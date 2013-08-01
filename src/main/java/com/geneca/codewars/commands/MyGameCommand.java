package com.geneca.codewars.commands;

import com.geneca.codewars.data.GameCommandObject;
import com.geneca.codewars.data.GameStatusObject;

/**
 * Actual implementation of the game logic - add your smarts here
 */
public class MyGameCommand extends MyAbstractGameCommand {

	@Override
	public GameCommandObject takeTurn(GameStatusObject gameStatus) {
		// bogus logic to convert game status to game command
		GameCommandObject gco = new GameCommandObject();
		gco.setData1(gameStatus.getData1() * 2);
		gco.setData2("Command for JavaCodeWars: " + gameStatus.getData2());
		gco.setList(gameStatus.getList());
		return gco;
	}

}
