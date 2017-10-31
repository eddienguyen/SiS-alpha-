package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

public class Player extends GameObject {
    private Vector2D velocity;

    public Player(){
        super();
        this.renderer = ImageRenderer.create("assets/images/players/player_walk1.png");

        velocity = new Vector2D();

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        //gravity impact velocity

        //getinput


    }
}
