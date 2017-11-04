package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import jdk.internal.util.xml.impl.Input;

public class Player extends GameObject implements PhysicsBody {
    public Vector2D velocity;
    private final float GRAVITY = 1f;
    private final float JUMPSPEED = 10;
    protected float force = 0;
    private BoxCollider boxCollider;
    PlayerSmite playerSmite;
//    Hammer hammer;
    final static float maxForce = 3.5f;
    public static float currentForce;

    public GaugeBar gaugeBar;

    public boolean isDragged;


    public Player() {
        super();
        this.renderer = ImageRenderer.create("assets/images/players/player_walk1.png");

        velocity = new Vector2D();
        boxCollider = new BoxCollider(36, 45);
        this.children.add(boxCollider);

        gaugeBar = GameObject.recycle(GaugeBar.class);
        GameObject.add(gaugeBar);
        isDragged = false;
        playerSmite = new PlayerSmite();
//        hammer = new Hammer();
//        hammer.position.set(this.position);
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        //GRAVITY impact velocity && Inputs
        this.velocity.y += GRAVITY;

        //gaugebar update:
        gaugeBar.setPosition(this.position.x - 40, this.position.y - 40);
        System.out.println(this.position);

        if (InputManager.instance.spacePressed) {
            if (force <= maxForce) {
                gaugeBar.setValue(force);
                force += 0.1f;
                return currentForce = force;
            }

        }

        playerSmite.run(this);


        if (InputManager.instance.spaceReleased) {
            //when player is at platform(not in the air), enable jump, vice versa
            BoxCollider boxColliderAtBottom = this.boxCollider.shift(0, 1);
            if (Physics.collideWith(boxColliderAtBottom, Platform.class) != null) {
                velocity.y = -JUMPSPEED * force;
//                playerSmite.run(this);

            }
            force = 0;
            gaugeBar.reset();
        }

//        playerSmite.run(this);


        //Platform physics
        moveVertical();
        //moveHorizontal will be handled by Platformer

        this.position.addUp(velocity);
        this.screenPosition.addUp(velocity);
        return 0;


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
//        this.position.addUp(0, velocity.y);
//        this.screenPosition.addUp(0, velocity.y);

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
                } else {
                    shiftDistance += Math.signum(velocity.x );
                    this.position.addUp(Math.signum(velocity.x ), 0);
                }
            }
            //end move player continously towards platform


            //update velocity ()
            velocity.x = 0;
        }

        //velocity impact position
        this.position.addUp(velocity.x, 0);
        this.screenPosition.addUp(velocity.x, 0);

    }

    public void smite(){

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
