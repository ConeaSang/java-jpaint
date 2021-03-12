package application.shapes;

import java.util.List;

public interface IShapeGroup extends IShape {
    List<IShape> getChildren();
}
