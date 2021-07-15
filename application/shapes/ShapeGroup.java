package application.shapes;

import application.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Composite Pattern (Composite)
public class ShapeGroup implements IShapeGroup {
    // Data
    private final List<IShape> m_children;

    private final Point m_topLeftPoint;
    private final Point m_bottomRightPoint;

    // Constructors
    public ShapeGroup(List<IShape> shapeList) {
        this.m_children = new ArrayList<>();

        this.m_children.addAll(shapeList);

        // Find topLeftPoint & bottomRightPoint
        this.m_topLeftPoint = new Point(this.m_children.get(0).getTopLeftPoint());
        this.m_bottomRightPoint = new Point(this.m_children.get(0).getBottomRightPoint());

        for (IShape s : this.m_children) {
            if (s.getTopLeftPoint().getX() < this.m_topLeftPoint.getX()) {
                this.m_topLeftPoint.setX(s.getTopLeftPoint().getX());
            }

            if (s.getTopLeftPoint().getY() < this.m_topLeftPoint.getY()) {
                this.m_topLeftPoint.setY(s.getTopLeftPoint().getY());
            }

            if (s.getBottomRightPoint().getX() > this.m_bottomRightPoint.getX()) {
                this.m_bottomRightPoint.setX(s.getBottomRightPoint().getX());
            }

            if (s.getBottomRightPoint().getY() > this.m_bottomRightPoint.getY()) {
                this.m_bottomRightPoint.setY(s.getBottomRightPoint().getY());
            }
        }
    }

    // Methods
    @Override
    public void draw(Graphics2D g2D) {
        for (IShape s : this.m_children) {
            s.draw(g2D);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        int width = Math.abs(this.m_bottomRightPoint.getX() - this.m_topLeftPoint.getX());
        int height = Math.abs(this.m_bottomRightPoint.getY() - this.m_topLeftPoint.getY());

        g2D.drawRect(this.m_topLeftPoint.getX() - 6, this.m_topLeftPoint.getY() - 6, width + 12, height + 12);
    }

    @Override
    public IShape deepCopyShape() {
        List<IShape> childrenCopy = new ArrayList<>();

        for (IShape s : this.m_children) {
            IShape shape = s.deepCopyShape();

            childrenCopy.add(shape);
        }

        return ShapeGroupFactory.createShapeGroup(childrenCopy);
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
        this.m_topLeftPoint.setXY(this.m_topLeftPoint.getX() + deltaX, this.m_topLeftPoint.getY() + deltaY);
        this.m_bottomRightPoint.setXY(this.m_bottomRightPoint.getX() + deltaX, this.m_bottomRightPoint.getY() + deltaY);

        for (IShape s : this.m_children) {
            s.translateAllPoint(deltaX, deltaY);
        }
    }

    @Override
    public List<IShape> getChildren() {
        return this.m_children;
    }

    @Override
    public List<IShape> ungroup() {
        return this.m_children;
    }
}
