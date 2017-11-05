package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.renderers.ImageRenderer;

public class Hammer extends GameObject {
    public Vector2D vector2D;
    public BoxCollider boxCollider;

    public Hammer(){
        this.renderer = ImageRenderer.create("assets/images/hammer/hammer1.jpg");

        vector2D = new Vector2D();
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }

    public void run(){

    }


}
