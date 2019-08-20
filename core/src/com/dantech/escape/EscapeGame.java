package com.dantech.escape;

import com.badlogic.gdx.Game;

public class EscapeGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}

}
