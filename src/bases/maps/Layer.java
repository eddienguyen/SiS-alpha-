package bases.maps;

import bases.GameObject;
import smithitsmiths.Platform;
import smithitsmiths.Player;

import java.util.*;

public class Layer {
    private List<Integer> data;
    public int height;
    public int width;
    private final float SPEED = 2;
    public boolean outOfWorld;

    ArrayList<GameObject> mapComponents;


    @Override
    public String toString() {
        return "Layer{" +
                "data=" + data +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public void generate(float firstPos) {
        mapComponents = new ArrayList<>();

        for (int titleY = height - 1; titleY > 0; titleY--) {
            for (int titleX = width - 1; titleX > 0; titleX--) {

                int mapData = data.get(titleY * width + titleX);
                /*
                TẦNG 1: platforms và ô trống
                 */
                if (mapData == 1) {                                  //platform
                    Platform platform = new Platform();
                    platform.position.set(firstPos + (titleX * 30), titleY * 30);
//                    GameObject.add(platform);
                    mapComponents.add(platform);
                }
                /*
                các tầng bên trên
                sinh ngẫu nhiên các object tại ô có đánh số là 2
                tỉ lệ:
                10% ra platform: randomObject <10
                20% ra spike: 10 <= randomObject < 30
                20% ra enemy: 30 <= randomObject < 50
                còn lại chưa tính
                 */
                else if (mapData == 2) {                             //random object

                    Random rand = new Random();
                    int randomObject = rand.nextInt(101);

                    if (randomObject < 10) {                         //platform
                        int belowMapData = data.get((titleY + 1) * width + titleX);
                        if (belowMapData == 1) {

                            data.set(titleY * width + titleX, 1);
                            // if below spot == other platform, then spawn
                            Platform platform = new Platform();
                            platform.position.set(firstPos + (titleX * 30), titleY * 30);
                            //GameObject.add(platform);
                            mapComponents.add(platform);

                        }

                    } else if (randomObject >= 10 && randomObject < 30) {           //Spike
                    } else if (randomObject >= 30 && randomObject < 50) {         //Enemy
                    }
                    //từ 50 đến 100 chưa tính đến

                }
            }
        }

    }

    public void initMap(){
        GameObject.addAll(mapComponents);
        System.out.println(width*30);
    }

    public void move(){
        for (GameObject object : mapComponents){
            if (object instanceof Platform){
                Platform platform = (Platform) object;
                platform.velocity.x = -SPEED;
            }


        }
    }

    public float getSpeed() {
        return SPEED;
    }


    public boolean isOutOfWorld(){
        for (GameObject object : mapComponents){
            if (object.position.x > 768){
                return false;
            }
        }
        return true;
    }
//    public Platform getLastPlatform(){
//        for (int titleY = height - 1; titleY > 0; titleY--) {
//            for (int titleX = width - 1; titleX > 0; titleX--) {
//                if (titleY == (height - 1) && titleX == (width - 1)) {
//                    Platform platform = new Platform();
//                    platform.position.set(titleX * 30, titleY * 30);
//                    mapComponents.add(platform);
//                    return platform;
//                }
//            }
//        }
//        return null;
//    }

}
