package application.shapes;

public class ShapeFactory {
    // Data

    // Constructors
    private ShapeFactory() {
    }

    // Methods
    public static IShape createShapeEllipse(ShapeInfo shapeInfo) {
        return new ShapeEllipse(shapeInfo);
    }

    public static IShape createShapeRectangle(ShapeInfo shapeInfo) {
        return new ShapeRectangle(shapeInfo);
    }

    public static IShape createShapeTriangle(ShapeInfo shapeInfo) {
        return new ShapeTriangle(shapeInfo);
    }
}
