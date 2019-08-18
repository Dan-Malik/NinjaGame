package com.dantech.escape.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


public class Constants {

    public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final float WORLD_SIZE = 256;
    public static final float MAP_LEFT_LIMIT = 0;

    //SPRITE STUFF

    public static final String TEXTURE_ATLAS = "images/escape.pack.atlas";
    public static final String STANDING_RIGHT = "standing-right";
    public static final String STANDING_LEFT = "standing-left";
    public static final String JUMPING_RIGHT = "jumping-right";
    public static final String JUMPING_LEFT = "jumping-left";
    public static final String WALKING_RIGHT_1 = "walk-1-right";
    public static final String WALKING_LEFT_1 = "walk-1-left";
    public static final String WALKING_RIGHT_2 = "walk-2-right";
    public static final String WALKING_LEFT_2 = "walk-2-left";
    public static final String WALKING_RIGHT_3 = "walk-3-right";
    public static final String WALKING_LEFT_3 = "walk-3-left";
    public static final String PLATFORM_SPRITE_1 = "platform";
    public static final String PLATFORM_SPRITE_2 = "platform2";
    public static final String SPIKE = "spike";
    public static final String ENEMY1 = "enemy";
    public static final String ENEMY2 = "enemy-2";
    public static final String FIRE_1 = "fire1";
    public static final String FIRE_2 = "fire2";
    public static final String FIRE_3 = "fire3";
    public static final String FIRE_4 = "fire4";
    public static final String FIRE_5 = "fire5";
    public static final String FIRE_6 = "fire6";
    public static final float FIRE_LOOP_TIME = 0.06f;



    public static final int PLATFORM_EDGE_SIZE = 3;
    public static final float PLAYER_FEET_LENGTH = 10;
    public static final float PLAYER_FACE_HEIGHT = 11 ;
//    public static Vector2 PLAYER_FACE_POSITION = new Vector2(6,4);
    public static final float WALK_LOOP_TIME = 0.10f;

    public static final float PLAYER_MOVE_SPEED = 96;
    public static final float JUMP_SPEED = 0.625f * WORLD_SIZE;
    public static final float MAX_JUMP_TIME = 0.25f;
    public static final float GRAVITY = 1000;


}