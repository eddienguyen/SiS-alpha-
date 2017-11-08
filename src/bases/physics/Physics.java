package bases.physics;

import smithitsmiths.players.Player;
import smithitsmiths.enemy.Enemy;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>(); // Generics

    public static <T extends PhysicsBody> T collideWith(BoxCollider boxCollider, Class<T> classz) {
        for(PhysicsBody body : bodies) {
            if(body.getClass() == Player.class && classz == Player.class) {
//                System.out.println(true);
//                System.out.println(body.getBoxCollider().intersects(boxCollider));
//                System.out.println(">>>>");
//                System.out.println(body.getBoxCollider().getScreenPosition());
//                System.out.println(boxCollider.getScreenPosition());
            }
            if (body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }
        return null;
    }


    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static void clearAll() {
        bodies.clear();
    }
}
