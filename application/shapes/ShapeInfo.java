package application.shapes;

import application.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.awt.Color;

public class ShapeInfo {
    // Data
    private ShapeType shapeType;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shadingType;

    private Point pressedPoint;
    private Point releasedPoint;

    // Constructors
    public ShapeInfo() {
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

    public ShapeType getShapeType()
    {
        return this.shapeType;
    }

    public Color getPrimaryColor() {return this.primaryColor; }

    public Color getSecondaryColor() {return this.secondaryColor; }

    public ShapeShadingType getShadingType() {return this.shadingType; }

    public Point getPressedPoint() { return this.pressedPoint; }

    public Point getReleasedPoint() { return this.releasedPoint; }
}
