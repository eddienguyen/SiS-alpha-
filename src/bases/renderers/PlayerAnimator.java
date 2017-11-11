package bases.renderers;

import bases.Vector2D;
import smithitsmiths.players.Player;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {

    Animation runAnimation;
    Animation smashAnimation;   //when spaceReleased
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
        currentAnimation = runAnimation;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        currentAnimation.render(g2d, position);
    }

    public void run(Player player){
        //get player velocity
        Vector2D velocity = player.velocity;

        //change animation based on velocity
        if (velocity.y < 0){
            System.out.println("jump");
            currentAnimation = jumpAnimation;
        } else {
            currentAnimation = runAnimation;
        }
    }
}
