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
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class AboBullet extends GameObject implements PhysicsBody{
    public BoxCollider boxCollider;
    public Vector2D velocity;
    final private float GRAVITY = 0.2f;
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
            this.velocity.x = -MapSpawner.getCurrentSpeed()*4;
        }
        if (platform != null) {
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
