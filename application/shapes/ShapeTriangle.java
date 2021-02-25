package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo shapeInfo) {
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
            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.drawPolygon(xArray, yArray, 3);

        } else if (this.shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);
            //System.out.println("fillPolygon");

        } else {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);

            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getSecondaryColor());
            g2D.drawPolygon(xArray, yArray, 3);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        int width = Math.abs(this.shapeInfo.getReleasedPoint().getX() - this.shapeInfo.getPressedPoint().getX());
        int height = Math.abs(this.shapeInfo.getReleasedPoint().getY() - this.shapeInfo.getPressedPoint().getY());

        float ratio = ((float)width / (float)height);
        int adjustX = (int)(6 * ratio * 1.414f);
        int adjustY = (int)(6 / ratio * 1.414f);

        //System.out.println("adjustX: " + adjustX + ",     adjustY: " + adjustY);

        int[] xArray = new int[3];
        int[] yArray = new int[3];

        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 0);

        if (this.shapeInfo.getPressedPoint().getX() < this.shapeInfo.getReleasedPoint().getX()) {
            if (this.shapeInfo.getPressedPoint().getY() < this.shapeInfo.getReleasedPoint().getY()) {
                p0.setXY(this.shapeInfo.getPressedPoint().getX() - 6, this.shapeInfo.getPressedPoint().getY() - 6 - adjustY);
                p1.setXY(this.shapeInfo.getReleasedPoint().getX() + 6 + adjustX, this.shapeInfo.getReleasedPoint().getY() + 6);
            } else {
                p0.setXY(this.shapeInfo.getPressedPoint().getX() - 6, this.shapeInfo.getPressedPoint().getY() + 6 + adjustY);
                p1.setXY(this.shapeInfo.getReleasedPoint().getX() + 6 + adjustX, this.shapeInfo.getReleasedPoint().getY() - 6);
            }
        } else {
            if (this.shapeInfo.getPressedPoint().getY() < this.shapeInfo.getReleasedPoint().getY()) {
                p0.setXY(this.shapeInfo.getPressedPoint().getX() + 6, this.shapeInfo.getPressedPoint().getY() - 6 - adjustY);
                p1.setXY(this.shapeInfo.getReleasedPoint().getX() - 6 - adjustX, this.shapeInfo.getReleasedPoint().getY() + 6);
            } else {
                p0.setXY(this.shapeInfo.getPressedPoint().getX() + 6, this.shapeInfo.getPressedPoint().getY() + 6 + adjustY);
                p1.setXY(this.shapeInfo.getReleasedPoint().getX() - 6 - adjustX, this.shapeInfo.getReleasedPoint().getY() - 6);
            }
        }

        xArray[0] = p0.getX();
        xArray[1] = p1.getX();
        xArray[2] = p0.getX();

        yArray[0] = p0.getY();
        yArray[1] = p1.getY();
        yArray[2] = p1.getY();

        g2D.drawPolygon(xArray, yArray, 3);
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
