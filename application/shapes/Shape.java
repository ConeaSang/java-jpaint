package application.shapes;

import application.Point;

public abstract class Shape implements IShape {
    // Data
    protected ShapeInfo shapeInfo;

    protected Point topLeftPoint;
    protected Point bottomRightPoint;
}
