package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyAborigines;
import smithitsmiths.enemy.EnemyJumping;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class HammerSmite extends GameObject implements PhysicsBody {

    public BoxCollider boxCollider;
    public float duration;
    public float damage;
    Clip kill;

    public HammerSmite() {
        kill = AudioUtils.loadSound("assets/sound effect/hammering_01.wav");
        boxCollider = new BoxCollider(45, 32);
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {
        this.duration -= 1;
//        this.position.addUp(-2, 0);                  // SPEED = -2, change with gameSpeed

        checkHitWithEnemy();


        //TODO: allow player to jump if hammer collide with platform

        deactiveIfNeeded();
        return super.run(parentPosition);
    }

    private void checkHitWithEnemy() {
        if (isActive) {
            Enemy enemy = Physics.collideWith(boxCollider, Enemy.class);
            if (enemy != null) {
                enemy.HP -= this.damage;
                enemy.getHit();
                AudioUtils.play(kill);
                this.isActive = false;
            }
            EnemyJumping jumping = Physics.collideWith(boxCollider, EnemyJumping.class);
            if (jumping != null) {
                jumping.HP -= this.damage;
                jumping.getHit();
                AudioUtils.play(kill);
                this.isActive = false;
            }
            EnemyAborigines aborigines = Physics.collideWith(boxCollider, EnemyAborigines.class);
            if (aborigines != null) {
                aborigines.HP -= this.damage;
                aborigines.getHit();
                AudioUtils.play(kill);
                this.isActive = false;
            }

            kill.setFramePosition(0);
        }

    }


    private void deactiveIfNeeded() {
        if (this.duration <= 0) {
            this.isActive = false;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
