package bases.renderers;

import bases.Vector2D;
import bases.inputs.InputManager;
import smithitsmiths.players.Hammer;
import smithitsmiths.players.Player;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {

    Animation runWoodAnimation;
    Animation runRockAnimation;
    Animation runIronAnimation;
    Animation runGoldAnimation;
    Animation runDiamondAnimation;

    public static Animation smashWoodAnimation;   //when spaceReleased
    public static Animation smashRockAnimation;
    public static Animation smashIronAnimation;
    public static Animation smashGoldAnimation;
    public static Animation smashDiamondAnimation;


    Animation jumpWoodAnimation;    //when enabled jump(not hitting enemy)
    Animation jumpRockAnimation;
    Animation jumpIronAnimation;
    Animation jumpGoldAnimation;
    Animation jumpDiamondAnimation;

    Animation currentAnimation;

    public PlayerAnimator() {
        //wood = default:
        runWoodAnimation = new Animation(
                2,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00013.png")

        );

        jumpWoodAnimation = new Animation(
                5,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/wood/demo_smith_00013.png")
        );

        smashWoodAnimation = new Animation(
                0,
                false,
                false,


                SpriteUtils.loadImage("assets/images/players/smash/wood/demo_smith07.png"),
                SpriteUtils.loadImage("assets/images/players/smash/wood/demo_smith08.png")


        );

        //rock
        runRockAnimation = new Animation(
                2,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00013.png")

        );

        jumpRockAnimation = new Animation(
                5,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/rock/demo_smith_00013.png")
        );

        smashRockAnimation = new Animation(
                0,
                false,
                false,


                SpriteUtils.loadImage("assets/images/players/smash/rock/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/smash/rock/demo_smith_00008.png")


        );

        //Iron:
        runIronAnimation = new Animation(
                2,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00013.png")

        );

        jumpIronAnimation = new Animation(
                5,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/iron/demo_smith_00013.png")
        );

        smashIronAnimation = new Animation(
                0,
                false,
                false,


                SpriteUtils.loadImage("assets/images/players/smash/iron/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/smash/iron/demo_smith_00008.png")


        );

        //gold:
        runGoldAnimation = new Animation(
                2,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00013.png")

        );

        jumpGoldAnimation = new Animation(
                5,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/gold/demo_smith_00013.png")
        );

        smashGoldAnimation = new Animation(
                0,
                false,
                false,


                SpriteUtils.loadImage("assets/images/players/smash/gold/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/smash/gold/demo_smith_00008.png")


        );

        //diamond:
        runDiamondAnimation = new Animation(
                2,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00000.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00001.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00002.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00003.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00004.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00005.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00006.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00008.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00009.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00010.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00011.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00012.png"),
                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00013.png")

        );

        jumpDiamondAnimation = new Animation(
                5,
                false,
                false,

                SpriteUtils.loadImage("assets/images/players/run/diamond/demo_smith_00013.png")
        );

        smashDiamondAnimation = new Animation(
                0,
                false,
                false,


                SpriteUtils.loadImage("assets/images/players/smash/diamond/demo_smith_00007.png"),
                SpriteUtils.loadImage("assets/images/players/smash/diamond/demo_smith_00008.png")


        );

        currentAnimation = runWoodAnimation;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        currentAnimation.render(g2d, position);
    }

    public void run(Player player) {
        //get player velocity and player currentHammerDamage
        Vector2D velocity = player.velocity;
        int currentHammerDamage = (int) Hammer.getCurrentHammerDamage();

        //change animation based on currentHammer first
        switch (currentHammerDamage) {
            case Hammer.WOODDAMAGE:
                //change animation based on velocity second
                if (velocity.y < 0) {
                    currentAnimation = jumpWoodAnimation;
                } else {
                    currentAnimation = runWoodAnimation;
                }
                break;
            case Hammer.ROCKDAMAGE:
                //change animation based on velocity second
                if (velocity.y < 0) {
                    currentAnimation = jumpRockAnimation;
                } else {
                    currentAnimation = runRockAnimation;
                }
                break;
            case Hammer.IRONDAMAGE:
                //change animation based on velocity second
                if (velocity.y < 0) {
                    currentAnimation = jumpIronAnimation;
                } else {
                    currentAnimation = runIronAnimation;
                }
                break;
            case Hammer.GOLDDAMAGE:
                //change animation based on velocity second
                if (velocity.y < 0) {
                    currentAnimation = jumpGoldAnimation;
                } else {
                    currentAnimation = runGoldAnimation;
                }
                break;
            case Hammer.DIAMONDDAMAGE:
                //change animation based on velocity second
                if (velocity.y < 0) {
                    currentAnimation = jumpDiamondAnimation;
                } else {
                    currentAnimation = runDiamondAnimation;
                }
                break;
        }

    }

    public void changeAnimation(Animation animation) {
        currentAnimation = animation;
    }


}
