package bases.renderers;

import bases.Vector2D;
import bases.inputs.InputManager;
import smithitsmiths.players.Player;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {

    Animation runAnimation;
    public static Animation smashAnimation;   //when spaceReleased
    Animation jumpAnimation;    //when enabled jump(not hitting enemy)
    Animation currentAnimation;

    public PlayerAnimator() {
        runAnimation = new Animation(
                2,
                false,
                false,
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00013.png")

        );

        jumpAnimation = new Animation(
                5,
                false,
                false,
                SpriteUtils.loadImage("assets/images/players/running/demo_smith_00013.png")

        );

        smashAnimation = new Animation(
                0,
                false,
                false,
//                SpriteUtils.loadImage("assets/images/players/smashing/00.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/01.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/02.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/03.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/04.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/05.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/06.png"),

                SpriteUtils.loadImage("assets/images/players/smashing/07.png"),
                SpriteUtils.loadImage("assets/images/players/smashing/08.png")
//                SpriteUtils.loadImage("assets/images/players/smashing/09.png")
//                SpriteUtils.loadImage("assets/images/players/smashing/10.png"),
//                SpriteUtils.loadImage("assets/images/players/smashing/11.png")

//                SpriteUtils.loadImage("assets/images/players/smashing/12.png")
        );
        currentAnimation = runAnimation;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        currentAnimation.render(g2d, position);
    }

    public void run(Player player) {
        //get player velocity
        Vector2D velocity = player.velocity;

        //change animation based on velocity
        if (velocity.y < 0) {
            currentAnimation = jumpAnimation;
        } else {
            currentAnimation = runAnimation;
        }
    }

    public void changeAnimation(Animation animation) {
        currentAnimation = animation;
    }


}
