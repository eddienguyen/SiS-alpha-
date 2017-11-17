package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.enemy.EnemyHP;
import smithitsmiths.Platform;
import smithitsmiths.players.Player;

public class EnemyJumping extends GameObject implements PhysicsBody {
    public Vector2D velocity;
    private final float GRAVITY = 1f;
    private float moveSpeed;
    private final float JUMPSPEED = -20;

    public BoxCollider boxCollider;
    FrameCounter frameCounter = new FrameCounter(100);
    public float HP;
    public EnemyHP enemyHP;

    public EnemyJumping() {
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/enemy3.png");
        this.getPosition().set(1024, 0);

        velocity = new Vector2D();

        boxCollider = new BoxCollider(50, 50);
        this.children.add(boxCollider);

        enemyHP = new EnemyHP();
        this.children.add(enemyHP);

    }

    @Override
    public float run(Vector2D parentPosition) {
        this.velocity.y += GRAVITY;
        this.velocity.x = -moveSpeed * 3 / 2;

        moveVertical();
        playerHit();
        jump();

        enemyHP.setPosition(this.position.x - 18, this.position.y - 40);
        enemyHP.setValue(HP * 2);

        deActiveIfNeeded();
        return super.run(parentPosition);
    }

    public void getHit() {
        if (this.HP <= 0) {
            this.isActive = false;
            this.enemyHP.setActive(false);
        } else {
            if (frameCounter.run()) {
                velocity.x -= 4 * moveSpeed;
                velocity.y += JUMPSPEED;
                frameCounter.reset();
            }
        }
    }


    private void deActiveIfNeeded() {
        if (this.position.x < 0) {
            this.isActive = false;
        }
    }

    private void jump() {
        if (frameCounter.run()) {
            frameCounter.reset();
            velocity.y += JUMPSPEED;
            if (velocity.x > 0) {
                velocity.x += -4 * moveSpeed;
            }

        }
    }

    private void playerHit() {
        Player playerHit = Physics.collideWith(this.boxCollider, Player.class);
        if (playerHit != null) {
            playerHit.getHit();
        }
    }

    private void moveVertical() {

        //calculate future position(box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(0, velocity.y);

        Platform platform = Physics.collideWith(nextBoxCollider, Platform.class);
        if (platform != null) {

            //move player continously towards platform
            boolean moveContinue = true;
            float shiftDistance = Math.signum(velocity.y);
            while (moveContinue) {
                if (Physics.collideWith(this.boxCollider.shift(0, shiftDistance), Platform.class) != null) {
                    moveContinue = false;
                } else {
                    shiftDistance += Math.signum(velocity.y);
                    this.position.addUp(0, Math.signum(velocity.y));
                }
            }

            //update velocity ()
            velocity.y = 0;
        }

        //velocity impact position
        this.position.addUp(velocity.x, velocity.y);
        this.screenPosition.addUp(velocity.x, velocity.y);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
