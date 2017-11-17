package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.actions.Action;
import bases.actions.ActionWait;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.GaugeBar;
import smithitsmiths.Platform;
import smithitsmiths.players.Player;

public class Enemy extends GameObject implements PhysicsBody {
    public float moveSpeed;
    public BoxCollider boxCollider;
    public Vector2D velocity;
    private final float GRAVITY = 1f;
    public float HP;
    public float JUMPSPEED = -4;
    GaugeBar gaugeBar;

    public Enemy() {
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/enemyWalking.png");
        velocity = new Vector2D();
        boxCollider = new BoxCollider();
        this.children.add(boxCollider);
        gaugeBar = new GaugeBar();
        this.children.add(gaugeBar);

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        moveHorizontal();
        moveVertical();
        spikeChecker();

        gaugeBar.setPosition(this.position.x - 18, this.position.y - 40);
        gaugeBar.setValue(HP*3);
        playerHit();
        this.velocity.y += GRAVITY;
        this.velocity.x = -moveSpeed*2;
        return 0;
    }

    private void spikeChecker() {
        Spike spike = Physics.collideWith(this.boxCollider,Spike.class);
        if (spike != null){
            this.velocity.y += JUMPSPEED;
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

    private void moveHorizontal() {
        //calculate future position(box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(velocity.x, 0);

        Platform platform = Physics.collideWith(nextBoxCollider, Platform.class);
        if (platform != null) {

            //move player continously towards platform
            boolean moveContinue = true;
            float shiftDistance = Math.signum(velocity.x);
            while (moveContinue) {
                if (Physics.collideWith(this.boxCollider.shift(shiftDistance, 0), Platform.class) != null) {
                    moveContinue = false;
//                    position.x -= platform.getSpeed();
                } else {
                    shiftDistance += Math.signum(velocity.x);
                    this.position.addUp(Math.signum(velocity.x), 0);
                }
            }

            //update velocity ()
            velocity.x = 0;
//            velocity.x = -velocity.x ;
        }

        //velocity impact position
        this.position.addUp(velocity.x, 0);
        this.screenPosition.addUp(velocity.x, 0);
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    private void playerHit() {
        Player hitPlayer = Physics.collideWith(this.boxCollider, Player.class);
        if (hitPlayer != null) {
            hitPlayer.getHit();
        }
    }

    public void getHit() {
        if (this.HP <= 0){
            this.isActive = false;
        }
        System.out.println(String.format("enemy get hit, left %s HP",HP ));
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
