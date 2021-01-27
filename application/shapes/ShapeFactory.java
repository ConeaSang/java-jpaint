package application.shapes;

public class ShapeFactory {
    // Constructors
    private ShapeFactory() {

    }

    // Methods
    public static IShape getShapeEllipse() {
        return new ShapeEllipse();
    }

    public static IShape getShapeRectangle() {
        return new ShapeRectangle();
    }

    public static IShape getShapeTriangle() {
        return new ShapeTriangle();
    }
}
