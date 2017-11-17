package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.ActionRepeatForever;
import bases.actions.ActionSequence;
import bases.actions.ActionWait;
import smithitsmiths.maps.MapSpawner;

import java.util.Random;

public class EnemySpawner extends GameObject {
    public final int NORMAL_ENEMIES_EACH_MAP = 5;
    int spawnCount;
    int level;
    FrameCounter frameCounter = new FrameCounter(7200);
    int Lv = 1;
    int waitTime = 800;
    int waitTimeJump = 600;
    int waitTimeBullet = 4200;


    Random random = new Random();

    public EnemySpawner(){
        Action wait = new ActionWait(waitTime);
        Action waitJump = new ActionWait(waitTimeJump);
        Action waitBullet = new ActionWait(waitTimeBullet);
        Action spawnAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(1100, 0);
                enemy.boxCollider.setWidth(30);
                enemy.boxCollider.setHeight(30);
                enemy.setMoveSpeed(MapSpawner.getCurrentSpeed());
                enemy.HP = 10;
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
                jumping.position.set(1024, 0);
                jumping.boxCollider.setWidth(50);
                jumping.boxCollider.setHeight(50);
                jumping.setMoveSpeed(MapSpawner.getCurrentSpeed());
                jumping.HP = 15;
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
                bullet.boxCollider.setWidth(60);
                bullet.boxCollider.setHeight(30);
//                charging.HP = 15;
                return true;
            }

            @Override
            public void reset() {

            }
        };
        Action spawnBulletSequence = new ActionRepeatForever(new ActionSequence(waitBullet, spawnBulletAction));
        this.addAction(spawnBulletSequence);

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run() && Lv <= 10){
            frameCounter.reset();
            waitTime -= 50;
            waitTimeJump -= 240;
            waitTimeBullet -= 360;
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
