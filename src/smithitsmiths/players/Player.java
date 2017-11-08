package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import bases.scenes.SceneManager;
import smithitsmiths.GaugeBar;
import smithitsmiths.Platform;
import smithitsmiths.scenes.GameOverScene;
import tklibs.SpriteUtils;

public class Player extends GameObject implements PhysicsBody {
    public Vector2D velocity;
    private final float GRAVITY = 1f;

    private BoxCollider boxCollider;

    public GaugeBar gaugeBar;


    public boolean isDragged;

    Hammer hammer;
    PlayerHammerDown playerHammerDown;
    protected float force = 0;
    final static float maxForce = 15f;
    public static float currentForce;
    public float Damage;

    public Player() {
        super();
        isActive = true;
        isDragged = false;
        this.renderer = ImageRenderer.create("assets/images/players/running/Smith_00000.png");
        this.renderer = new Animation(
                5,
                false,
                false,
                SpriteUtils.loadImage("assets/images/players/running/Smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/running/Smith_00007.png")
        );

        velocity = new Vector2D();
        boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);
        gaugeBar = GameObject.recycle(GaugeBar.class);
        //GameObject.add(gaugeBar);
        this.children.add(gaugeBar);

        playerHammerDown = new PlayerHammerDown();
        hammer = new Hammer();

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        BoxCollider boxColliderAtBottom = this.boxCollider.shift(0, 1);
        boolean onAir = true;
        if (Physics.collideWith(boxColliderAtBottom, Platform.class) != null) {
            onAir = false;
        }

        //GRAVITY impact velocity && Inputs
        this.velocity.y += GRAVITY;

        //gaugebar update:
        gaugeBar.setPosition(this.position.x - 20, this.position.y - 40);


        if (InputManager.instance.spacePressed && !onAir) {
            if (force <= maxForce) {
                force += 0.4f;
                Damage = force + hammer.getCurrentHammerDamage();
                gaugeBar.setValue(Damage);

                return currentForce = force;

            }
        }

        if (InputManager.instance.spaceReleased && !onAir) {
            //when player is at platform(not in the air), enable jump, vice versa
            velocity.y = - Damage;
            System.out.println("Damage caused: " + Damage);
            force = 0;
            gaugeBar.reset();

            //smash Hammer
            playerHammerDown.run(this);

            InputManager.spaceReleased = false;
        }

        //Platform physics
        moveVertical();
        //moveHorizontal will be handled by Platformer

        this.position.addUp(velocity);
        this.screenPosition.addUp(velocity);

        checkIfOutOfScreen();
        //TODO : repositioning

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


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public float getDamage(){ return this.Damage; }

    public void getHit() {
        SceneManager.changeScene(new GameOverScene());
    }

    public void checkIfOutOfScreen(){
        if (this.position.x <= -15 || this.position.y >= 768){
            SceneManager.changeScene(new GameOverScene());
        }
    }

    private void moveHorizontal() {
        //unused
    }



}
