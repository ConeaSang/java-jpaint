package application;

public class Point {
    // Data
    private int m_x;
    private int m_y;

    // Constructors
    public Point(int x, int y) {
        this.m_x = x;
        this.m_y = y;
    }

    public Point(Point p) {
        this.m_x = p.m_x;
        this.m_y = p.m_y;
    }

    // Methods
    public void setX(int x) {
        this.m_x = x;
    }

    public void setY(int y) {
        this.m_y = y;
    }

    public void setXY(int x, int y) {
        this.m_x = x;
        this.m_y = y;
    }

    public int getX() {
        return this.m_x;
    }

    public int getY() {
        return this.m_y;
    }
}
