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
        GameObject bg = new GameObject();
        bg.position.set(512, 384);
        bg.screenPosition.set(512,384);
        bg.renderer = ImageRenderer.create("assets/images/background/gameover_bg.png");

        GameObject.add(bg);
    }
}
