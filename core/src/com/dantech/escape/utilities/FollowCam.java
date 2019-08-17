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
        if(player.position.x>-70){
        cam.position.x = player.position.x;
        }
//        cam.position.y = player.position.y+25;
    }


}
