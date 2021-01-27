package application.shapes;

import application.Point;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;

public class ShapeInfo {
    // Data
    private Point pointTopLeft;
    private int width;
    private int height;
    //private ShapeColor primaryColor;
    //private ShapeColor secondaryColor;
    //private ShapeShadingType shadingType;
    //private MouseMode mouseMode;

    // Constructors
    public ShapeInfo() {
        this.pointTopLeft = new Point(0,0);
        this.width = 0;
        this.height = 0;
    }

    // Methods
    public ShapeInfo setPointTopLeft(Point _pointTopLeft)
    {
        this.pointTopLeft = _pointTopLeft;
        return this;
    }

    public ShapeInfo setWidth(int _width)
    {
        this.width = _width;
        return this;
    }

    public ShapeInfo setHeight(int _height)
    {
        this.height = _height;
        return this;
    }

}
