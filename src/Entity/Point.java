package Entity;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        if (x < 0 || x > 15) {
            throw new IllegalArgumentException();
        }
        if (y < 0 || y > 15) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
