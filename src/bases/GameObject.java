package bases;

import bases.renderers.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;

    protected Renderer renderer;

    protected ArrayList<GameObject> children;

    public static Vector<GameObject> gameObjects = new Vector<>();
    public static Vector<GameObject> newGameObjects = new Vector<>();

    protected boolean isActive;
    protected boolean isRenewing;

    public GameObject(){
        children = new ArrayList<>();
        isActive = true;

        position = new Vector2D();
        screenPosition = new Vector2D();
    }

    public static void add(GameObject object){
        newGameObjects.add(object);
    }

    public static void runAll(){

        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive){
                gameObject.run(new Vector2D(0,0));
            }
        }

        for (GameObject newGameObject : newGameObjects){
            //add PhysicsBody
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive && !gameObject.isRenewing){
                gameObject.render(g2d);
            }
        }
    }

    public static void clearAll(){
        gameObjects.clear();
        newGameObjects.clear();
    }

    /*
    each Object
     */

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }

    public void run(Vector2D parentPosition){
        screenPosition = parentPosition.add(position);
        isRenewing = false;
        for (GameObject child : children){
            if (child.isActive){
                child.run(screenPosition);
            }
        }
    }

    public Vector2D getPosition() {return position;}

    public Vector2D getScreenPosition() {return screenPosition;}

    public void setPosition(Vector2D position){
        if (position != null){
            this.position = position;
        }
    }

    public void reset(){
        this.isActive = true;
        this.isRenewing = true;
    }

    public void render(Graphics2D g2d){
        if (renderer != null){
            renderer.render(g2d, screenPosition);
        }

        for (GameObject child : children){
            if (child.isActive){
                child.render(g2d);
            }
        }
    }
}
