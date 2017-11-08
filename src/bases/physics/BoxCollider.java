package bases.physics;

import bases.GameObject;

public class BoxCollider extends GameObject {
    private float width;
    private float height;

    public BoxCollider() {
        this(0, 0, 0, 0);
    }

    public BoxCollider(float x, float y, float width, float height) {
        super();
        this.position.set(x, y);
        this.screenPosition.set(x, y);
        this.width = width;
        this.height = height;
    }

    public BoxCollider(float width, float height) {
        this(0, 0, width, height);
    }

    public BoxCollider shift(float dx, float dy) {
        BoxCollider shiftedBoxCollider = new BoxCollider(this.width, this.height);
        shiftedBoxCollider.position.set(this.position.add(dx, dy)); //new position of newBC = current position + (dx,dy)
        shiftedBoxCollider.screenPosition.set(this.screenPosition.add(dx, dy));
        return shiftedBoxCollider;
    }

    public float left() {
        return this.screenPosition.x - this.width / 2;
    }

    public float right() {
        return this.screenPosition.x + this.width / 2;
    }

    public float top() {
        return this.screenPosition.y - this.height / 2;
    }

    public float bottom() {
        return this.screenPosition.y + this.height / 2;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean intersects(BoxCollider other) {
        return this.bottom() >= other.top() &&
                this.top() <= other.bottom() &&
                this.right() >= other.left() &&
                this.left() <= other.right();
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
