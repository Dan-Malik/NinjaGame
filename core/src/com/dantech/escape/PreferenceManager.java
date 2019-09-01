package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.dantech.escape.utilities.Constants;

public class PreferenceManager {

    static FileHandle file = Gdx.files.local("gameprefs/prefs.json");
    static JsonReader jsonReader;
    static JsonWriter jsonWriter;
    static JsonValue baseJson;

    /////

    static public void setSoundOn(){
        jsonReader = new JsonReader();
        baseJson = jsonReader.parse(file);

        if(!baseJson.get("sound").asBoolean()) {
            baseJson.get("sound").set(true);

            file.writeString("{\n" +
                    "  \"levelUnlocked\":" + baseJson.get("levelUnlocked").asInt() + ",\n" +
                    "  \"sound\":" + baseJson.get("sound").asBoolean() + "\n" +
                    "}", false);
        }
    }

    static public void setSoundOff(){
        jsonReader = new JsonReader();
        baseJson = jsonReader.parse(file);

        if(baseJson.get("sound").asBoolean()) {
            baseJson.get("sound").set(false);

            file.writeString("{\n" +
                    "  \"levelUnlocked\":" + baseJson.get("levelUnlocked").asInt() + ",\n" +
                    "  \"sound\":" + baseJson.get("sound").asBoolean() + "\n" +
                    "}", false);
        }
    }

    static public boolean getSoundSettings(){
        jsonReader = new JsonReader();
        baseJson = jsonReader.parse(file);
        return baseJson.getBoolean("sound");
    }

    static public void setLevelUnlocked(int levelUnlocked) {
        jsonReader = new JsonReader();
        baseJson = jsonReader.parse(file);

            baseJson.get("levelUnlocked").set(String.valueOf(levelUnlocked));

            file.writeString("{\n" +
                    "  \"levelUnlocked\":" + baseJson.get("levelUnlocked").asInt() + ",\n" +
                    "  \"sound\":" + baseJson.get("sound").asBoolean() + "\n" +
                    "}", false);
    }

    static public int getLevelsUnlocked(){
        jsonReader = new JsonReader();
        baseJson = jsonReader.parse(file);
        return baseJson.getInt("levelUnlocked");
    }

    ///////
}
