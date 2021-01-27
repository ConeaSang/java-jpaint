package application.shapes;

public class ShapeFactory {
    // Constructors
    private ShapeFactory() {

    }

    // Methods
    public static IShape getShapeEllipse(ShapeInfo _shapeInfo) {
        return new ShapeEllipse(_shapeInfo);
    }

    public static IShape getShapeRectangle(ShapeInfo _shapeInfo) {
        return new ShapeRectangle(_shapeInfo);
    }

    public static IShape getShapeTriangle(ShapeInfo _shapeInfo) {
        return new ShapeTriangle(_shapeInfo);
    }
}
