package smithitsmiths.backgrounds;

import bases.GameObject;
import bases.renderers.ImageRenderer;

public class Background extends GameObject {

    public Background(){
        this.renderer = ImageRenderer.create("assets/images/background/blue_desert.png");
        this.position.x = 512;//still unstable
        this.position.y = 768/2;
    }
}
