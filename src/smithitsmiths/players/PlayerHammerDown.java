package smithitsmiths.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import smithitsmiths.Platform;
import smithitsmiths.enemy.Enemy;

public class PlayerSmite implements PhysicsBody {
    boolean smiteDisabled;

    public BoxCollider boxCollider;
    FrameCounter frameCounter = new FrameCounter(6);

    public PlayerSmite(){
        this.smiteDisabled = true;
    }

    public void placeHitBox(Player player){
        this.boxCollider.position.set(player.position.x + player.getBoxCollider().getWidth()/2, player.position.y);
    }

    public void run(Player owner){

        if (InputManager.instance.spaceReleased) {
            BoxCollider smite = GameObject.recycle(BoxCollider.class);
            smite.position.set(owner.position.x + owner.getBoxCollider().getWidth()/2, owner.position.y);
            smite.setWidth(20);
            smite.setHeight(30);

            unActiveSmiteIfNeeded(smite);
        }


    }

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }

    @Override
    public boolean isActive() {
        return this.isActive();
    }

    public void unActiveSmiteIfNeeded(BoxCollider smite){
        if (frameCounter.run()){
            frameCounter.reset();
            if (!smite.isActive()){
                smite.setActive(false);
                System.out.println(smite.isActive());
            }
        }
    }
}
