package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdCopyShape implements ICommand {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final int m_offset;

    // Constructors
    public CmdCopyShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_offset = 60;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCopyShape");

        List<IShape> tmpList = new ArrayList<>();

        for (IShape s : this.m_shapeRepo.getSelectedShapeList()) {
            //ShapeInfo shapeInfo = new ShapeInfo(s.getShapeInfo());
            //IShape shape;

            //if (shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
            //    shape = ShapeFactory.createShapeEllipse(shapeInfo);
            //} else if (shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
            //    shape = ShapeFactory.createShapeRectangle(shapeInfo);
            //} else {
            //    shape = ShapeFactory.createShapeTriangle(shapeInfo);
            //}

            IShape shape = s.deepCopyShape();

            shape.translateAllPoint(this.m_offset, this.m_offset);

            tmpList.add(shape);
        }

        this.m_shapeRepo.setClipboardShapeList(tmpList);

        System.out.print("____________   - ");
        this.m_shapeRepo.printSizeOfAllList();
    }
}
