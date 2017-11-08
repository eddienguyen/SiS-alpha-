package bases;

public class Vector2D {
    public float x, y;

    public Vector2D(){this(0, 0);}

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other){ this.set(other.x, other.y);}

    public void addUp(float dx, float dy){
        this.x += dx;
        this.y += dy;
    }

    public void addUp(Vector2D other){
        this.addUp(other.x, other.y);
    }

    public void subtractBy(float dx, float dy){
        this.x -= dx;
        this.y -= dy;
    }

    public void subtractBy(Vector2D other){ this.subtractBy(other.x, other.y); }

    public Vector2D add(float dx, float dy){
        return new Vector2D(this.x + dx, this.y + dy);
    }

    public Vector2D add(Vector2D other) { return this.add(other.x, other.y); }

    public Vector2D subtract(float dx, float dy){
        return new Vector2D(this.x - dx, this.y - dy);
    }

    public Vector2D subtract(Vector2D other){ return this.subtract(other.x, other.y); }

    public Vector2D multiply(float f) {
        return new Vector2D(this.x * f, this.y * f);
    }

    public float magnitude(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize(){
        float mag = magnitude();
        return new Vector2D(this.x / mag, this.y / mag);
    }

    public Vector2D rotate (float degree){
        double rad = Math.toRadians(degree);
        double sinRad = Math.sin(rad);
        double cosRad = Math.cos(rad);
        return new Vector2D(
                (float)(cosRad*x - sinRad*y),
                (float)(sinRad*x + cosRad*y)
        );
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
