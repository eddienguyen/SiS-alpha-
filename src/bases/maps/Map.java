package bases.maps;

import bases.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Layer> layers;

    public static Map load(String url){
        String mapContent = Utils.readTextFile(url);
        Gson gson = new Gson();
        return gson.fromJson(mapContent, Map.class);
    }

    public void generate() {
        if (layers.size() > 0){
            Layer layer = layers.get(0);
            layer.generate();
        }
    }
}
