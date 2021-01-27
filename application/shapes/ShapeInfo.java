package application.shapes;

import application.Point;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public class ShapeInfo {
    // Data
    private PaintCanvasBase paintCanvas;
    private ShapeType shapeType;
    private Point pointTopLeft;
    private int width;
    private int height;
    //private ShapeColor primaryColor;
    //private ShapeColor secondaryColor;
    //private ShapeShadingType shadingType;
    //private MouseMode mouseMode;

    // Constructors
    public ShapeInfo(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;
        this.shapeType = ShapeType.RECTANGLE;
        this.pointTopLeft = new Point(0,0);
        this.width = 0;
        this.height = 0;
    }

    // Methods
    public ShapeInfo setShapeType(ShapeType _shapeType)
    {
        this.shapeType = _shapeType;
        return this;
    }

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

    public PaintCanvasBase getPaintCanvas() { return this.paintCanvas; }

    public ShapeType getShapeType()
    {
        return this.shapeType;
    }

    public Point getPointTopLeft()
    {
        return this.pointTopLeft;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }
}
