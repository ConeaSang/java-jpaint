package application.shapes;

import application.Point;
import model.ShapeShadingType;
import model.ShapeType;

import java.awt.Color;

public class ShapeInfo {
    // Data
    private ShapeType m_shapeType;
    private Color m_primaryColor;
    private Color m_secondaryColor;
    private ShapeShadingType m_shadingType;

    private Point m_pressedPoint;
    private Point m_releasedPoint;

    // Constructors
    // Default constructor
    public ShapeInfo() {
    }

    // Special constructor for hard copy
    public ShapeInfo(ShapeInfo shapeInfo) {
        this.m_shapeType = shapeInfo.getShapeType();
        this.m_primaryColor = shapeInfo.getPrimaryColor();
        this.m_secondaryColor = shapeInfo.getSecondaryColor();
        this.m_shadingType = shapeInfo.getShadingType();

        this.m_pressedPoint = new Point(shapeInfo.getPressedPoint());
        this.m_releasedPoint = new Point(shapeInfo.getReleasedPoint());
    }

    // Methods
    public ShapeInfo setShapeType(ShapeType shapeType) {
        this.m_shapeType = shapeType;
        return this;
    }

    public ShapeInfo setPrimaryColor(Color primaryColor) {
        this.m_primaryColor = primaryColor;
        return this;
    }

    public ShapeInfo setSecondaryColor(Color secondaryColor) {
        this.m_secondaryColor = secondaryColor;
        return this;
    }

    public ShapeInfo setShadingType(ShapeShadingType shadingType) {
        this.m_shadingType = shadingType;
        return this;
    }

    public ShapeInfo setPressedPoint(Point pressedPoint) {
        this.m_pressedPoint = pressedPoint;
        return this;
    }

    public ShapeInfo setReleasedPoint(Point releasedPoint) {
        this.m_releasedPoint = releasedPoint;
        return this;
    }

    public ShapeType getShapeType()
    {
        return this.m_shapeType;
    }

    public Color getPrimaryColor() {return this.m_primaryColor; }

    public Color getSecondaryColor() {return this.m_secondaryColor; }

    public ShapeShadingType getShadingType() {return this.m_shadingType; }

    public Point getPressedPoint() { return this.m_pressedPoint; }

    public Point getReleasedPoint() { return this.m_releasedPoint; }
}
