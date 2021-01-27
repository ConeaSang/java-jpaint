package application.shapes;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeRectangle extends Shape{
    // Data

    // Constructors
    public ShapeRectangle(ShapeInfo _shapeInfo)
    {
        this.paintCanvas = _shapeInfo.getPaintCanvas();
        this.shapeType = _shapeInfo.getShapeType();
        this.pointTopLeft = _shapeInfo.getPointTopLeft();
        this.width = _shapeInfo.getWidth();
        this.height = _shapeInfo.getHeight();
    }

    // Methods
    @Override
    public void draw() {
        // Only Green Rectangle for now
        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(this.pointTopLeft.getX(), this.pointTopLeft.getY(), this.width, this.height);
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }
}
