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
        for (int titleY = 0; titleY < height; titleY ++){
            for (int titleX = 0; titleX < width; titleX++){
                int mapData = data.get(titleY * width + titleX);

                if (mapData == 1){                                  //platform
                    Platform platform = new Platform();
                    platform.position.set(titleX*30, titleY*30);
                    GameObject.add(platform);
                }
                else if (mapData == 2){                             //random object
                    Random rand = new Random() ;
                    int randomObject = rand.nextInt(3);
                    if (randomObject == 1){                         //platform
                        //TODO: if below spot == other platform, then spawn
                        Platform platform = new Platform();
                        platform.position.set(titleX*30, titleY*30);
                        GameObject.add(platform);
                    }
                    else if (randomObject == 2){
                        System.out.println("spike");
                    }

                }
            }
        }

    }
}
