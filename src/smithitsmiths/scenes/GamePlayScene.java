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
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePlayScene implements Scene {

    Player player = new Player();
    Enemy enemy = new Enemy();
    Enemy enemy1 = new Enemy();
    Enemy enemy2 = new Enemy();
    Enemy enemy3 = new Enemy();
    EnemyJumping jumping = new EnemyJumping();
    Bullet bullet = new Bullet();
    Background background = new Background();
    Background background2 = new Background();
    ScoreManager scoreManager = new ScoreManager();

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

        GameObject.add(background);
        GameObject.add(background2);


        //1.Player


        //3.Platform

        MapSpawner mapSpawner = new MapSpawner();
        GameObject.add(mapSpawner);


        player.position.set(100, 50);
        GameObject.add(player);

        GameObject.add(new EnemySpawner());
        GameObject.add(scoreManager);
    }

    public Player getPlayer(){
        return player;
    }
}
