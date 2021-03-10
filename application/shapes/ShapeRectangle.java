package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;
import java.util.List;

public class ShapeRectangle extends Shape {
    // Data

    // Constructors
    public ShapeRectangle(ShapeInfo shapeInfo) {
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
        int width = Math.abs(this.m_shapeInfo.getReleasedPoint().getX() - this.m_shapeInfo.getPressedPoint().getX());
        int height = Math.abs(this.m_shapeInfo.getReleasedPoint().getY() - this.m_shapeInfo.getPressedPoint().getY());

        // ShadingType
        if (this.m_shapeInfo.getShadingType() == ShapeShadingType.OUTLINE) {
            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.drawRect(this.m_topLeftPoint.getX(), this.m_topLeftPoint.getY(), width, height);

        } else if (this.m_shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.fillRect(this.m_topLeftPoint.getX(), this.m_topLeftPoint.getY(), width, height);
            //System.out.println("fillRect");

        } else {
            g2D.setColor(this.m_shapeInfo.getPrimaryColor());
            g2D.fillRect(this.m_topLeftPoint.getX(), this.m_topLeftPoint.getY(), width, height);

            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.m_shapeInfo.getSecondaryColor());
            g2D.drawRect(this.m_topLeftPoint.getX(), this.m_topLeftPoint.getY(), width, height);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        //int width = Math.abs(this.m_shapeInfo.getReleasedPoint().getX() - this.m_shapeInfo.getPressedPoint().getX());
        //int height = Math.abs(this.m_shapeInfo.getReleasedPoint().getY() - this.m_shapeInfo.getPressedPoint().getY());
        int width = Math.abs(this.m_bottomRightPoint.getX() - this.m_topLeftPoint.getX());
        int height = Math.abs(this.m_bottomRightPoint.getY() - this.m_topLeftPoint.getY());

        g2D.drawRect(this.m_topLeftPoint.getX() - 6, this.m_topLeftPoint.getY() - 6, width + 12, height + 12);
    }

    //@Override
    //public ShapeInfo getShapeInfo() {
    //    return this.m_shapeInfo;
    //}

    @Override
    public IShape deepCopyShape() {
        ShapeInfo newShapeInfo = new ShapeInfo(this.m_shapeInfo);

        return ShapeFactory.createShapeRectangle(newShapeInfo);
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
}
