package application.shapes;

import application.Point;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class ShapeRectangle extends Shape {
    // Data

    // Constructors
    public ShapeRectangle(ShapeInfo _shapeInfo) {
        //this.paintCanvas = _shapeInfo.getPaintCanvas();
        //this.graphics2D = _shapeInfo.getPaintCanvas().getGraphics2D();
        this.shapeType = _shapeInfo.getShapeType();
        this.primaryColor = _shapeInfo.getPrimaryColor();
        this.secondaryColor = _shapeInfo.getSecondaryColor();
        this.shadingType = _shapeInfo.getShadingType();

        //this.pressedPoint = _shapeInfo.getPressedPoint();
        this.pressedPoint = new Point(_shapeInfo.getPressedPoint());
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
    public void draw(Graphics2D _g2D) {
        int width = Math.abs(this.releasedPoint.getX() - this.pressedPoint.getX());
        int height = Math.abs(this.releasedPoint.getY() - this.pressedPoint.getY());

        // getGraphics2D
        //Graphics2D graphics2D = this.paintCanvas.getGraphics2D();

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            _g2D.setStroke(new BasicStroke(5));
            _g2D.setColor(this.primaryColor);
            _g2D.drawRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            _g2D.setColor(this.primaryColor);
            _g2D.fillRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);
            //System.out.println("fillRect");

        } else {
            _g2D.setColor(this.primaryColor);
            _g2D.fillRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);

            _g2D.setStroke(new BasicStroke(5));
            _g2D.setColor(this.secondaryColor);
            _g2D.drawRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);
        }

//        // Only Green Rectangle for now
//        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();
//        graphics2D.setColor(Color.GREEN);
//        graphics2D.fillRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), this.width, this.height);

        //--------------------------------------------------
        // Testing

        //graphics2D.fillPolygon();

        //--------------------------------------------------
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
