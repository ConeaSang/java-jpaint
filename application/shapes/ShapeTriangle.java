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
            System.out.println("fillPolygon");

        } else {
            _graphics2D.setColor(this.primaryColor);
            _graphics2D.fillPolygon(xArray, yArray, 3);

            _graphics2D.setStroke(new BasicStroke(5));
            _graphics2D.setColor(this.secondaryColor);
            _graphics2D.drawPolygon(xArray, yArray, 3);
        }
    }

//    @Override
//    public PaintCanvasBase getPaintCanvas() {
//        return this.paintCanvas;
//    }
}
