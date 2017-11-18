package smithitsmiths.maps;

import bases.GameObject;
import bases.Vector2D;
import smithitsmiths.Platform;

public class MapSpawner extends GameObject {
    public static float eachMapSpeed;
    public static final float SPEED_MULTIPLIER = 1.05f;

    static Map currentMap;
    static Map nextMap;
    static Map previousMap;

    public MapSpawner() {
        super();

        eachMapSpeed = 3;

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        autoLoadNextMap(new MapLevel1());

        return 0;
    }

    public static void autoLoadNextMap(Map newMap) {      //changeMap
        //change speed of currentMap => change speed of previousMap also


        if (currentMap == null) {
            currentMap = newMap;
            currentMap.init();
            currentMap.setEachPlatformSpeed(eachMapSpeed);

        } else {
            if (currentMap.getLastPlatform().position.x <= 1069) {
                changeMap(new MapLevelN());
                eachMapSpeed *= SPEED_MULTIPLIER;
                previousMap = currentMap;
                currentMap = nextMap;
                currentMap.init();
                previousMap.setEachPlatformSpeed(eachMapSpeed);
                currentMap.setEachPlatformSpeed(eachMapSpeed);
                System.out.println(eachMapSpeed);
//                currentMap.getLastPlatform().position.x -= SPEED_MULTIPLIER / 1.15f;
                nextMap = null;
            }
            if (previousMap != null) {
                if (previousMap.getLastPlatform().position.x <= -45) {
                    previousMap.deinit();
                    previousMap = null;
                }
            }
        }
    }

    public static void changeMap(Map newMap) {

        nextMap = newMap;

    }

    public static float getCurrentSpeed(){
        return eachMapSpeed;
    }


}
