package smithitsmiths.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import smithitsmiths.Platform;
import smithitsmiths.enemy.Enemy;

public class PlayerHammerDown {


    public void run(Player owner) {

        if (InputManager.instance.spaceReleased) {
            HammerSmite hammerSmite = GameObject.recycle(HammerSmite.class);
            hammerSmite.duration = 5f;
            hammerSmite.position.set(owner.position.x + owner.getBoxCollider().getWidth() / 2 + hammerSmite.boxCollider.getWidth()/2, owner.position.y);

            InputManager.spaceReleased = false;
        }

    }


}
