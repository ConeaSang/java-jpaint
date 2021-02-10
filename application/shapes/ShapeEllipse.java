package application.shapes;

import application.Point;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeEllipse extends Shape {
    // Data

    // Constructors
    public ShapeEllipse(ShapeInfo _shapeInfo) {
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
    }

    // Methods
    @Override
    public void draw(Graphics2D _graphics2D) {
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
        //Graphics2D graphics2D = this.paintCanvas.getGraphics2D();

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            _graphics2D.setStroke(new BasicStroke(5));
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.drawOval(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.fillOval(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
            System.out.println("fillOval");

        } else {
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.fillOval(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

            _graphics2D.setStroke(new BasicStroke(5));
            _graphics2D.setColor(this.secondaryColor);
            _graphics2D.drawOval(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
        }
    }

//    @Override
//    public PaintCanvasBase getPaintCanvas() { return this.paintCanvas; }
}
