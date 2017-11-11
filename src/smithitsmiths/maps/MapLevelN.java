package smithitsmiths.maps;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.enemy.Bullet;
import smithitsmiths.enemy.Spike;

import java.util.ArrayList;
import java.util.Random;

public class MapLevelN implements Map {
    final int BASELAYER = 1;
    final int SECONDLAYER = 2;
    final int THIRDLAYER = 3;
    final int FOURTHLAYER = 4;
    final int FIFTHLAYER = 5;
    final int SIXTHLAYER = 6;
    Random r = new Random();
    int[] suitablePositions = new int[50];

    Platform lastPlatform = new Platform();
    ArrayList<Platform> basePlatforms = new ArrayList<>();
    ArrayList<GameObject> secondLayerObjects = new ArrayList<>();
    ArrayList<GameObject> thirdLayerObjects = new ArrayList<>();
    ArrayList<GameObject> fourthLayerObjects = new ArrayList<>();
    ArrayList<GameObject> fifthLayerObjects = new ArrayList<>();
    ArrayList<GameObject> sixthLayerObjects = new ArrayList<>();


    @Override
    public void init() {
        System.out.println("loading new map");

        //each platform is 30pixel wide, so suitablePositions is an array of x where each platform can placed on
        for (int i = 0; i < suitablePositions.length; i++) {
            suitablePositions[i] = 1054 + (i * 30);
        }


        //tang 1:
        generateBaseLayer();
        //cac tang tren
        generateUpperLayer(2);
        generateUpperLayer(3);
//        generateUpperLayer(4);
        generateUpperLayer(5);
    }

    @Override
    public void deinit() {
        for (Platform platform : basePlatforms) {
            platform.setActive(false);
        }
        basePlatforms.clear();
        for (GameObject object : secondLayerObjects) {
            object.setActive(false);
        }
        secondLayerObjects.clear();

        for (GameObject object : thirdLayerObjects) {
            object.setActive(false);
        }
        thirdLayerObjects.clear();
    }

    public void generateBaseLayer() {
        int firstPosition = 1054;
        for (int i = 0; i < 50; i++) {
            Platform platform = GameObject.recycle(Platform.class);
            platform.position.set(firstPosition, 600);
            firstPosition += platform.getBoxCollider().getWidth();
            basePlatforms.add(platform);
            if (i == 49) {
                //last position
                lastPlatform = platform;
            }
        }
    }

    public void generateUpperLayer(int layerOrder) {

        switch (layerOrder) {
            case SECONDLAYER:
                //tang2:
                /*
                sinh ngẫu nhiên các object
                tỉ lệ:
                30% ra platform: randomObject <30
                5% ra spike: 30 <= randomObject < 35
                20% ra enemy: 50 <= randomObject < 70
                1% ra bullet: 70 <= randomObject <71
                còn lại chưa tính
                 */
                //at single place of 50 places in a layer:
                for (int layerElement = 0; layerElement < suitablePositions.length; layerElement++) {
                    int randomObject = r.nextInt(101);
                    //platform:
                    if (randomObject < 30) {

                        //check if underLayer with specific position has any platform, if true => spawn:
                        for (Platform belowPlatform : basePlatforms) {
                            if (belowPlatform.position.isMatch(suitablePositions[layerElement], 600)) {
                                Platform secondLayerPlatform = GameObject.recycle(Platform.class);
                                secondLayerPlatform.position.set(suitablePositions[layerElement], 570);
                                secondLayerObjects.add(secondLayerPlatform);
                            }
                        }

                    }
                    //spike:
                    else if (randomObject >= 30 && randomObject < 35) {
                        Spike secondLayerSpike = GameObject.recycle(Spike.class);
                        secondLayerSpike.position.set(suitablePositions[layerElement], 570);
                        secondLayerObjects.add(secondLayerSpike);
                    }
                    //spike

                    //bullet:
                    else if (randomObject >= 70 && randomObject < 71) {
                        Bullet secondLayerBullet = GameObject.recycle(Bullet.class);
                        secondLayerBullet.position.set(suitablePositions[layerElement], 570);
                        secondLayerObjects.add(secondLayerBullet);
                    }
                }
                break;

            case THIRDLAYER:
                //tang3:
                /*
                sinh ngẫu nhiên các object
                tỉ lệ:
                20% ra platform: randomObject <20
                20% ra spike: 20 <= randomObject < 40
                10% ra enemy: 40 <= randomObject < 50
                còn lại chưa tính
                 */
                //at single place of 50 places in a layer:
                for (int layerElement = 0; layerElement < suitablePositions.length; layerElement++) {
                    int randomObject = r.nextInt(101);
                    //platform:
                    if (randomObject < 20) {

                        //check if underLayer with specific position has any platform, if true => spawn:
                        for (GameObject object : secondLayerObjects) {
                            if ((object.getClass().equals(Platform.class)) && (object.position.isMatch(suitablePositions[layerElement], 570))) {
                                Platform thirdLayerPlatform = GameObject.recycle(Platform.class);
                                thirdLayerPlatform.position.set(suitablePositions[layerElement], 540);
                                thirdLayerObjects.add(thirdLayerPlatform);
                            }
                        }
                    }
                    //spike:
                }
                break;
            case FOURTHLAYER:
                //leave space for onairPlatforms
                break;
            case FIFTHLAYER:
                //on air platform
                int airPlatformFirstPos = r.nextInt(50);
                int randomAirPlatformsWidth = r.nextInt(10) + 1;
                for (int i = 0; i < randomAirPlatformsWidth; i++){
                    Platform airPlatform = GameObject.recycle(Platform.class);
                    airPlatform.renderer = ImageRenderer.create("assets/images/platform/brickBrown.png");
                    airPlatform.position.set(suitablePositions[airPlatformFirstPos], 480);
                    airPlatformFirstPos++;
                    fifthLayerObjects.add(airPlatform);
                }
                break;
            case SIXTHLAYER:
                break;

        }

    }

    @Override
    public Platform getLastPlatform() {
        return lastPlatform;
    }
}
