package application.shapes;

import application.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Shape implements IShape {
    // Data
    //protected ShapeType shapeType;
    //protected Color primaryColor;
    //protected Color secondaryColor;
    //protected ShapeShadingType shadingType;

    //protected Point pressedPoint;
    //protected Point releasedPoint;

    protected ShapeInfo shapeInfo;

    protected Point topLeftPoint;
    protected Point bottomRightPoint;
}
