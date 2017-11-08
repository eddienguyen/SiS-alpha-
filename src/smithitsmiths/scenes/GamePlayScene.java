package smithitsmiths.scenes;

import bases.GameObject;
import bases.maps.Map;
import bases.scenes.Scene;
import smithitsmiths.backgrounds.Background;
import smithitsmiths.enemy.Bullet;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyJumping;
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

    @Override
    public void deinit() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        //4.Background

        GameObject.add(background);

        //1.Player

        player.position.set(100, 50);

        GameObject.add(player);

        //2.Enemy


        enemy.getPosition().set(600, 50);
        enemy1.getPosition().set(800, 50);
        enemy2.getPosition().set(1000, 50);
        enemy3.getPosition().set(1200, 50);

        GameObject.add(enemy);
        GameObject.add(enemy1);
        GameObject.add(enemy2);
        GameObject.add(enemy3);
        GameObject.add(jumping);
        GameObject.add(bullet);

        //3.Platform

        Map map = Map.load("assets/maps/map_layer1.json");

        map.generate();
        GameObject.add(map);

    }
}
