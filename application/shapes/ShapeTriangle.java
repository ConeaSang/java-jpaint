package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo shapeInfo) {
        this.m_shapeInfo = shapeInfo;

        // Find topLeftPoint & bottomRightPoint
        this.m_topLeftPoint = new Point(0, 0);
        this.m_bottomRightPoint = new Point(0, 0);

        if (this.m_shapeInfo.getPressedPoint().getX() < this.m_shapeInfo.getReleasedPoint().getX()) {
            this.m_topLeftPoint.setX(this.m_shapeInfo.getPressedPoint().getX());
            this.m_bottomRightPoint.setX(this.m_shapeInfo.getReleasedPoint().getX());
        } else {
            this.m_topLeftPoint.setX(this.m_shapeInfo.getReleasedPoint().getX());
            this.m_bottomRightPoint.setX(this.m_shapeInfo.getPressedPoint().getX());
        }

        if (this.m_shapeInfo.getPressedPoint().getY() < this.m_shapeInfo.getReleasedPoint().getY()) {
            this.m_topLeftPoint.setY(this.m_shapeInfo.getPressedPoint().getY());
            this.m_bottomRightPoint.setY(this.m_shapeInfo.getReleasedPoint().getY());
        } else {
            this.m_topLeftPoint.setY(this.m_shapeInfo.getReleasedPoint().getY());
            this.m_bottomRightPoint.setY(this.m_shapeInfo.getPressedPoint().getY());
        }
    }

    // Methods
    @Override
    public void draw(Graphics2D g2D) {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.m_shapeInfo.getPressedPoint().getX();
        xArray[1] = this.m_shapeInfo.getReleasedPoint().getX();
        xArray[2] = this.m_shapeInfo.getPressedPoint().getX();

        yArray[0] = this.m_shapeInfo.getPressedPoint().getY();
        yArray[1] = this.m_shapeInfo.getReleasedPoint().getY();
        yArray[2] = this.m_shapeInfo.getReleasedPoint().getY();

        // ShadingType
        if (this.m_shapeInfo.getShadingType() == ShapeShadingType.OUTLINE) {
            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.drawPolygon(xArray, yArray, 3);

        } else if (this.m_shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);
            //System.out.println("fillPolygon");

        } else {
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);

            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.m_shapeInfo.getSecondaryColor());
            g2D.drawPolygon(xArray, yArray, 3);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        //int width = Math.abs(this.m_shapeInfo.getReleasedPoint().getX() - this.m_shapeInfo.getPressedPoint().getX());
        //int height = Math.abs(this.m_shapeInfo.getReleasedPoint().getY() - this.m_shapeInfo.getPressedPoint().getY());
        int width = Math.abs(this.m_bottomRightPoint.getX() - this.m_topLeftPoint.getX());
        int height = Math.abs(this.m_bottomRightPoint.getY() - this.m_topLeftPoint.getY());

        float ratio = ((float)width / (float)height);
        if (ratio > 16.0f) {
            ratio = 16.0f;
        } else if (ratio < 0.0625f) {
            ratio = 0.0625f;
        }
        //System.out.println("ratio: " + ratio);
        int adjustX = (int)(6 * ratio * 1.414f);
        int adjustY = (int)(6 / ratio * 1.414f);
        //System.out.println("adjustX: " + adjustX + ",     adjustY: " + adjustY);

        int[] xArray = new int[3];
        int[] yArray = new int[3];

        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 0);

        if (this.m_shapeInfo.getPressedPoint().getX() < this.m_shapeInfo.getReleasedPoint().getX()) {
            if (this.m_shapeInfo.getPressedPoint().getY() < this.m_shapeInfo.getReleasedPoint().getY()) {
                p0.setXY(this.m_shapeInfo.getPressedPoint().getX() - 6, this.m_shapeInfo.getPressedPoint().getY() - 6 - adjustY);
                p1.setXY(this.m_shapeInfo.getReleasedPoint().getX() + 6 + adjustX, this.m_shapeInfo.getReleasedPoint().getY() + 6);
            } else {
                p0.setXY(this.m_shapeInfo.getPressedPoint().getX() - 6, this.m_shapeInfo.getPressedPoint().getY() + 6 + adjustY);
                p1.setXY(this.m_shapeInfo.getReleasedPoint().getX() + 6 + adjustX, this.m_shapeInfo.getReleasedPoint().getY() - 6);
            }
        } else {
            if (this.m_shapeInfo.getPressedPoint().getY() < this.m_shapeInfo.getReleasedPoint().getY()) {
                p0.setXY(this.m_shapeInfo.getPressedPoint().getX() + 6, this.m_shapeInfo.getPressedPoint().getY() - 6 - adjustY);
                p1.setXY(this.m_shapeInfo.getReleasedPoint().getX() - 6 - adjustX, this.m_shapeInfo.getReleasedPoint().getY() + 6);
            } else {
                p0.setXY(this.m_shapeInfo.getPressedPoint().getX() + 6, this.m_shapeInfo.getPressedPoint().getY() + 6 + adjustY);
                p1.setXY(this.m_shapeInfo.getReleasedPoint().getX() - 6 - adjustX, this.m_shapeInfo.getReleasedPoint().getY() - 6);
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

    //@Override
    //public ShapeInfo getShapeInfo() {
    //    return this.m_shapeInfo;
    //}

    @Override
    public IShape deepCopyShape() {
        ShapeInfo newShapeInfo = new ShapeInfo(this.m_shapeInfo);

        return ShapeFactory.createShapeTriangle(newShapeInfo);
    }

    @Override
    public Point getTopLeftPoint() {
        return this.m_topLeftPoint;
    }

    @Override
    public Point getBottomRightPoint() {
        return this.m_bottomRightPoint;
    }

    @Override
    public void translateAllPoint(int deltaX, int deltaY) {
        this.m_shapeInfo.getPressedPoint().setXY(this.m_shapeInfo.getPressedPoint().getX() + deltaX, this.m_shapeInfo.getPressedPoint().getY() + deltaY);
        this.m_shapeInfo.getReleasedPoint().setXY(this.m_shapeInfo.getReleasedPoint().getX() + deltaX, this.m_shapeInfo.getReleasedPoint().getY() + deltaY);
        this.m_topLeftPoint.setXY(this.m_topLeftPoint.getX() + deltaX, this.m_topLeftPoint.getY() + deltaY);
        this.m_bottomRightPoint.setXY(this.m_bottomRightPoint.getX() + deltaX, this.m_bottomRightPoint.getY() + deltaY);
    }

    @Override
    public List<IShape> getChildren() {
        return null;
    }

    @Override
    public List<IShape> ungroup() {
        List<IShape> tmpShapeList = new ArrayList<>();
        tmpShapeList.add(this);
        return tmpShapeList;
    }
}
