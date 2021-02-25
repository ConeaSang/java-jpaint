package application.shapes;

import application.Point;

public abstract class Shape implements IShape {
    // Data
    protected ShapeInfo m_shapeInfo;

    protected Point m_topLeftPoint;
    protected Point m_bottomRightPoint;
}
