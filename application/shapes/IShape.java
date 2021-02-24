package application.shapes;

import application.Point;
import application.observers.ShapeRepository;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D _g2D);

    ShapeInfo getShapeInfo();
    Point getTopLeftPoint();
    Point getBottomRightPoint();

    void translateAllPoint(int _deltaX, int _deltaY);

    //PaintCanvasBase getPaintCanvas();
}
