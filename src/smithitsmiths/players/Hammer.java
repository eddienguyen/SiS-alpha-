package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class Hammer extends GameObject {

    public static final int WOODDAMAGE = 3;
    public static final int ROCKDAMAGE = 5;
    public static final int IRONDAMAGE = 8;
    public static final int GOLDDAMAGE = 10;
    public static final int DIAMONDDAMAGE = 15;

    public static String material;

    public static float currentHammerDamage;

    public Hammer() {
        currentHammerDamage = WOODDAMAGE;
    }


    public static float getCurrentHammerDamage() {
        return currentHammerDamage;
    }

    public String toString() {
        if (currentHammerDamage == WOODDAMAGE) {
            return "WOOD";
        } else if (currentHammerDamage == ROCKDAMAGE) {
            return "ROCK";
        } else if (currentHammerDamage == IRONDAMAGE) {
            return "IRON";
        } else if (currentHammerDamage == GOLDDAMAGE) {
            return "GOLD";
        } else {
            return "DIAMOND";
        }
    }

    public static void changeHammer(String material) {          //  = setCurrentHammerDamage
        switch (material) {
            case "wood":
                currentHammerDamage = WOODDAMAGE;
                break;
            case "rock":
                currentHammerDamage = ROCKDAMAGE;
                break;
            case "iron":
                currentHammerDamage = IRONDAMAGE;
                break;
            case "gold":
                currentHammerDamage = GOLDDAMAGE;
                break;
            case "diamond":
                currentHammerDamage = DIAMONDDAMAGE;
                break;
            default:
                System.out.println("wrong in changeHammer()");
        }
    }


}
