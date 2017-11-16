package smithitsmiths.maps;

import bases.GameObject;
import bases.Vector2D;
import smithitsmiths.Platform;

public class MapSpawner extends GameObject {
    public float eachMapSpeed;
    public final float SPEED_MULTIPLIER = 1.25f;
    public float speedIncreaseMap;      //= mapWidth =
    public float mapCount;


    static Map currentMap;
    static Map nextMap;
    static Map previousMap;

    public MapSpawner() {
        super();

//        speedIncreaseMap =
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        autoLoadNextMap(new MapLevel1());

        return 0;
    }

    public static void autoLoadNextMap(Map newMap) {      //changeMap
        //change speed of nextMap => change speed of currentMap also


        if (currentMap == null) {
            currentMap = newMap;
            currentMap.init();
        } else {
            if (currentMap.getLastPlatform().position.x <= 1069) {
                changeMap(new MapLevelN());
                previousMap = currentMap;
                currentMap = nextMap;
                currentMap.init();
                nextMap = null;
            }
            if (previousMap != null) {
                if (previousMap.getLastPlatform().position.x <= 0) {
                    previousMap.deinit();
                    previousMap = null;
                }
            }
        }
    }

    public static void changeMap(Map newMap) {

        nextMap = newMap;

    }


}
