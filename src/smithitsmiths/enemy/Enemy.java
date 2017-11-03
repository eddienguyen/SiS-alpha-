package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;

public class Enemy extends GameObject {
    private BoxCollider boxCollider;
    private Vector2D velocity;
    private final float GRAVITY = 1f;

    public Enemy() {
        this.renderer = ImageRenderer.create("assets/images/platform/green_square.png");
        velocity = new Vector2D();
        boxCollider = new BoxCollider(30,30);
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {

        moveHorizontal();
        moveVertical();
        this.velocity.y += GRAVITY;
        this.position.x -= 2;
        return super.run(parentPosition);
    }

    private void moveVertical() {

        //calculate future position(box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(0,velocity.y);

        Platform platform = Physics.collideWith(nextBoxCollider,Platform.class);
        if (platform != null){

            //move player continously towards platform
            boolean moveContinue = true;
            float shiftDistance = Math.signum(velocity.y);
            while (moveContinue){
                if (Physics.collideWith(this.boxCollider.shift(0,shiftDistance), Platform.class) != null){
                    moveContinue = false;
                }else {
                    shiftDistance += Math.signum(velocity.y);
                    this.position.addUp(0,Math.signum(velocity.y));
                }
            }

            //update velocity ()
            velocity.y = 0;
        }

        //velocity impact position
        this.position.addUp(0,velocity.y);
        this.screenPosition.addUp(0,velocity.y);
    }

    private void moveHorizontal() {

        //calculate future position(box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(velocity.x,0);

        Platform platform = Physics.collideWith(nextBoxCollider,Platform.class);
        if (platform != null){

            //move player continously towards platform
            boolean moveContinue = true;
            float shiftDistance = Math.signum(velocity.x);
            while (moveContinue){
                if (Physics.collideWith(this.boxCollider.shift(shiftDistance,0), Platform.class) != null){
                    moveContinue = false;
                    position.x -= platform.getSpeed();
                }else {
                    shiftDistance += Math.signum(velocity.x);
                    this.position.addUp(Math.signum(velocity.x),0);
                }
            }

            //update velocity ()
            velocity.x = 0;
        }

        //velocity impact position
        this.position.addUp(velocity.x,0);
        this.screenPosition.addUp(velocity.x,0);
    }
}
