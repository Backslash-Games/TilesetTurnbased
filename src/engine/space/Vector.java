package engine.space;

public class Vector {
    // Default vectors
    public static final Vector zero = new Vector(0, 0);
    public static final Vector one = new Vector(1, 1);


    public float x = 0;
    public float y = 0;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static Vector Add(Vector a, Vector b){
        return new Vector(a.x + b.x, a.y + b.y);
    }
    public static Vector Mult(Vector a, int b){
        return new Vector(a.x * b, a.y * b);
    }

    @Override
    public String toString() {
        return STR."(\{x}, \{y})";
    }
}
