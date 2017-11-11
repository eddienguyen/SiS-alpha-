package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.enemy.EnemyJumping;

public class HammerSmite extends GameObject implements PhysicsBody {

    public BoxCollider boxCollider;
    public float duration;
    public Vector2D velocity;
    public float damage;

    public HammerSmite() {

        this.renderer = ImageRenderer.create("assets/images/hammer/hammerSmite.png");
        boxCollider = new BoxCollider(40, 30);
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {
        this.duration -= 1;
        this.position.addUp(-2, 0);                  // SPEED = -2, change with gameSpeed

        //check hit with Enemy
        Enemy enemy = Physics.collideWith(boxCollider, Enemy.class);
        if (enemy != null) {
            enemy.HP -= this.damage;
            enemy.getHit();
            this.isActive = false;
        }
        EnemyJumping jumping = Physics.collideWith(boxCollider, EnemyJumping.class);
        if (jumping != null) {
            jumping.HP -= this.damage;
            jumping.getHit();
            this.isActive = false;
        }

        //TODO: allow player to jump if hammer collide with platform

        deactiveIfNeeded();
        return super.run(parentPosition);
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
