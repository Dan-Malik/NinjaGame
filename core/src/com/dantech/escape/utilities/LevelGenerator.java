package com.dantech.escape.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dantech.escape.Level;
import com.dantech.escape.entities.Decoration;
import com.dantech.escape.entities.Door;
import com.dantech.escape.entities.Gear;
import com.dantech.escape.entities.HollowPlatform;
import com.dantech.escape.entities.Player;
import com.dantech.escape.entities.SolidPlatform;
import com.dantech.escape.entities.Spike;

public class LevelGenerator {

    public static Level generate(int levelNumber, Viewport viewport){

        //Accessing and parsing level json
        String filepath = "levels/level" + levelNumber + ".json";
        Level level = new Level(viewport);
        FileHandle levelFile = Gdx.files.internal(filepath);
        JsonReader jsonReader = new JsonReader();
        JsonValue baseJson = jsonReader.parse(levelFile);

        //Adding platforms from json platform child node
        JsonValue platformChild = baseJson.get("platforms");
        for(int i=0;i<platformChild.size;i++){
            boolean isMoving = false;
            if(platformChild.get(i).get("movement").get("time").asFloat() !=0){
                isMoving = true;
            }

            if(platformChild.get(i).get("type").asString().contains("solid")){
                SolidPlatform platform = new SolidPlatform(
                        platformChild.get(i).get("left").asFloat(),
                        platformChild.get(i).get("top").asFloat(),
                        platformChild.get(i).get("width").asFloat(),
                        platformChild.get(i).get("height").asFloat(),
                        isMoving);
                if (isMoving){
                    platform.setTravelTime(platformChild.get(i).get("movement").get("time").asLong());
                    platform.setLeftBound(platformChild.get(i).get("movement").get("leftBound").asFloat());
                    platform.setRightBound(platformChild.get(i).get("movement").get("rightBound").asFloat());
                }
                level.solidPlatforms.add(platform);
            } else if (platformChild.get(i).get("type").asString().contains("hollow")){
                HollowPlatform platform = new HollowPlatform(
                        platformChild.get(i).get("left").asFloat(),
                        platformChild.get(i).get("top").asFloat(),
                        platformChild.get(i).get("width").asFloat(),
                        platformChild.get(i).get("height").asFloat(),
                        isMoving);
                if (isMoving){
                    platform.setTravelTime(platformChild.get(i).get("movement").get("time").asLong());
                    platform.setLeftBound(platformChild.get(i).get("movement").get("leftBound").asFloat());
                    platform.setRightBound(platformChild.get(i).get("movement").get("rightBound").asFloat());
                }
                level.hollowPlatforms.add(platform);
            }
        }

        //Hazards
        JsonValue hazardsChild = baseJson.get("hazards");
        for(int i=0;i<hazardsChild.size;i++){
            if(hazardsChild.get(i).get("type").asString().contains("spike")){
                level.hazards.add(new Spike(new Vector2(
                        hazardsChild.get(i).get("x").asFloat(),
                        hazardsChild.get(i).get("y").asFloat()
                        ))
                );
            } else if(hazardsChild.get(i).get("type").asString().contains("gear")){
                level.hazards.add(new Gear(new Vector2(
                                hazardsChild.get(i).get("x").asFloat(),
                                hazardsChild.get(i).get("y").asFloat()
                        ))
                );
            }
        }

        //Decoration
        try {
            JsonValue decorationChild = baseJson.get("decoration");

            //Bricks
            for(int i=0;i<120;i++){
                if(i%5==1){
                    Decoration brick = new Decoration(new Vector2(MathUtils.random(-100,800),MathUtils.random(0,200)));
                    brick.setRegion(Assets.instance.decorationAssets.brick);
                    level.deco.add(brick);
                }else if(i%5==2){
                    Decoration bricks = new Decoration(new Vector2(MathUtils.random(-100,800),MathUtils.random(0,200)));
                    bricks.setRegion(Assets.instance.decorationAssets.bricks1);
                    level.deco.add(bricks);
                }else if(i%5==3){
                    Decoration bricks = new Decoration(new Vector2(MathUtils.random(-100,800),MathUtils.random(0,200)));
                    bricks.setRegion(Assets.instance.decorationAssets.bricks2);
                    level.deco.add(bricks);
                }else if(i%5==4){
                    Decoration bricks = new Decoration(new Vector2(MathUtils.random(-100,800),MathUtils.random(0,200)));
                    bricks.setRegion(Assets.instance.decorationAssets.bricks3);
                    level.deco.add(bricks);
                } else {
                    Decoration bricks = new Decoration(new Vector2(MathUtils.random(-100,800),MathUtils.random(0,200)));
                    bricks.setRegion(Assets.instance.decorationAssets.bricks4);
                    level.deco.add(bricks);
                }
            }

            for(int i=0;i<decorationChild.size;i++){

                //Windows
                if(decorationChild.get(i).get("type").asString().contains("window")){
                    Decoration decoPiece = new Decoration(new Vector2(
                            decorationChild.get(i).get("x").asFloat(),
                            decorationChild.get(i).get("y").asFloat()));
                    decoPiece.setRegion(Assets.instance.decorationAssets.window);
                    level.deco.add(decoPiece);
                }
                if(decorationChild.get(i).get("type").asString().contains("column")){
                    Decoration decoPiece = new Decoration(new Vector2(
                            decorationChild.get(i).get("x").asFloat(),
                            decorationChild.get(i).get("y").asFloat()));
                    decoPiece.setRegion(Assets.instance.decorationAssets.column);
                    level.deco.add(decoPiece);
                }


            }
        } catch(Exception ex){
            Gdx.app.log("No decoration","flop" );
        }

        //Level number
        level.levelNumber =levelNumber;

        //Door
        level.door = new Door(new Vector2(baseJson.get("door").get(0).asFloat(),baseJson.get("door").get(1).asFloat()));

        //Player
        level.player = new Player(new Vector2(baseJson.get("player").get(0).asFloat(),baseJson.get("player").get(1).asFloat()));

        return level;

     }
}
