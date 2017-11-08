package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.Player;

import java.util.function.BinaryOperator;

public class EnemyJumping extends GameObject implements PhysicsBody{
    public Vector2D velocity;
    private final float GRAVITY = 1f;
    private final float SPEED = -2;
    private final float JUMPSPEED = -20;
    private BoxCollider boxCollider;
    FrameCounter frameCounter = new FrameCounter(50);


    public EnemyJumping(){
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/enemy3.png");
        this.getPosition().set(1024,0);
        velocity = new Vector2D();
        boxCollider = new BoxCollider(50, 50);
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {
        this.velocity.y += GRAVITY;
        moveVertical();
        playerHit();
        jump();
        deActiveIfNeeded();

        return super.run(parentPosition);
    }


    private void deActiveIfNeeded() {
        if (this.position.x < 0){
            this.isActive = false;
        }
    }

    private void jump() {
        if (frameCounter.run()){
            frameCounter.reset();
            velocity.y += JUMPSPEED;
            velocity.x += 0.5*SPEED;

        }
    }

    private void playerHit() {
        Player playerHit = Physics.collideWith(this.boxCollider,Player.class);
        if (playerHit != null){
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
        return null;
    }
}