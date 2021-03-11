package application.shapes;

import java.util.List;

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

    public static IShape createShapeGroup(List<IShape> shapeList) {
        return new ShapeGroup(shapeList);
    }
}
