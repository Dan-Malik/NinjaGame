package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dantech.escape.entities.Door;
import com.dantech.escape.entities.Fire;
import com.dantech.escape.entities.Gear;
import com.dantech.escape.entities.HollowPlatform;
import com.dantech.escape.entities.Platform;
import com.dantech.escape.entities.Player;
import com.dantech.escape.entities.SolidPlatform;
import com.dantech.escape.entities.Hazard;
import com.dantech.escape.entities.Spike;


public class Level {

    Viewport viewport;
    public int levelNumber;
    public Player player;
    public Array<HollowPlatform> hollowPlatforms;
    public Array<SolidPlatform> solidPlatforms;
    public Array<Hazard> hazards;
    public Door door;

    public Level(Viewport viewport){
        this.viewport = viewport;
        hollowPlatforms = new Array<HollowPlatform>();
        solidPlatforms = new Array<SolidPlatform>();
        hazards = new Array<Hazard>();
    }

    public void update(float delta){

        for (Hazard hazard: hazards){
            if (hazard.animated == true){
                hazard.update(delta);
            }
        }

        for (SolidPlatform platform:solidPlatforms){
            if(platform.moving==true){
                platform.update(delta);
            }
        }

        player.update(delta,levelNumber, hollowPlatforms, solidPlatforms, hazards, door);
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

        hazards.add(new Spike(new Vector2(40,47)));
        hazards.add(new Spike(new Vector2(52,47)));
        hazards.add(new Spike(new Vector2(64,47)));

        player = new Player(new Vector2(-140,50));
    }
}
