package application.shapes;

import application.Point;

import java.awt.*;
import java.util.List;

public interface IShape {
    void draw(Graphics2D g2D);
    void drawOutline(Graphics2D g2D);

    //ShapeInfo getShapeInfo();
    IShape deepCopyShape();

    Point getTopLeftPoint();
    Point getBottomRightPoint();

    void translateAllPoint(int deltaX, int deltaY);

    List<IShape> getChildren();

    List<IShape> ungroup();
}
