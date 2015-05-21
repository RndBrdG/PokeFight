package com.pokefight.game;

import com.badlogic.gdx.Game;
import com.pokefight.gamescreens.battleScreen;
import com.pokefight.gamescreens.menuScreen;

public class PokeFight extends Game {

	@Override
	public void create() {
		setScreen(new battleScreen());
	}
	
}
