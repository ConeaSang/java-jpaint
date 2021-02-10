package application.shapes;

public class ShapeFactory {
    // Constructors
    private ShapeFactory() {
    }

    // Methods
    public static IShape createShapeEllipse(ShapeInfo _shapeInfo) {
        return new ShapeEllipse(_shapeInfo);
    }

    public static IShape createShapeRectangle(ShapeInfo _shapeInfo) {
        return new ShapeRectangle(_shapeInfo);
    }

    public static IShape createShapeTriangle(ShapeInfo _shapeInfo) {
        return new ShapeTriangle(_shapeInfo);
    }
}
