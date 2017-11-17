package smithitsmiths.items;

import bases.GameObject;
import bases.actions.Action;
import bases.actions.ActionRepeatForever;
import bases.actions.ActionSequence;
import bases.actions.ActionWait;
import smithitsmiths.maps.MapSpawner;

import java.util.Random;

public class ItemSpawner extends GameObject {

    Random r = new Random();
    public ItemSpawner(){
        Action wait = new ActionWait(2000);
        Action spawnAction = new Action() {

            @Override
            public boolean run(GameObject owner) {
                int randomMaterialChance = r.nextInt(101);
                if (randomMaterialChance < 40) {
                    HammerPickUp hammerPickUp = new HammerPickUp(0);
                    hammerPickUp.position.set(924,200);
                    GameObject.add(hammerPickUp);
                }else if (randomMaterialChance >= 40 && randomMaterialChance < 70){
                    HammerPickUp hammerPickUp = new HammerPickUp(1);
                    hammerPickUp.position.set(924,200);
                    GameObject.add(hammerPickUp);
                }else if (randomMaterialChance >= 70 && randomMaterialChance <80){
                    HammerPickUp hammerPickUp = new HammerPickUp(2);
                    hammerPickUp.position.set(924,200);
                    GameObject.add(hammerPickUp);
                }else if (randomMaterialChance >= 80 && randomMaterialChance < 95){
                    HammerPickUp hammerPickUp = new HammerPickUp(3);
                    hammerPickUp.position.set(924,200);
                    GameObject.add(hammerPickUp);
                } else {
                    HammerPickUp hammerPickUp = new HammerPickUp(4);
                    hammerPickUp.position.set(924,200);
                    GameObject.add(hammerPickUp);
                }
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action spawnRapidly = new ActionRepeatForever(new ActionSequence(wait, spawnAction ));
        this.addAction(spawnRapidly);
    }
}
