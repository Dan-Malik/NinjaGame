package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.JsonWriter;
import com.dantech.escape.utilities.Constants;

public class PreferenceManager {
    static Preferences prefs = Gdx.app.getPreferences(Constants.PREFERENCES_FILE);


    static public void showAllPrefs(){
        Gdx.app.log("Sound", String.valueOf(prefs.getBoolean("SoundEffects")));
        Gdx.app.log("Music", String.valueOf(prefs.getBoolean("MusicOn")));
        Gdx.app.log("Levels Unlocked", String.valueOf(prefs.getString("LevelsUnlocked")));
    }

    static public void setSoundEffectsOn(){
        prefs.putBoolean("SoundEffects",true);
        prefs.flush();
    }

    static public void setSoundEffectsOff(){
        prefs.putBoolean("SoundEffects",false);
        prefs.flush();
    }

    static public void setMusicOn(){
        prefs.putBoolean("MusicOn",true);
        prefs.flush();
    }

    static public void setMusicOff(){
        prefs.putBoolean("MusicOn",false);
        prefs.flush();
    }

    static public void setLevelUnlocked(int levelUnlocked){
        prefs.putInteger("LevelsUnlocked",levelUnlocked);
        prefs.flush();
    }

    static public boolean getSoundEffectsSettings(){
        return prefs.getBoolean("SoundEffects", true);
    }

    static public boolean getMusicSettings(){
        return prefs.getBoolean("MusicOn", true);
    }

    static public int getLevelsUnlocked(){
        //If prefs file doesn't exist default to 0
        //Check if it's zero at initialisation
        return prefs.getInteger("LevelsUnlocked", 0);
    }
}
