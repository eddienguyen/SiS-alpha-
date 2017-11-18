package smithitsmiths.maps;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.players.Player;

import java.util.ArrayList;

public class MapLevel1 implements Map {
    public float eachPlatformSpeed;
    Platform lastPlatform = new Platform();
    ArrayList<Platform> platforms = new ArrayList<>();
    @Override
    public void init() {
        System.out.println("loading map level1");
        //tang 1:
        int firstPosition = 0;
        for (int i = 0; i < 30; i++) {
            Platform platform = GameObject.recycle(Platform.class);
            platform.renderer = ImageRenderer.create("assets/images/platform/grassPlatform-01.png");
            platform.position.set(firstPosition, 600);
            firstPosition += 45;
            platforms.add(platform);
            if (i == 29) {
                //last position
                lastPlatform = platform;
            }
        }


    }

    @Override
    public void deinit() {
        for (Platform platform : platforms){
            platform.setActive(false);
        }
        platforms.clear();
    }

    @Override
    public Platform getLastPlatform() {
        return lastPlatform;
    }

    @Override
    public void setEachPlatformSpeed(float eachPlatformSpeed) {
        this.eachPlatformSpeed = eachPlatformSpeed;
        for (Platform platform : platforms){
            platform.setMoveSpeed(eachPlatformSpeed);
        }
    }
}
