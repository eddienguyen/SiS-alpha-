package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import smithitsmiths.Platform;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;

import java.awt.*;

public class Bullet extends GameObject implements PhysicsBody{
    public BoxCollider boxCollider;
    private Vector2D velocity;
    public Bullet(){
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/Bullet.png");
        velocity = new Vector2D();
        boxCollider = new BoxCollider();
        this.children.add(boxCollider);
    }

    @Override
    public float run(Vector2D parentPosition) {
        playerHit();
        this.velocity.x = -MapSpawner.getCurrentSpeed()*3;
        deActiveIfNeeded();
        return super.run(parentPosition);

    }

    private void deActiveIfNeeded() {
        if (this.position.x < 0){
            this.isActive = false;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
    private void playerHit() {
        position.addUp(velocity);
        Player hitPlayer = Physics.collideWith(this.boxCollider, Player.class);
        if (hitPlayer != null){
            hitPlayer.getHit();
        }
    }
}
