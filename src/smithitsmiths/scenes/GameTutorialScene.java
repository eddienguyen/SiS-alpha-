package smithitsmiths.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import bases.scenes.Scene;

public class GameTutorialScene implements Scene {
    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        GameObject tutorialPNG = new GameObject();
        tutorialPNG.renderer = ImageRenderer.create("assets/images/GUI/tutorial_1.png");
        tutorialPNG.position.set(512,384);
        tutorialPNG.screenPosition.set(512,384);
        GameObject.add(tutorialPNG);
    }
}
