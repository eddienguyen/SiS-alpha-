package smithitsmiths.maps;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.enemy.Spike;

import java.util.ArrayList;
import java.util.Random;

public class MapLevelN implements Map {
    public float eachPlatformSpeed;
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
            suitablePositions[i] = 1024 + (i * 45);
        }


        //tang 1:
        generateBaseLayer();
        //cac tang tren
        generateUpperLayer(2);
        generateUpperLayer(3);
        generateUpperLayer(4);
        generateUpperLayer(6);
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

        for (GameObject object : fourthLayerObjects) {
            object.setActive(false);
        }
        fourthLayerObjects.clear();

        for (GameObject object : fifthLayerObjects){
            object.setActive(false);
        }
        fifthLayerObjects.clear();

        for (GameObject object : sixthLayerObjects) {
            object.setActive(false);
        }
        sixthLayerObjects.clear();
    }

    public void generateBaseLayer() {
        int firstPosition = 1069;
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
                2% ra spike: 30 <= randomObject < 32
                 */
                //at single place of 50 places in a layer:
                for (int layerElement = 0; layerElement < suitablePositions.length; layerElement++) {
                    int randomObject = r.nextInt(101);
                    //platform:
                    if (randomObject < 90) {

                        //check if underLayer with specific position has any platform, if true => spawn:
                        for (Platform belowPlatform : basePlatforms) {
                            if (belowPlatform.position.isMatch(suitablePositions[layerElement], 600)) {
                                Platform secondLayerPlatform = GameObject.recycle(Platform.class);
                                secondLayerPlatform.renderer = ImageRenderer.create("assets/images/platform/dirt_grass.png");
                                secondLayerPlatform.position.set(suitablePositions[layerElement], 555);
                                secondLayerObjects.add(secondLayerPlatform);
                            }
                        }

                    }
                    //spike:
                    else if (randomObject >= 90 && randomObject < 101) {
                        for (Platform belowPlatform : basePlatforms) {
                            if (belowPlatform.position.isMatch(suitablePositions[layerElement], 600)) {
                                Spike secondLayerSpike = GameObject.recycle(Spike.class);
                                secondLayerSpike.position.set(suitablePositions[layerElement], 555);
                                secondLayerObjects.add(secondLayerSpike);
                            }
                        }
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
                                thirdLayerPlatform.renderer = ImageRenderer.create("assets/images/platform/dirt_grass.png");
                                thirdLayerPlatform.position.set(suitablePositions[layerElement], 510);
                                thirdLayerObjects.add(thirdLayerPlatform);
                            }
                        }
                    }
                    //spike:
                }
                break;
            case FOURTHLAYER:
                //leave space for onairPlatforms

            case FIFTHLAYER:
                //leave space for onAirPlatform

                break;
            case SIXTHLAYER:
                //on air platform
                int airPlatformFirstPos = r.nextInt(39);
                int randomAirPlatformsWidth = r.nextInt(10) + 1;
                for (int i = 0; i < randomAirPlatformsWidth; i++) {
                    Platform airPlatform = GameObject.recycle(Platform.class);
                    airPlatform.renderer = ImageRenderer.create("assets/images/platform/brickBrown.png");
                    airPlatform.position.set(suitablePositions[airPlatformFirstPos], 375);
                    airPlatformFirstPos++;
                    sixthLayerObjects.add(airPlatform);
                }
                break;
        }

    }

    @Override
    public Platform getLastPlatform() {
        return lastPlatform;
    }
}
