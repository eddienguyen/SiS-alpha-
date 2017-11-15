package smithitsmiths.players;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerHammerDown {


    public void run(Player owner) {

        if (InputManager.instance.spaceReleased) {
            HammerSmite hammerSmite = GameObject.recycle(HammerSmite.class);
            hammerSmite.damage = owner.getDamage();
            hammerSmite.duration = 5;
            hammerSmite.position.set(owner.position.x + owner.getBoxCollider().getWidth() , owner.position.y);

            InputManager.spaceReleased = false;
        }

    }


}
