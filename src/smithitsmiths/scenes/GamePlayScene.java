package smithitsmiths.scenes;

import bases.GameObject;
import bases.maps.Map;
import bases.scenes.Scene;
import smithitsmiths.backgrounds.Background;
import smithitsmiths.enemy.Bullet;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyJumping;
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

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        //4.Background

        GameObject.add(background);
        GameObject.add(background2);

        if (background.position.x <= 512) background2.position.x = 1536;
        if (background2.position.x <= 512) background.position.x = 1536;

        //1.Player


        //3.Platform

        MapSpawner mapSpawner = new MapSpawner();
        GameObject.add(mapSpawner);


        player.position.set(100, 50);
        GameObject.add(player);

    }

    public Player getPlayer(){
        return player;
    }
}
