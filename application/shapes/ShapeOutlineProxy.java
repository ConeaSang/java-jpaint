package application.shapes;

import application.Point;

import java.awt.*;
import java.util.List;

public class ShapeOutlineProxy implements IShape {
    // Data
    private final IShape m_shape;

    // Constructors
    public ShapeOutlineProxy(IShape shape) {
        this.m_shape = shape;
    }

    // Methods
    @Override
    public void draw(Graphics2D g2D) {
        // Drawing the outline
        g2D.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[] { 9 }, 0));
        g2D.setColor(Color.BLACK);

        this.m_shape.drawOutline(g2D);
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        // This method should not be called
        System.out.println("This method should not be called.");
    }

    //@Override
    //public ShapeInfo getShapeInfo() {
    //    // This method should not be called
    //    System.out.println("This method should not be called.");
    //    return this.m_shape.getShapeInfo();
    //}

    @Override
    public IShape deepCopyShape() {
        // This method should not be called
        System.out.println("This method should not be called.");
        return null;
    }

    @Override
    public Point getTopLeftPoint() {
        // This method should not be called
        System.out.println("This method should not be called.");
        return this.m_shape.getTopLeftPoint();
    }

    @Override
    public Point getBottomRightPoint() {
        // This method should not be called
        System.out.println("This method should not be called.");
        return this.m_shape.getBottomRightPoint();
    }

    @Override
    public void translateAllPoint(int deltaX, int deltaY) {
        // This method should not be called
        System.out.println("This method should not be called.");
        this.m_shape.translateAllPoint(deltaX, deltaY);
    }

    //@Override
    //public List<IShape> getChildren() {
    //    // This method should not be called
    //    System.out.println("This method should not be called.");
    //    return null;
    //}

    @Override
    public List<IShape> ungroup() {
        // This method should not be called
        System.out.println("This method should not be called.");
        return null;
    }
}
