package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.players.Player;

public class Spike extends GameObject implements PhysicsBody{
    public BoxCollider boxCollider;
    public Vector2D velocity;

    public Spike() {
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/spike.png");
        this.boxCollider = new BoxCollider(40, 18);
        velocity = new Vector2D();
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        playerHit();

        moveVertical();
        this.position.x -= 2;
        this.velocity.y += 2;
        return 0;
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    private void playerHit() {
        Player hitPlayer = Physics.collideWith(this.boxCollider, Player.class);
        if (hitPlayer != null) {
            hitPlayer.getHit();
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
        this.position.addUp(0, velocity.y);
        this.screenPosition.addUp(0, velocity.y);
    }


}
