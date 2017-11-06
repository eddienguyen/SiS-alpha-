package smithitsmiths.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class Hammer extends GameObject {
//    public int material;
//    public final int WOOD = 1;


    public Hammer(){
        this.renderer = ImageRenderer.create("assets/images/hammer/hammer_bronze.png ");

    }

    @Override
    public float run(Vector2D parentPosition) {
        return super.run(parentPosition);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }

    public void setPosition(float x, float y){
        this.position.set(x,y);
    }
}
