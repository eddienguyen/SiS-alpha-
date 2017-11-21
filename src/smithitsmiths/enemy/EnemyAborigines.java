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

public class EnemyAborigines extends GameObject implements PhysicsBody{
    public Vector2D velocity;
    private final float GRAVITY = 1f;
    public BoxCollider boxCollider;
    private float moveSpeed;


    boolean bulletDisabled;
    final int COOL_DOWN_TIME = 200;
    int coolDownTime;

    public EnemyAborigines(){
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/Aborigines.png");
        this.getPosition().set(1024,0);

        velocity = new Vector2D();

        boxCollider = new BoxCollider();
        this.children.add(boxCollider);

    }

    @Override
    public float run(Vector2D parentPosition) {

        this.velocity.y += GRAVITY;
        this.velocity.x = -moveSpeed;

        moveVertical();

        shoot();


        deActiveIfNeeded();
        return super.run(parentPosition);
    }


    private void shoot() {
        if (bulletDisabled) {
            coolDownTime++;
            if (coolDownTime >= COOL_DOWN_TIME) {
                bulletDisabled = false;
                coolDownTime = 0;
            }
            return;
        }
        AboBullet bullet = GameObject.recycle(AboBullet.class);
        bullet.position.set(this.position);
        bulletDisabled = true;
    }

    private void deActiveIfNeeded() {
        if (this.position.x < 0){
            this.isActive = false;
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
        return this.boxCollider;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
