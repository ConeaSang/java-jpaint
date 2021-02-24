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
    private final ShapeRepository shapeRepo;
    private final List<IShape> localPasteShapeList;

    // Constructors
    public CmdPasteShape(ShapeRepository _shapeRepo) {
        this.shapeRepo = _shapeRepo;
        this.localPasteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdPasteShape");

        for (IShape s : this.shapeRepo.getClipboardShapeList()) {
            ShapeInfo shapeInfo = new ShapeInfo(s.getShapeInfo());
            IShape shape;

            if (shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
                shape = ShapeFactory.createShapeEllipse(shapeInfo);
            } else if (shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
                shape = ShapeFactory.createShapeRectangle(shapeInfo);
            } else {
                shape = ShapeFactory.createShapeTriangle(shapeInfo);
            }

            this.localPasteShapeList.add(shape);
        }

        this.shapeRepo.add(this.localPasteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.shapeRepo.remove(this.localPasteShapeList);
    }

    @Override
    public void redo() {
        this.shapeRepo.add(this.localPasteShapeList);
    }
}
