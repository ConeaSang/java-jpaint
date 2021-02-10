package application;

public class Point {
    // Data
    private int x;
    private int y;

    // Constructors
    public Point(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    // Methods
    public void setX(int _x) {
        this.x = _x;
    }

    public void setY(int _y) {
        this.y = _y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
