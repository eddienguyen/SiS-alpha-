package smithitsmiths.scenes;

import bases.GameObject;
import bases.maps.Map;
import bases.physics.Physics;
import bases.scenes.Scene;
import smithitsmiths.backgrounds.Background;
import smithitsmiths.enemy.Bullet;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyJumping;
import smithitsmiths.enemy.Spike;
import smithitsmiths.maps.MapLevel1;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePlayScene implements Scene {

    Player player = new Player();
    Spike spike1 = new Spike();
    Spike spike2 = new Spike();
    Spike spike3 = new Spike();
    Spike spike4 = new Spike();
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

        player.position.set(100, 50);

        GameObject.add(player);

        //2.Enemy


        spike1.getPosition().set(600, 50);
        spike2.getPosition().set(1200, 50);
        spike3.getPosition().set(1500, 50);
        spike4.getPosition().set(2000, 50);

//        GameObject.add(spike1);
//        GameObject.add(spike2);
//        GameObject.add(spike3);
//        GameObject.add(spike4);
//
//        GameObject.add(jumping);
//        GameObject.add(bullet);

        //3.Platform

//        Map map = Map.load("assets/maps/map_layer1.json");
//
//        map.generate();
//        GameObject.add(map);

        MapSpawner mapSpawner = new MapSpawner();
        GameObject.add(mapSpawner);
    }

    public Player getPlayer() {
        return player;
    }
}
