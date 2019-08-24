package com.dantech.escape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.Constants;
import com.dantech.escape.utilities.DrawingUtil;

// PLATFORM HEAD COLLISION NEEDS IMPLEMENTING

public class Player {

    public static String TAG = Player.class.getName();

    public Vector2 position;
    public Vector2 spawnLocation;
    Vector2 previousFramePosition;
    Vector2 velocity;
    PlayerFacing facing;
    JumpState jumpState;
    WalkState walkState;
    WallCollisionState collisionState;
    boolean headCollision;

    long jumpStartTime;
    long walkStartTime;

    public Player(Vector2 spawnLocation){
        this.spawnLocation = spawnLocation;
        position = new Vector2();
        previousFramePosition = new Vector2();
        velocity = new Vector2();
        init();
    }

    public void init(){
        position.set(spawnLocation);
        previousFramePosition.set(spawnLocation);
        facing = PlayerFacing.RIGHT;
        jumpState = JumpState.FALLING;
        walkState = WalkState.NOT_WALKING;
        collisionState = WallCollisionState.NO_COLLISION;
        headCollision = false;
    }

    public void update(float delta, Array<HollowPlatform> hollowPlatforms, Array<SolidPlatform> solidPlatforms, Array<Hazard> hazards){
        previousFramePosition.set(position);
        velocity.y -= Constants.GRAVITY * delta;
        position.mulAdd(velocity,delta);

        if (position.y < -150){
            init();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && collisionState != WallCollisionState.LEFT_SIDE_PLAYER_COLLISION) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && collisionState != WallCollisionState.RIGHT_SIDE_PLAYER_COLLISION) {
            moveRight(delta);
        } else {
            walkState = walkState.NOT_WALKING;
        }

        for (Hazard hazard: hazards){
            if(checkHazardCollision(hazard)){
                init();
            }
        }


        for (SolidPlatform platform: solidPlatforms){
            if(checkSolidPlatformLeftCollision(platform)){
                collisionState = WallCollisionState.RIGHT_SIDE_PLAYER_COLLISION;
                position.x = platform.left - (Constants.PLAYER_FEET_LENGTH+2);
            }
            else if(checkSolidPlatformRightCollision(platform)){
                collisionState = WallCollisionState.LEFT_SIDE_PLAYER_COLLISION;
                position.x = platform.right;
            } else {
                collisionState = WallCollisionState.NO_COLLISION;
            }
        }

        if (jumpState != JumpState.JUMPING) {
            jumpState = JumpState.FALLING;

            for (HollowPlatform platform : hollowPlatforms) {
                if (checkPlatformLanding(platform)) {
                    velocity.y = 0;
                    jumpState = JumpState.GROUND;
                    position.y = platform.top;
                }
            }
            for (SolidPlatform platform : solidPlatforms){
                if (checkPlatformLanding(platform)){
                    velocity.y = 0;
                    jumpState = JumpState.GROUND;
                    position.y = platform.top;
                }
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            switch (jumpState) {
                case GROUND:
                    startJump();
                    break;
                case JUMPING:
                    midJump();
            }
        } else {
            finishJump();
        }
    }

    private void moveLeft(float delta){
        if (jumpState == JumpState.GROUND && walkState != WalkState.WALKING) {
        walkStartTime = TimeUtils.nanoTime();
    }
        position.x -= delta* Constants.PLAYER_MOVE_SPEED;
        facing = PlayerFacing.LEFT;
        walkState=walkState.WALKING;
    }

    private void moveRight(float delta){
        if (jumpState == JumpState.GROUND && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }

        position.x += delta* Constants.PLAYER_MOVE_SPEED;
        facing = PlayerFacing.RIGHT;
        walkState=walkState.WALKING;
    }


    private void startJump(){
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        midJump();
    }

    private void midJump(){
        if (jumpState == JumpState.JUMPING) {
        float jumpLength = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);
        if (jumpLength < Constants.MAX_JUMP_TIME) {
            velocity.y = Constants.JUMP_SPEED;
        } else {
            finishJump();
        }
    }
    }

    private void finishJump(){
        if (jumpState == JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }
    }

    //I THINK THIS IS BAD CODE, ONLY WORKS FOR SPIKES, IT IS BROKEN
    boolean checkHazardCollision(Hazard hazard){
        if((position.x + 11 >=hazard.left && position.x <= hazard.right)&&
                ((position.y+1 >= hazard.bottom && position.y +1 <= hazard.top)||
                (position.y+14 >=hazard.bottom && position.y+14<=hazard.top))){
            return true;
        }
        return false;
    }

    boolean checkPlatformLanding(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;
        if (previousFramePosition.y>= platform.top &&
                position.y < platform.top) {
            float leftFoot = position.x + Constants.PLAYER_FEET_LENGTH;
            float rightFoot = position.x;
            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);
            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }
        return leftFootIn || rightFootIn || straddle;
    }

    boolean checkSolidPlatformLeftCollision(SolidPlatform platform){
        if((position.x + Constants.PLAYER_FEET_LENGTH +2 >=platform.left) &&
                (position.x + Constants.PLAYER_FEET_LENGTH -1 <platform.left) &&
                (position.y -1 <= platform.top) &&
                (position.y+16> platform.bottom)&&
                (facing == PlayerFacing.RIGHT)
        ){
            return true;
        } else {
            return false;
        }
    }

    boolean checkSolidPlatformRightCollision(SolidPlatform platform){
        if((position.x + Constants.PLAYER_FEET_LENGTH -10 <= platform.right) &&
                (position.x + Constants.PLAYER_FEET_LENGTH-7>platform.right)&&
                (position.y -1 <= platform.top) &&
                (position.y+16> platform.bottom)&&
                (facing == PlayerFacing.LEFT)
        )
        {
            return true;
        } else {
            return false;
        }
    }

    boolean checkSolidPlatformHeadCollision(SolidPlatform platform){
        if((position.x + Constants.PLAYER_FEET_LENGTH -7 < platform.right) &&
                (position.x + Constants.PLAYER_FEET_LENGTH >platform.left) &&
                (position.y + Constants.PLAYER_FACE_HEIGHT +6> platform.bottom ) &&
                (position.y + Constants.PLAYER_FACE_HEIGHT +3< platform.bottom )
        ) {
            return true;
        } else{
            return false;
        }
    }

    public void render(SpriteBatch sb){
        TextureRegion region = Assets.instance.escapeAssets.standingRight;

        if(facing == PlayerFacing.LEFT) {
            if (jumpState != JumpState.GROUND) {
                region = Assets.instance.escapeAssets.jumpingLeft;
            } else if (walkState == WalkState.WALKING) {
                float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                region = (TextureRegion) Assets.instance.escapeAssets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
            } else if (walkState == WalkState.NOT_WALKING) {
                region = Assets.instance.escapeAssets.standingLeft;
            }
        } else {
                if (jumpState != JumpState.GROUND) {
                    region = Assets.instance.escapeAssets.jumpingRight;
                } else if (walkState == WalkState.WALKING) {
                    float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
                    region = (TextureRegion) Assets.instance.escapeAssets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                } else if (walkState == WalkState.NOT_WALKING) {
                    region = Assets.instance.escapeAssets.standingRight;
                }
        }

        DrawingUtil.drawTextureRegion(sb, region, position.x,position.y);
//        DrawingUtil.drawTextureRegion(sb,Assets.instance.escapeAssets.pixel, position.x,position.y);
    }

    enum PlayerFacing {
        LEFT,
        RIGHT
    }

    enum JumpState{
        GROUND,
        JUMPING,
        FALLING
    }

    enum WalkState{
        WALKING,
        NOT_WALKING
    }

    enum WallCollisionState{
        LEFT_SIDE_PLAYER_COLLISION,
        RIGHT_SIDE_PLAYER_COLLISION,
        NO_COLLISION
    }
}
