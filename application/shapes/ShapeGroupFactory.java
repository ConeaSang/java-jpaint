package application.shapes;

import java.util.List;

public class ShapeGroupFactory {
    // Data

    // Constructors
    private ShapeGroupFactory() {
    }

    // Methods
    public static IShapeGroup createShapeGroup(List<IShape> shapeList) {
        return new ShapeGroup(shapeList);
    }
}
