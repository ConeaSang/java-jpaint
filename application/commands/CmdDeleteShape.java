package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdDeleteShape implements ICommand, IUndoable {
    // Data
    private ShapeRepository shapeRepo;
    private List<IShape> localDeleteShapeList;

    // Constructors
    public CmdDeleteShape(ShapeRepository _shapeRepo) {
        this.shapeRepo = _shapeRepo;
        this.localDeleteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdDeleteShape");

        List<IShape> selectedList = this.shapeRepo.getSelectedShapeList();

        for (IShape s : selectedList) {
            this.localDeleteShapeList.add(s);
        }

        this.shapeRepo.remove(localDeleteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.shapeRepo.add(this.localDeleteShapeList);
    }

    @Override
    public void redo() {
        this.shapeRepo.remove(this.localDeleteShapeList);
    }
}
