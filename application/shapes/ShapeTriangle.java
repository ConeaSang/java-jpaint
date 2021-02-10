package application.shapes;

import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.BasicStroke;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo _shapeInfo) {
        this.paintCanvas = _shapeInfo.getPaintCanvas();
        this.graphics2D = _shapeInfo.getPaintCanvas().getGraphics2D();
        this.shapeType = _shapeInfo.getShapeType();
        this.primaryColor = _shapeInfo.getPrimaryColor();
        this.secondaryColor = _shapeInfo.getSecondaryColor();
        this.shadingType = _shapeInfo.getShadingType();

        this.pressedPoint = _shapeInfo.getPressedPoint();
        this.releasedPoint = _shapeInfo.getReleasedPoint();
    }

    // Methods
    @Override
    public void draw() {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.pressedPoint.getX();
        xArray[1] = this.releasedPoint.getX();
        xArray[2] = this.pressedPoint.getX();

        yArray[0] = this.pressedPoint.getY();
        yArray[1] = this.releasedPoint.getY();
        yArray[2] = this.releasedPoint.getY();

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            this.graphics2D.setStroke(new BasicStroke(5));
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.drawPolygon(xArray, yArray, 3);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.fillPolygon(xArray, yArray, 3);
            System.out.println("fillPolygon");

        } else {
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.fillPolygon(xArray, yArray, 3);

            this.graphics2D.setStroke(new BasicStroke(5));
            this.graphics2D.setColor(this.secondaryColor);
            this.graphics2D.drawPolygon(xArray, yArray, 3);
        }
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }
}
