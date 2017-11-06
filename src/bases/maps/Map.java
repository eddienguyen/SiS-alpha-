package bases.maps;

import bases.GameObject;
import bases.Vector2D;
import bases.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Map extends GameObject {
    private List<Layer> layers;
    public float firstPosition;
    public int width, height;

    public static Map load(String url){
        String mapContent = Utils.readTextFile(url);
        Gson gson = new Gson();
        return gson.fromJson(mapContent, Map.class);
    }



    public void setFirstPosition(float position){
        firstPosition = position;
    }

    public void generate() {
        if (layers.size() > 0){
            Layer layer = layers.get(0);
            layer.generate(firstPosition);
            layer.initMap();

            //get width and height
            this.width = layer.width;
            this.height = layer.height;
        }
    }

    public void move(){
        if (layers.size() > 0){
            Layer layer = layers.get(0);
            layer.move();
        }
    }

    public boolean isOutOfWorld(){
        if (layers.size() > 0){
            Layer layer = layers.get(0);
            if (layer.isOutOfWorld()){
                return true;
            }
        }
        return false;
    }

    @Override
    public float run(Vector2D parentPosition) {
        this.position.addUp(-2,0);

        return super.run(parentPosition);

    }


    /*
    TODO: get width, position, left,right position, setPosition(), run() for a Map
    Map could extends GameObject
    when Map run -> layer run -> mapComponents run. So disable each Platform:: run().
     */
}
