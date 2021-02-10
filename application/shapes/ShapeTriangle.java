package application.shapes;

import application.Point;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo _shapeInfo) {
        //this.paintCanvas = _shapeInfo.getPaintCanvas();
        //this.graphics2D = _shapeInfo.getPaintCanvas().getGraphics2D();
        this.shapeType = _shapeInfo.getShapeType();
        this.primaryColor = _shapeInfo.getPrimaryColor();
        this.secondaryColor = _shapeInfo.getSecondaryColor();
        this.shadingType = _shapeInfo.getShadingType();

        //this.pressedPoint = _shapeInfo.getPressedPoint();
        this.pressedPoint = new application.Point(_shapeInfo.getPressedPoint());
        //this.releasedPoint = _shapeInfo.getReleasedPoint();
        this.releasedPoint = new Point(_shapeInfo.getReleasedPoint());

        // Find topLeftPoint & bottomRightPoint
        this.topLeftPoint = new Point(0, 0);
        this.bottomRightPoint = new Point(0, 0);

        if (this.pressedPoint.getX() < this.releasedPoint.getX()) {
            this.topLeftPoint.setX(this.pressedPoint.getX());
            this.bottomRightPoint.setX(this.releasedPoint.getX());
        } else {
            this.topLeftPoint.setX(this.releasedPoint.getX());
            this.bottomRightPoint.setX(this.pressedPoint.getX());
        }

        if (this.pressedPoint.getY() < this.releasedPoint.getY()) {
            this.topLeftPoint.setY(this.pressedPoint.getY());
            this.bottomRightPoint.setY(this.releasedPoint.getY());
        } else {
            this.topLeftPoint.setY(this.releasedPoint.getY());
            this.bottomRightPoint.setY(this.pressedPoint.getY());
        }
    }

    // Methods
    @Override
    public void draw(Graphics2D _graphics2D) {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.pressedPoint.getX();
        xArray[1] = this.releasedPoint.getX();
        xArray[2] = this.pressedPoint.getX();

        yArray[0] = this.pressedPoint.getY();
        yArray[1] = this.releasedPoint.getY();
        yArray[2] = this.releasedPoint.getY();

        // getGraphics2D
        //Graphics2D graphics2D = this.paintCanvas.getGraphics2D();

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            _graphics2D.setStroke(new BasicStroke(5));
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.drawPolygon(xArray, yArray, 3);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.fillPolygon(xArray, yArray, 3);
            //System.out.println("fillPolygon");

        } else {
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.fillPolygon(xArray, yArray, 3);

            _graphics2D.setStroke(new BasicStroke(5));
            _graphics2D.setColor(this.secondaryColor);
            _graphics2D.drawPolygon(xArray, yArray, 3);
        }
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
        this.pressedPoint.setXY(this.pressedPoint.getX() + _deltaX, this.pressedPoint.getY() + _deltaY);
        this.releasedPoint.setXY(this.releasedPoint.getX() + _deltaX, this.releasedPoint.getY() + _deltaY);
        this.topLeftPoint.setXY(this.topLeftPoint.getX() + _deltaX, this.topLeftPoint.getY() + _deltaY);
        this.bottomRightPoint.setXY(this.bottomRightPoint.getX() + _deltaX, this.bottomRightPoint.getY() + _deltaY);
    }

//    @Override
//    public PaintCanvasBase getPaintCanvas() {
//        return this.paintCanvas;
//    }
}
