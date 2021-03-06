package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class GaugeBar extends GameObject {
    public float value;
    public float min, max;

    public GaugeBar(){
        this(0,100);
    }

    public GaugeBar(float min, float max){
        this.min = min;
        this.max = max;
    }

    public void setValue(float value){
        this.value = value;
    }

    public float getValue(){ return this.value; }

    public void setPosition(float x, float y){
        this.position.set(x,y);
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);
        return 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.drawRect((int)this.position.x, (int)this.position.y,50 , 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int)this.position.x+1, (int)this.position.y+1,(int) value, 9);
    }

    public void reset(){
        this.value = 0;
    }
}