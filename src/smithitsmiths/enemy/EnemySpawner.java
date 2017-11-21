package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.ActionRepeatForever;
import bases.actions.ActionSequence;
import bases.actions.ActionWait;
import smithitsmiths.maps.Map;
import smithitsmiths.maps.MapSpawner;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;
import java.util.Random;

public class EnemySpawner extends GameObject {
    Clip rocket;
    public final int NORMAL_ENEMIES_EACH_MAP = 5;
    int spawnCount;
    int level;
    FrameCounter frameCounter = new FrameCounter(7200);
    int Lv = 1;
    int waitTime = 800;
    int waitTimeJump = 2600;
    int waitTimeBullet = 4200;
    int waitTimeAborigines = 200;

    int enemyHP;
    int jumpEnemyHP;


    Random random = new Random();

    public EnemySpawner() {
        rocket = AudioUtils.loadSound("assets/sound effect/rocket.wav");
        Action wait = new ActionWait(waitTime);
        Action waitJump = new ActionWait(waitTimeJump);
        Action waitBullet = new ActionWait(waitTimeBullet);
        Action waitAbo = new ActionWait(waitTimeAborigines);

        enemyHP = 5;
        jumpEnemyHP = 10;

        Action spawnAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(1100, 0);
                enemy.boxCollider.setWidth(30);
                enemy.boxCollider.setHeight(30);
                enemy.setMoveSpeed(MapSpawner.getCurrentSpeed());
                enemy.HP = enemyHP;
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action spawnSequence = new ActionRepeatForever(new ActionSequence(wait, spawnAction));
        this.addAction(spawnSequence);

        Action spawnJumpAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                EnemyJumping jumping = GameObject.recycle(EnemyJumping.class);
                jumping.position.set(1024 + 40 * MapSpawner.getCurrentSpeed(), 0);
                jumping.boxCollider.setWidth(50);
                jumping.boxCollider.setHeight(50);
                jumping.setMoveSpeed(MapSpawner.getCurrentSpeed());
                jumping.HP = jumpEnemyHP;
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action spawnJumpSequence = new ActionRepeatForever(new ActionSequence(waitJump, spawnJumpAction));
        this.addAction(spawnJumpSequence);

        Action spawnBulletAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                Bullet bullet = GameObject.recycle(Bullet.class);
                bullet.position.set(1024, 500);
                AudioUtils.play(rocket);
                bullet.boxCollider.setWidth(60);
                bullet.boxCollider.setHeight(30);
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action spawnBulletSequence = new ActionRepeatForever(new ActionSequence(waitBullet, spawnBulletAction));
        this.addAction(spawnBulletSequence);

        Action spawnAboAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                EnemyAborigines aborigines = GameObject.recycle(EnemyAborigines.class);
                aborigines.position.set(1024 + 40* MapSpawner.getCurrentSpeed(), 500);
                aborigines.boxCollider.setWidth(30);
                aborigines.boxCollider.setHeight(30);
                aborigines.setMoveSpeed(MapSpawner.getCurrentSpeed());
//                aborigines.HP = enemyHP;
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action spawnAboSequence = new ActionRepeatForever(new ActionSequence(waitAbo, spawnAboAction));
        this.addAction(spawnAboSequence);

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run() && Lv <= 10){
            frameCounter.reset();
            enemyHP +=10;
            jumpEnemyHP +=20;
            waitTime -= 50;
            waitTimeJump -= 130;
            waitTimeBullet -= 180;
            waitTimeAborigines -= 220;
            Lv++;
        }


        //spawn by framecounter:
//        if (frameCounter.run()) {
//
//            spawnByRow();
//            spawnCount++;
//
//            if (spawnCount >= NORMAL_ENEMIES_EACH_MAP) {
//                spawnCount = 0;
//                level++;
//                //spawnBoss(level);
//            }
//
//            frameCounter.reset();
//        }

        return 0;
    }

//    private void spawnByRow() {
//        //4 layers's objects
//        Enemy enemy = GameObject.recycle(Enemy.class);
//        enemy.boxCollider.setWidth(30);
//        enemy.boxCollider.setHeight(30);
//        enemy.HP = 10;
//        int randomSpawnY = random.nextInt(4) + 2;
//        switch (randomSpawnY) {
//            case 2:
//                enemy.position.set(1054, 570);
//                break;
//            case 3:
//                enemy.position.set(1054, 540);
//                break;
//            case 4:
//                enemy.position.set(1054, 510);
//                break;
//            case 5:
//                enemy.position.set(1054, 480);
//                break;
//        }
//    }
}
