package smithitsmiths.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import bases.scenes.Scene;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class GameOverScene implements Scene {
    Clip playerDie;

    @Override
    public void deinit() {

    }

    @Override
    public void init() {
        playerDie = AudioUtils.loadSound("assets/sound effect/playerdie_01.wav");
        GameObject background = new GameObject();
        playerDie.start();
        background.renderer = ImageRenderer.create("assets/images/background/gameover_bg.png");
        background.position.set(512,384);
        background.screenPosition.set(512,384);

        GameObject.add(background);
    }
}
