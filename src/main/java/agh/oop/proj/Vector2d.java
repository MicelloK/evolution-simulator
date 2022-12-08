package agh.oop.proj;

public record Vector2d(int x, int y) {
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (other instanceof Vector2d otherVector) {
            return this.x == otherVector.x && this.y == otherVector.y;
        }
        return false;
    }
}
