package smithitsmiths.maps;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;

import java.util.ArrayList;
import java.util.Random;

public class MapLevelN implements Map {
    Random r = new Random();
    int[] suitablePositions = new int[50];

    Platform lastPlatform = new Platform();
    ArrayList<Platform> platforms = new ArrayList<>();

    @Override
    public void init() {
        System.out.println("loading new map");

        //each platform is 30pixel wide, so suitablePositions is an array of x where each platform can placed on
        for (int i = 0; i < suitablePositions.length; i++) {
            suitablePositions[i] = 1054 + (i * 30);
        }


        //tang 1:
        generateBaseLayer();
        //TANG 2
        generateUpperLayer();

    }

    @Override
    public void deinit() {
        for (Platform platform : platforms) {
            platform.setActive(false);
        }
        platforms.clear();
    }

    public void generateBaseLayer() {
        int firstPosition = 1054;
        for (int i = 0; i < 50; i++) {
            Platform platform = GameObject.recycle(Platform.class);
            platform.position.set(firstPosition, 600);
            firstPosition += platform.getBoxCollider().getWidth();
            platforms.add(platform);
            if (i == 49) {
                //last position
                lastPlatform = platform;
            }
        }
    }

    public void generateUpperLayer() {
        int countPlatform = 0;

        int randomPlatforms = r.nextInt(3) + 8;
        for (; countPlatform < randomPlatforms; countPlatform++) {
            int randomIndexForPosX = r.nextInt(50);
            //spawnPlatformInSecondLayer
            Platform secondLayerPlatform = GameObject.recycle(Platform.class);
            secondLayerPlatform.position.set(suitablePositions[randomIndexForPosX], 570);
            platforms.add(secondLayerPlatform);
        }
    }

    @Override
    public Platform getLastPlatform() {
        return lastPlatform;
    }
}
