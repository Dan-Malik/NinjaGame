package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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

    public Level(){
        hollowPlatforms = new Array<HollowPlatform>();
        solidPlatforms = new Array<SolidPlatform>();
        hazards = new Array<Hazard>();

        initTestLevel();

    }

    public void update(float delta){

        for (Hazard hazard: hazards){
            if(hazard.animated == false){
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

        player.render(sb);
        sb.end();
    }

    void initTestLevel(){
        hollowPlatforms.add(new HollowPlatform(-50, 0, 71, 20, false));
        hollowPlatforms.add(new HollowPlatform(-100, -40, 1000, 30,false));
        solidPlatforms.add(new SolidPlatform(40,10,59,61,false));
        hazards.add(new Fire(new Vector2(0,0)));

        player = new Player(new Vector2(-40,20));
    }
}
