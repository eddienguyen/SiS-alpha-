package smithitsmiths.enemy;

import bases.GameObject;
import bases.Vector2D;

import java.awt.*;

public class EnemyHP extends GameObject {
    public final int FILLCONSTANT = 5;
    public float value;
    public float min, max;

    public EnemyHP(){
        this(0,100);
    }

    public EnemyHP(float min, float max){
        this.min = min;
        this.max = max;
    }

    public void setValue(float value){
        //this.value = value*FILLCONSTANT;    //easier to look
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
        g2d.setColor(Color.RED);
        g2d.drawLine((int)this.position.x, (int)this.position.y,(int)(this.position.x + value),(int)this.position.y);
    }

    //TODO : reset value (to 0)
}