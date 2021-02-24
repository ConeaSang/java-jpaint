package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class CmdPasteShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final List<IShape> m_localPasteShapeList;

    // Constructors
    public CmdPasteShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_localPasteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdPasteShape");

        for (IShape s : this.m_shapeRepo.getClipboardShapeList()) {
            ShapeInfo shapeInfo = new ShapeInfo(s.getShapeInfo());
            IShape shape;

            if (shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
                shape = ShapeFactory.createShapeEllipse(shapeInfo);
            } else if (shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
                shape = ShapeFactory.createShapeRectangle(shapeInfo);
            } else {
                shape = ShapeFactory.createShapeTriangle(shapeInfo);
            }

            this.m_localPasteShapeList.add(shape);
        }

        this.m_shapeRepo.add(this.m_localPasteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.m_shapeRepo.remove(this.m_localPasteShapeList);
    }

    @Override
    public void redo() {
        this.m_shapeRepo.add(this.m_localPasteShapeList);
    }
}
