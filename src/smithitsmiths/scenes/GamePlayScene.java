package smithitsmiths.scenes;

import bases.GameObject;
import bases.maps.Map;
import bases.scenes.Scene;
import smithitsmiths.HUD.ScoreManager;
import smithitsmiths.backgrounds.Background;
import smithitsmiths.enemy.Bullet;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyJumping;
import smithitsmiths.enemy.EnemySpawner;
import smithitsmiths.items.ItemSpawner;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePlayScene implements Scene {

    Player player = new Player();

    Background background = new Background();
    Background background2 = new Background();
    ScoreManager scoreManager = new ScoreManager(player);

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        //4.Background


        background.position.x = 512;//still unstable
        background.screenPosition.x = 512;//still unstable
        background.position.y = 768/2;
        background.screenPosition.y = 768/2;

        background2.position.x = 512+1024;//still unstable
        background2.screenPosition.x = 512+1024;//still unstable
        background2.position.y = 768/2;
        background2.screenPosition.y = 768/2;

        MapSpawner mapSpawner = new MapSpawner();
        player.position.set(100, 50);

        GameObject.add(background);
        GameObject.add(background2);
        GameObject.add(mapSpawner);
        GameObject.add(player);

        GameObject.add(new EnemySpawner());
        GameObject.add(new ItemSpawner());
        GameObject.add(scoreManager );
    }

    public Player getPlayer(){
        return player;
    }
}
