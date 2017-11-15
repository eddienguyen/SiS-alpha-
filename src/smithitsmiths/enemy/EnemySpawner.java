package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.ActionRepeatForever;
import bases.actions.ActionSequence;
import bases.actions.ActionWait;

import java.util.Random;

public class EnemySpawner extends GameObject {
    public final int NORMAL_ENEMIES_EACH_MAP = 5;
    int spawnCount;
    int level;
    FrameCounter frameCounter = new FrameCounter(200);

    Random random = new Random();

    public EnemySpawner(){
        Action wait = new ActionWait(1200);
        Action spawnAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(1024, 200);
                enemy.boxCollider.setWidth(30);
                enemy.boxCollider.setHeight(30);
                enemy.HP = 10;
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action spawnSequence = new ActionRepeatForever(new ActionSequence(wait, spawnAction));
        this.addAction(spawnSequence);
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

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

    private void spawnByRow() {
        //4 layers's objects
        Enemy enemy = GameObject.recycle(Enemy.class);
        enemy.boxCollider.setWidth(30);
        enemy.boxCollider.setHeight(30);
        enemy.HP = 10;
        int randomSpawnY = random.nextInt(4) + 2;
        switch (randomSpawnY) {
            case 2:
                enemy.position.set(1054, 570);
                break;
            case 3:
                enemy.position.set(1054, 540);
                break;
            case 4:
                enemy.position.set(1054, 510);
                break;
            case 5:
                enemy.position.set(1054, 480);
                break;
        }
    }
}
