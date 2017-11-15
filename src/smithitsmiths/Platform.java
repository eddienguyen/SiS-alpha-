package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import bases.scenes.SceneManager;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.players.Player;
import smithitsmiths.scenes.GamePlayScene;

public class Platform extends GameObject implements PhysicsBody {
    public float moveSpeed = 2;
    private BoxCollider boxCollider;
    public Vector2D velocity;

    public Platform() {
        super();
        this.renderer = ImageRenderer.create("assets/images/platform/dirt_grass.png");
        boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);
        velocity = new Vector2D();
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.x = -2;
        dragHorizontal();
        return 0;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void dragHorizontal() {

        //calculate the future platformer (box collider) & predict collision
        BoxCollider nextBoxCollider = this.boxCollider.shift(velocity.x, 0);

        Player player = Physics.collideWith(nextBoxCollider, Player.class);


        if (player != null) {
            //move platform continously towards player
            boolean moveContinue = true;
            float shiftDistance = Math.signum(velocity.x);

            while (moveContinue) {
                if (Physics.collideWith(this.boxCollider.shift(shiftDistance, 0), Player.class) != null) {
                    moveContinue = false;
                } else {
                    shiftDistance += Math.signum(velocity.x);
                    moveContinue = true;
                }
            }

            //handling when player is being dragged
            player.position.addUp(this.velocity);
        }

        //velocity impact position
        this.position.addUp(velocity.x, 0);
        this.screenPosition.addUp(velocity.x, 0);

    }
}
