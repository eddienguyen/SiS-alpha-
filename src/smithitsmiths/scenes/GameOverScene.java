package smithitsmiths.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import bases.scenes.Scene;

public class GameOverScene implements Scene {
    @Override
    public void deinit() {

    }

    @Override
    public void init() {
        GameObject background = new GameObject();
        background.renderer = ImageRenderer.create("assets/images/background/blue_land.png");
        background.position.set(512,384);
        GameObject.add(background);
    }
}
