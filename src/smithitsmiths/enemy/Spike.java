package smithitsmiths.enemy;

import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

public class Spike extends Enemy  {

    public Spike(){
        super();
        this.renderer = ImageRenderer.create("assets/images/platform/green_square.png");
        this.boxCollider.setHeight(30);
        this.boxCollider.setWidth(30);
        this.HP = 10;
    }


}
