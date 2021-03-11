package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdPasteShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final List<IShape> m_localAfterPasteShapeList;

    // Constructors
    public CmdPasteShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_localAfterPasteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdPasteShape");

        for (IShape s : this.m_shapeRepo.getClipboardShapeList()) {
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

            this.m_localAfterPasteShapeList.add(shape);
        }

        this.m_shapeRepo.add(this.m_localAfterPasteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("----------------> undo() CmdPasteShape");

        this.m_shapeRepo.remove(this.m_localAfterPasteShapeList);
    }

    @Override
    public void redo() {
        System.out.println("----------------> redo() CmdPasteShape");
        this.m_shapeRepo.add(this.m_localAfterPasteShapeList);
    }
}
