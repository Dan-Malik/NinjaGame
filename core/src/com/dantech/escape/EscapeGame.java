package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.dantech.escape.utilities.LevelGenerator;

public class EscapeGame extends Game {

	@Override
	public void create() {
		//If game state hasn't been saved, create default settings
		if(PreferenceManager.getLevelsUnlocked() == 0){
			PreferenceManager.setMusicOn();
			PreferenceManager.setSoundEffectsOn();
			PreferenceManager.setLevelUnlocked(1);
			PreferenceManager.showAllPrefs();
		}

		setScreen(new LevelScreen());
	}

}
