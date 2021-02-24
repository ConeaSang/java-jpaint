package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo _shapeInfo) {
        //this.shapeInfo = new ShapeInfo();

        //this.shapeInfo.setShapeType(_shapeInfo.getShapeType());
        //this.shapeInfo.setPrimaryColor(_shapeInfo.getPrimaryColor());
        //this.shapeInfo.setSecondaryColor(_shapeInfo.getSecondaryColor());
        //this.shapeInfo.setShadingType(_shapeInfo.getShadingType());

        //this.shapeInfo.setPressedPoint(new Point(_shapeInfo.getPressedPoint()));
        //this.shapeInfo.setReleasedPoint(new Point(_shapeInfo.getReleasedPoint()));

        this.shapeInfo = _shapeInfo;

        // Find topLeftPoint & bottomRightPoint
        this.topLeftPoint = new Point(0, 0);
        this.bottomRightPoint = new Point(0, 0);

        if (this.shapeInfo.getPressedPoint().getX() < this.shapeInfo.getReleasedPoint().getX()) {
            this.topLeftPoint.setX(this.shapeInfo.getPressedPoint().getX());
            this.bottomRightPoint.setX(this.shapeInfo.getReleasedPoint().getX());
        } else {
            this.topLeftPoint.setX(this.shapeInfo.getReleasedPoint().getX());
            this.bottomRightPoint.setX(this.shapeInfo.getPressedPoint().getX());
        }

        if (this.shapeInfo.getPressedPoint().getY() < this.shapeInfo.getReleasedPoint().getY()) {
            this.topLeftPoint.setY(this.shapeInfo.getPressedPoint().getY());
            this.bottomRightPoint.setY(this.shapeInfo.getReleasedPoint().getY());
        } else {
            this.topLeftPoint.setY(this.shapeInfo.getReleasedPoint().getY());
            this.bottomRightPoint.setY(this.shapeInfo.getPressedPoint().getY());
        }
    }

    // Methods
    @Override
    public void draw(Graphics2D _g2D) {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.shapeInfo.getPressedPoint().getX();
        xArray[1] = this.shapeInfo.getReleasedPoint().getX();
        xArray[2] = this.shapeInfo.getPressedPoint().getX();

        yArray[0] = this.shapeInfo.getPressedPoint().getY();
        yArray[1] = this.shapeInfo.getReleasedPoint().getY();
        yArray[2] = this.shapeInfo.getReleasedPoint().getY();

        // ShadingType
        if (this.shapeInfo.getShadingType() == ShapeShadingType.OUTLINE) {
            _g2D.setStroke(new BasicStroke(5));
            _g2D.setColor(this.shapeInfo.getPrimaryColor());
            _g2D.drawPolygon(xArray, yArray, 3);

        } else if (this.shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            _g2D.setColor(this.shapeInfo.getPrimaryColor());
            _g2D.fillPolygon(xArray, yArray, 3);
            //System.out.println("fillPolygon");

        } else {
            _g2D.setColor(this.shapeInfo.getPrimaryColor());
            _g2D.fillPolygon(xArray, yArray, 3);

            _g2D.setStroke(new BasicStroke(5));
            _g2D.setColor(this.shapeInfo.getSecondaryColor());
            _g2D.drawPolygon(xArray, yArray, 3);
        }
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return this.shapeInfo;
    }

    @Override
    public Point getTopLeftPoint() {
        return this.topLeftPoint;
    }

    @Override
    public Point getBottomRightPoint() {
        return this.bottomRightPoint;
    }

    @Override
    public void translateAllPoint(int _deltaX, int _deltaY) {
        this.shapeInfo.getPressedPoint().setXY(this.shapeInfo.getPressedPoint().getX() + _deltaX, this.shapeInfo.getPressedPoint().getY() + _deltaY);
        this.shapeInfo.getReleasedPoint().setXY(this.shapeInfo.getReleasedPoint().getX() + _deltaX, this.shapeInfo.getReleasedPoint().getY() + _deltaY);
        this.topLeftPoint.setXY(this.topLeftPoint.getX() + _deltaX, this.topLeftPoint.getY() + _deltaY);
        this.bottomRightPoint.setXY(this.bottomRightPoint.getX() + _deltaX, this.bottomRightPoint.getY() + _deltaY);
    }
}
