package smithitsmiths.backgrounds;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

public class Background extends GameObject {

    public Background(){
        this.renderer = ImageRenderer.create("assets/images/background/blue_desert.png");
        this.position.y = 384;                  //still unstable
        this.position.x = 512;
    }

    @Override
    public float run(Vector2D parentPosition) {
        position.x -= 1;
        return super.run(parentPosition);


    }
}
