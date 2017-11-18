package smithitsmiths.items;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Hammer;
import smithitsmiths.players.Player;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class HammerPickUp extends GameObject implements PhysicsBody {
    public float moveSpeed;
    private final float GRAVITY = 0.98f;

    public static final int WOOD = 0;
    public static final int ROCK = 1;
    public static final int IRON = 2;
    public static final int GOLD = 3;
    public static final int DIAMOND = 4;

    public int material;

    public Vector2D velocity;

    public BoxCollider boxCollider;

    Clip pickUp;

    public HammerPickUp(int material) {
        super();
        this.boxCollider = new BoxCollider(32, 32);
        velocity = new Vector2D();
        pickUp = AudioUtils.loadSound("assets/sound effect/chipquest.wav");
        switch (material) {
            case WOOD:
                this.renderer = ImageRenderer.create("assets/images/hammer/hammer_wood.png");
                this.material = WOOD;
                break;
            case ROCK:
                this.renderer = ImageRenderer.create("assets/images/hammer/hammer_rock.png");
                this.material = ROCK;
                break;
            case IRON:
                this.renderer = ImageRenderer.create("assets/images/hammer/hammer_iron.png");
                this.material = IRON;
                break;
            case GOLD:
                this.renderer = ImageRenderer.create("assets/images/hammer/hammer_gold.png");
                this.material = GOLD;
                break;
            case DIAMOND:
                this.renderer = ImageRenderer.create("assets/images/hammer/hammer_diamond.png");
                this.material = DIAMOND;
                break;
        }
        this.children.add(boxCollider);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        updateMoveSpeed();
        moveHorizontal();
        moveVertical();

        this.velocity.y += GRAVITY;
        this.velocity.x = -moveSpeed;

        //when player hit this pickup:

        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if (player != null) {
            switch (this.material){
                case WOOD:
                    Hammer.changeHammer("wood");
                    break;
                case ROCK:
                    Hammer.changeHammer("rock");
                    break;
                case IRON:
                    Hammer.changeHammer("iron");
                    break;
                case GOLD:
                    Hammer.changeHammer("gold");
                    break;
                case DIAMOND:
                    Hammer.changeHammer("diamond");
                    break;
            }
            pickUp.start();
            this.isActive = false;
        }
        pickUp.setFramePosition(0);

        deactivateIfNeeded();
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
                } else {
                    shiftDistance += Math.signum(velocity.x);
                    this.position.addUp(Math.signum(velocity.x), 0);
                }
            }

            //update velocity ()
            velocity.x = 0;
        }

        //velocity impact position
        this.position.addUp(velocity.x, 0);
        this.screenPosition.addUp(velocity.x, 0);
    }

    public void updateMoveSpeed(){
        this.moveSpeed = MapSpawner.getCurrentSpeed();
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void deactivateIfNeeded(){
        if (this.position.x <= 0){
            this.isActive = false;
        }
    }
}
