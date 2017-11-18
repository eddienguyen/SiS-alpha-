package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.maps.Map;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;

public class AboBullet extends GameObject implements PhysicsBody{
    public BoxCollider boxCollider;
    public Vector2D velocity;
    final private float GRAVITY = 0.2f;
    private float moveSpeed = -8;
    public AboBullet(){
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/aboBullet.png");
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);

        velocity = new Vector2D();

        velocity.y = -4;

    }

    @Override
    public float run(Vector2D parentPosition) {
        this.velocity.y += GRAVITY;

        move();
        hitPlayer();

        return super.run(parentPosition);
    }


    private void hitPlayer() {
        Player playerHit = Physics.collideWith(this.boxCollider,Player.class);
        if (playerHit != null){
            playerHit.getHit();
        }
    }

    private void move() {
        //calculate future position(box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(0, velocity.y);

        Platform platform = Physics.collideWith(nextBoxCollider, Platform.class);
        if (platform == null){
            this.velocity.x = moveSpeed;
        }
        if (platform != null) {

//            //move player continously towards platform
//            boolean moveContinue = true;
//            float shiftDistance = Math.signum(velocity.y);
//            while (moveContinue) {
//                if (Physics.collideWith(this.boxCollider.shift(0, shiftDistance), Platform.class) != null) {
//                    moveContinue = false;
//                } else {
//                    shiftDistance += Math.signum(velocity.y);
//                    this.position.addUp(0, Math.signum(velocity.y));
//                }
//            }

            //update velocity ()
            this.velocity.x = -MapSpawner.getCurrentSpeed();
            velocity.y = 0;
        }

        //velocity impact position
        this.position.addUp(velocity.x,velocity.y);
        this.screenPosition.addUp(velocity.x, velocity.y);
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
