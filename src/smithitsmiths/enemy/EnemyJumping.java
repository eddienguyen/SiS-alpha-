package smithitsmiths.enemy;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.players.Player;

import java.util.function.BinaryOperator;

public class EnemyJumping extends Enemy implements PhysicsBody {
    private final float SPEED = -1;
    private final float JUMPSPEED = -20;
    FrameCounter frameCounter = new FrameCounter(60);


    public EnemyJumping() {
        /*
        set width and height for boxCollider,
        set position,
        get Image
        set HP
         */
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/enemy3.png");
        this.getPosition().set(1024, 0);
        this.boxCollider.setWidth(50);
        this.boxCollider.setHeight(50);
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        //onAir check, only jump when !onAir
        BoxCollider boxColliderAtBottom = this.boxCollider.shift(0, 1);
        boolean onAir = true;
        if (Physics.collideWith(boxColliderAtBottom, Platform.class) != null) {
            onAir = false;
        }
        if (!onAir){
            jump();
        }

        playerHit();
        deActiveIfNeeded();

        return 0;
    }

    private void deActiveIfNeeded() {
        if (this.position.x < 0) {
            this.isActive = false;
        }
    }

    private void jump() {
        if (frameCounter.run()) {
            frameCounter.reset();
            velocity.addUp(SPEED,JUMPSPEED);
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
