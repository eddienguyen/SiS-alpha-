package smithitsmiths.scenes;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import bases.scenes.Scene;
import smithitsmiths.HUD.GameOverScore;
import smithitsmiths.HUD.ScoreManager;
import smithitsmiths.players.Player;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class GameOverScene implements Scene {
    Clip playerDie;
    GameOverScore gameOverScore = new GameOverScore();

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        playerDie = AudioUtils.loadSound("assets/sound effect/playerdie_01.wav");
        GameObject background = new GameObject();
        AudioUtils.play(playerDie);
        background.renderer = ImageRenderer.create("assets/images/background/gameover_bg.png");
        background.position.set(512,384);
        background.screenPosition.set(512,384);
        GameObject.add(background);
        GameOverScore.add(gameOverScore);
    }
}
