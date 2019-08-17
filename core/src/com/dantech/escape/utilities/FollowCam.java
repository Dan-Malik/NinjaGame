package com.dantech.escape.utilities;

import com.badlogic.gdx.graphics.Camera;
import com.dantech.escape.entities.Player;

public class FollowCam {

    Camera cam;
    Player player;

    public FollowCam(Camera cam, Player player){
        this.cam = cam;
        this.player = player;
    }

    public void update() {
        if (player.position.x > Constants.MAP_LEFT_LIMIT) {
            cam.position.x = player.position.x;
        } else {
            cam.position.x = Constants.MAP_LEFT_LIMIT;
        }
        cam.position.y = Constants.WORLD_SIZE / 2 - Constants.WORLD_SIZE / 8;

    }

}
