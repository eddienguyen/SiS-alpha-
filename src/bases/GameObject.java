package bases;

import bases.actions.Action;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    public Vector2D position;
    public Vector2D screenPosition;

    public Renderer renderer;

    protected ArrayList<GameObject> children;
    protected ArrayList<Action> actions;
    protected ArrayList<Action> newActions;
    protected boolean isActive;
    protected boolean isRenewing;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    /*
        *** EACH OBJECT ***
    */
    public GameObject() {
        children = new ArrayList<>();
        actions = new ArrayList<>();
        newActions = new ArrayList<>();

        position = new Vector2D();
        screenPosition = new Vector2D();
        isActive = true;
    }

    public float run(Vector2D parentPosition) {
        screenPosition = parentPosition.add(position);
        isRenewing = false;
        for (GameObject child : children) {
            if (child.isActive)
                child.run(screenPosition);
        }
//        System.out.println(gameObjects.size());
        return 0;
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, screenPosition);
        }

        for (GameObject child : children) {
            if (child.isActive)
                child.render(g2d);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
            this.position = position;
    }

    public void reset() {
        this.isActive = true;
        this.isRenewing = true;
        this.actions.clear();
        this.newActions.clear();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public GameObject setRenderer(Renderer renderer) {
        if (renderer != null)
            this.renderer = renderer;
        return this;
    }

    public static <T extends GameObject> T recycle(Class<T> cls) {
        for (GameObject object : gameObjects) {
            if (object.getClass().equals(cls)) {
                if (!object.isActive) {
                    object.isActive = true;
                    return (T) object;
                }
            }
        }
        try {
            T newGameObject = cls.newInstance();
            add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        *** ARRAY OF OBJECTS ***
    */
    public static void runAll() {

        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) {
                gameObject.run(new Vector2D(0, 0));
                gameObject.runActions();
            }
        }

        for (GameObject newGameObject : newGameObjects) {
            if (newGameObject instanceof PhysicsBody) {
                Physics.add((PhysicsBody) newGameObject);
            }
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && !gameObject.isRenewing)
                gameObject.render(g2d);
        }
    }

    public static void clearAll() {
        gameObjects.clear();
        newGameObjects.clear();

        Physics.clearAll();
        GameObjectPool.clearAll();
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public static void addAll(ArrayList arrayList) {
        newGameObjects.addAll(arrayList);
    }


    public static void runAllActions() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.runActions();
        }
    }

    private void runActions() {

        this.actions.removeIf(action -> action.run(this));

        this.actions.addAll(newActions);
        newActions.clear();
    }

    public void addAction(Action action) {
        newActions.add(action);
    }
}