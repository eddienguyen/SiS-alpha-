package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.PlayerAnimator;
import bases.scenes.SceneManager;
import smithitsmiths.GaugeBar;
import smithitsmiths.Platform;
import smithitsmiths.scenes.GameOverScene;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class Player extends GameObject implements PhysicsBody {
    public Vector2D velocity;
    private final float GRAVITY = 0.8f;

    private BoxCollider boxCollider;

    public GaugeBar gaugeBar;

    PlayerAnimator animator;

    Clip charging;
    Clip keepCharging;
    Clip jumping;
    int loop = 0;

    public boolean isDragged;

    public Hammer hammer;
    public PlayerHammerDown playerHammerDown;
    protected float force = 0;
    final static float maxForce = 20f;
    public static float currentForce;
    public float Damage;

    public Player() {
        super();
        isActive = true;
        isDragged = false;
        animator = new PlayerAnimator();
        this.renderer = animator;
        charging = AudioUtils.loadSound("assets/sound effect/charging_01.wav");
        keepCharging = AudioUtils.loadSound("assets/sound effect/keepcharging_01.wav");
        jumping = AudioUtils.loadSound("assets/sound effect/jump_01.wav");

        velocity = new Vector2D();
        boxCollider = new BoxCollider(30, 40);
        this.children.add(boxCollider);
        gaugeBar = GameObject.recycle(GaugeBar.class);
        this.children.add(gaugeBar);

        playerHammerDown = new PlayerHammerDown();
        hammer = new Hammer();

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        animator.run(this);

        //reposition if needed:
        if (position.x < 100 ){
            BoxCollider nextBoxCollider = this.boxCollider.shift(1, 0);
            Platform pf = Physics.collideWith(nextBoxCollider, Platform.class);

            if (pf != null){
                velocity.x = 0;
            } else {
                this.position.x += 0.5f;
            }
        } else velocity.x = 0;

        //on air check
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
                charging.start();
                return currentForce = force;

            }
            if (force >= maxForce){
                loop += 1;
                keepCharging.loop(loop);
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
            animator.changeAnimation(PlayerAnimator.smashAnimation);
            InputManager.spaceReleased = false;
            loop = 0;
            charging.stop();
            keepCharging.stop();
            keepCharging.setFramePosition(0);
            charging.setFramePosition(0);
            jumping.start();
            jumping.setFramePosition(0);
        }

        //Platform physics
        moveVertical();
        //moveHorizontal will be handled by Platformer

        this.position.addUp(velocity);
        this.screenPosition.addUp(velocity);

        checkIfOutOfScreen();

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

    void play(Clip clip){
        clip.setFramePosition(0); // reset con trỏ về đầu đoạn sound
        clip.start();
    }


}
