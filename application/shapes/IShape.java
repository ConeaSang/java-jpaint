package application.shapes;

import application.Point;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D g2D);

    ShapeInfo getShapeInfo();
    Point getTopLeftPoint();
    Point getBottomRightPoint();

    void translateAllPoint(int deltaX, int deltaY);
}
