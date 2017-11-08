package smithitsmiths.backgrounds;

import bases.GameObject;
import bases.renderers.ImageRenderer;

public class Background extends GameObject {

    public Background(){
        this.renderer = ImageRenderer.create("assets/images/background/blue_desert_extended.png");
        this.position.y = 512;                  //still unstable
    }
}
