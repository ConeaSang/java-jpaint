package application.shapes;

import application.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.awt.Color;

public class ShapeInfo {
    // Data
    private PaintCanvasBase paintCanvas;
    private ShapeType shapeType;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shadingType;

    private Point pressedPoint;
    private Point releasedPoint;

    //private Point pointTopLeft;
    //private int width;
    //private int height;

    // Constructors
    public ShapeInfo(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;

        //this.pointTopLeft = new Point(0,0);
        //this.width = 0;
        //this.height = 0;
    }

    // Methods
    public ShapeInfo setShapeType(ShapeType _shapeType) {
        this.shapeType = _shapeType;
        return this;
    }

    public ShapeInfo setPrimaryColor(Color _primaryColor) {
        this.primaryColor = _primaryColor;
        return this;
    }

    public ShapeInfo setSecondaryColor(Color _secondaryColor) {
        this.secondaryColor = _secondaryColor;
        return this;
    }

    public ShapeInfo setShadingType(ShapeShadingType _shadingType) {
        this.shadingType = _shadingType;
        return this;
    }

    public ShapeInfo setPressedPoint(Point _pressedPoint) {
        this.pressedPoint = _pressedPoint;
        return this;
    }

    public ShapeInfo setReleasedPoint(Point _releasedPoint) {
        this.releasedPoint = _releasedPoint;
        return this;
    }

//    public ShapeInfo setPointTopLeft(Point _pointTopLeft)
//    {
//        this.pointTopLeft = _pointTopLeft;
//        return this;
//    }
//
//    public ShapeInfo setWidth(int _width)
//    {
//        this.width = _width;
//        return this;
//    }
//
//    public ShapeInfo setHeight(int _height)
//    {
//        this.height = _height;
//        return this;
//    }

    public PaintCanvasBase getPaintCanvas() { return this.paintCanvas; }

    public ShapeType getShapeType()
    {
        return this.shapeType;
    }

    public Color getPrimaryColor() {return this.primaryColor; }

    public Color getSecondaryColor() {return this.secondaryColor; }

    public ShapeShadingType getShadingType() {return this.shadingType; }

    public Point getPressedPoint() { return this.pressedPoint; }

    public Point getReleasedPoint() { return this.releasedPoint; }

//    public Point getPointTopLeft()
//    {
//        return this.pointTopLeft;
//    }
//
//    public int getWidth()
//    {
//        return this.width;
//    }
//
//    public int getHeight()
//    {
//        return this.height;
//    }
}
