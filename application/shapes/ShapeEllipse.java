package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;

public class ShapeEllipse extends Shape {
    // Data

    // Constructors
    public ShapeEllipse(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;

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
    public void draw(Graphics2D g2D) {
        int width = Math.abs(this.shapeInfo.getReleasedPoint().getX() - this.shapeInfo.getPressedPoint().getX());
        int height = Math.abs(this.shapeInfo.getReleasedPoint().getY() - this.shapeInfo.getPressedPoint().getY());

        // ShadingType
        if (this.shapeInfo.getShadingType() == ShapeShadingType.OUTLINE) {
            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.drawOval(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);

        } else if (this.shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillOval(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);
            //System.out.println("fillOval");

        } else {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillOval(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);

            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getSecondaryColor());
            g2D.drawOval(this.topLeftPoint.getX(), this.topLeftPoint.getY(), width, height);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        int width = Math.abs(this.shapeInfo.getReleasedPoint().getX() - this.shapeInfo.getPressedPoint().getX());
        int height = Math.abs(this.shapeInfo.getReleasedPoint().getY() - this.shapeInfo.getPressedPoint().getY());

        g2D.drawOval(this.topLeftPoint.getX() - 5, this.topLeftPoint.getY() - 5, width + 10, height + 10);
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
    public void translateAllPoint(int deltaX, int deltaY) {
        this.shapeInfo.getPressedPoint().setXY(this.shapeInfo.getPressedPoint().getX() + deltaX, this.shapeInfo.getPressedPoint().getY() + deltaY);
        this.shapeInfo.getReleasedPoint().setXY(this.shapeInfo.getReleasedPoint().getX() + deltaX, this.shapeInfo.getReleasedPoint().getY() + deltaY);
        this.topLeftPoint.setXY(this.topLeftPoint.getX() + deltaX, this.topLeftPoint.getY() + deltaY);
        this.bottomRightPoint.setXY(this.bottomRightPoint.getX() + deltaX, this.bottomRightPoint.getY() + deltaY);
    }
}
