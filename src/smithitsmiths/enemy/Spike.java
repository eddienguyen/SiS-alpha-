package smithitsmiths.enemy;

import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;

public class Spike extends Enemy  {

    public Spike(){
        super();
        this.renderer = ImageRenderer.create("assets/images/enemies/spike.png");
        this.boxCollider.setHeight(10);
        this.boxCollider.setWidth(20);
        this.HP = 10;
    }


}
