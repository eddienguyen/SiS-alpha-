package bases.maps;

import bases.GameObject;
import smithitsmiths.Platform;

import java.util.*;

public class Layer {
    private List<Integer> data;
    private int height;
    private int width;



    @Override
    public String toString() {
        return "Layer{" +
                "data=" + data +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public void generate() {
        for (int titleY = height - 1; titleY > 0; titleY --){
            for (int titleX = width - 1; titleX > 0; titleX--){
                int mapData = data.get(titleY * width + titleX);
                /*
                TẦNG 1: platforms và ô trống
                 */
                if (mapData == 1){                                  //platform
                    Platform platform = new Platform();
                    platform.position.set(titleX*30, titleY*30);
                    GameObject.add(platform);
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
                else if (mapData == 2){                             //random object

                    Random rand = new Random() ;
                    int randomObject = rand.nextInt(101) ;

                    if (randomObject < 10){                         //platform
                        int belowMapData = data.get( (titleY+1) * width + titleX);
                        if (belowMapData == 1){

                            data.set(titleY * width + titleX,randomObject);
                            // if below spot == other platform, then spawn
                            Platform platform = new Platform();
                            platform.position.set(titleX*30, titleY*30);
                            GameObject.add(platform);
                        }

                    }
                    else if (randomObject >= 10 && randomObject <30){           //Spike
                    }
                    else if (randomObject >= 30 && randomObject < 50 ){         //Enemy
                    }
                    //từ 50 đến 100 chưa tính đến

                }
            }
        }

    }
}
