package application.shapes;

import application.Point;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {
    void draw();

    PaintCanvasBase getPaintCanvas();
}
