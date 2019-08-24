package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dantech.escape.entities.Door;
import com.dantech.escape.entities.Fire;
import com.dantech.escape.entities.HollowPlatform;
import com.dantech.escape.entities.Platform;
import com.dantech.escape.entities.Player;
import com.dantech.escape.entities.SolidPlatform;
import com.dantech.escape.entities.Hazard;


public class Level {

    Player player;
    Array<HollowPlatform> hollowPlatforms;
    Array<SolidPlatform> solidPlatforms;
    Array<Hazard> hazards;
    Door door;

    public Level(){
        hollowPlatforms = new Array<HollowPlatform>();
        solidPlatforms = new Array<SolidPlatform>();
        hazards = new Array<Hazard>();
        initTestLevel();

    }

    public void update(float delta){

        for (Hazard hazard: hazards){
            if(hazard.animated == true){
                hazard.update(delta);
            }
        }
        player.update(delta, hollowPlatforms, solidPlatforms, hazards);
    }

    public void render(SpriteBatch sb){
        sb.begin();
        for(Platform platform: hollowPlatforms){
            platform.render(sb);
        }
        for(Platform platform: solidPlatforms){
            platform.render(sb);
        }
        for (Hazard hazard: hazards){
            hazard.render(sb);
        }

        door.render(sb);

        player.render(sb);
        sb.end();
    }

    void initTestLevel(){
        door = new Door(new Vector2(500,0));
        hollowPlatforms.add(new HollowPlatform(-50, 30, 71, 10, false));
        hollowPlatforms.add(new HollowPlatform(-100, 60, 71, 10, false));
        hollowPlatforms.add(new HollowPlatform(-50, 90, 71, 10, false));
        hollowPlatforms.add(new HollowPlatform(-100, 120, 71, 10, false));
        hollowPlatforms.add(new HollowPlatform(-50, 150, 71, 10, false));

        hollowPlatforms.add(new HollowPlatform(130, 30, 71, 10, false));

        solidPlatforms.add(new SolidPlatform(-270, 0, 800, 40,false));
        solidPlatforms.add(new SolidPlatform(-200, 300, 40, 400, false));
        solidPlatforms.add(new SolidPlatform(40,47,37,47,false));

        hazards.add(new Hazard(new Vector2(40,47)));
        hazards.add(new Hazard(new Vector2(52,47)));
        hazards.add(new Hazard(new Vector2(64,47)));

        player = new Player(new Vector2(-140,50));
    }
}
