package smithitsmiths.backgrounds;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class Background extends GameObject {
    Vector2D secondPosition;

    public Background() {
        this.renderer = ImageRenderer.create("assets/images/background/blue_desert.png");
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
//        this.renderer.render(g2d, position);

    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.x -= 1f;
        this.screenPosition.x -= 1f;

        if (position.x <= -512) {
//            if (background.position.x <= 512) background2.position.x = 1536;
//            if (background2.position.x <= 512) background.position.x = 1536;
            position.x = 1536;
        }
        return 0;
    }
}
