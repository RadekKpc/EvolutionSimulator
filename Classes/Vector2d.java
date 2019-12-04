package agh.cs.po.Classes;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "(" + Integer.toString(this.x) + "," + Integer.toString(this.y) + ")";
    }

    public boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y) {
            return true;
        }
        return false;
    }

    public boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y) {
            return true;
        }
        return false;
    }

    public Vector2d upperRight(Vector2d other) {
        if (other == null) return this;
        int xn = this.x >= other.x ? this.x : other.x;

        int yn = this.y >= other.y ? this.y : other.y;
        return new Vector2d(xn, yn);
    }

    public Vector2d lowerLeft(Vector2d other) {
        if (other == null) return this;
        int xn = Math.min(other.x, this.x);
        int yn = Math.min(other.y, this.y);

        return new Vector2d(xn, yn);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return that.x == this.x && that.y == this.y;
    }

    public Vector2d opposite() {
        return new Vector2d(-1 * this.x, -1 * this.y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
