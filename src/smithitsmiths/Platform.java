package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

public class Platform extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    private final float SPEED = 10;
    Vector2D velocity;

    public Platform() {
        super();
        this.renderer = ImageRenderer.create("assets/images/platform/yellow_square.jpg");
        boxCollider = new BoxCollider(30,30);
        this.children.add(boxCollider);
        velocity = new Vector2D();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        velocity.x = -SPEED;
        position.addUp(velocity);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
