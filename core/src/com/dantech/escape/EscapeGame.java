package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.dantech.escape.utilities.LevelGenerator;

public class EscapeGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}

}
