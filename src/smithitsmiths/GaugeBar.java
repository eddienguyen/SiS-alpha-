package smithitsmiths;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import javax.swing.*;
import java.awt.*;

public class GaugeBar extends GameObject {
    public final int FILLCONSTANT = 30;
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
        this.value = value*FILLCONSTANT;    //easier to look
    }

    public float getValue(){ return this.value; }

    public void setPosition(float x, float y){
        this.position.set(x,y);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(Color.RED);
        g2d.drawLine((int)this.position.x, (int)this.position.y,(int)(this.position.x + value),(int)this.position.y);
    }
}