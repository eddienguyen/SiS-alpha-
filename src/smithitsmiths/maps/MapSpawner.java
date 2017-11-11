package smithitsmiths.maps;

import bases.GameObject;
import bases.Vector2D;
import smithitsmiths.Platform;

public class MapSpawner extends GameObject {
    static Map currentMap;
    static Map nextMap;
    static Map previousMap;

    public MapSpawner() {
        super();
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        autoLoadNextMap(new MapLevel1());

        return 0;
    }

    public static void autoLoadNextMap(Map newMap) {      //changeMap
        if (currentMap == null) {
            currentMap = newMap;
            currentMap.init();
        } else {
            if (currentMap.getLastPlatform().position.x <= 1054) {
                changeMap(new MapLevelN());
                previousMap = currentMap;
                currentMap = nextMap;
                currentMap.init();
            }
            if (previousMap != null){
                if (previousMap.getLastPlatform().position.x <= 0)
                previousMap.deinit();
            }
        }
    }

    public static void changeMap(Map newMap) {
        nextMap = newMap;
    }


}
