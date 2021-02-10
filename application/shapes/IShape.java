package application.shapes;

import application.Point;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D _graphics2D);

    Point getTopLeftPoint();
    Point getBottomRightPoint();

    //PaintCanvasBase getPaintCanvas();
}
