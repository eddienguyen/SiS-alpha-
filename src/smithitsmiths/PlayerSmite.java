package smithitsmiths;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerSmite {
    boolean smiteDisabled;
    Hammer hammer;

    public PlayerSmite(){
        hammer = new Hammer();
    }

    public void run(Player playerPosition){
        if (smiteDisabled){
            hammer.position.set(playerPosition.position);
        }
        if (InputManager.instance.spaceReleased) {

            try{
                hammer.position.set(100,600);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            smiteDisabled = true;
        }
    }
}
