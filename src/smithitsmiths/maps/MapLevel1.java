package smithitsmiths.maps;

import bases.GameObject;
import smithitsmiths.Platform;

import java.util.ArrayList;

public class MapLevel1 implements Map {
    Platform lastPlatform = new Platform();
    ArrayList<Platform> platforms = new ArrayList<>();
    @Override
    public void init() {
        System.out.println("loading map level1");
        //tang 1:
        int firstPosition = 0;
        for (int i = 0; i < 50; i++) {
            Platform platform = new Platform();
            platform.position.set(firstPosition, 600);
            firstPosition += platform.getBoxCollider().getWidth();
            platforms.add(platform);
            GameObject.add(platform);
            if (i == 49) {
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
    }

    @Override
    public Platform getLastPlatform() {
        return lastPlatform;
    }
}
