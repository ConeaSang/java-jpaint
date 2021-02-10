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
        this.paintCanvas = _shapeInfo.getPaintCanvas();
        //this.graphics2D = _shapeInfo.getPaintCanvas().getGraphics2D();
        this.shapeType = _shapeInfo.getShapeType();
        this.primaryColor = _shapeInfo.getPrimaryColor();
        this.secondaryColor = _shapeInfo.getSecondaryColor();
        this.shadingType = _shapeInfo.getShadingType();

        //this.pressedPoint = _shapeInfo.getPressedPoint();
        this.pressedPoint = new Point(_shapeInfo.getPressedPoint());
        //this.releasedPoint = _shapeInfo.getReleasedPoint();
        this.releasedPoint = new Point(_shapeInfo.getReleasedPoint());
    }

    // Methods
    @Override
    public void draw() {
        Point topLeftPoint = new Point(0, 0);

        if (this.pressedPoint.getX() < this.releasedPoint.getX()) {
            topLeftPoint.setX(this.pressedPoint.getX());
        } else {
            topLeftPoint.setX(this.releasedPoint.getX());
        }

        if (this.pressedPoint.getY() < this.releasedPoint.getY()) {
            topLeftPoint.setY(this.pressedPoint.getY());
        } else {
            topLeftPoint.setY(this.releasedPoint.getY());
        }

        int width = Math.abs(this.releasedPoint.getX() - this.pressedPoint.getX());
        int height = Math.abs(this.releasedPoint.getY() - this.pressedPoint.getY());

        // getGraphics2D
        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.setColor(this.primaryColor);
            graphics2D.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            graphics2D.setColor(this.primaryColor);
            graphics2D.fillRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
            System.out.println("fillRect");

        } else {
            graphics2D.setColor(this.primaryColor);
            graphics2D.fillRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.setColor(this.secondaryColor);
            graphics2D.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
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
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }
}
