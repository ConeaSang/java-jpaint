package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdDeleteShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final List<IShape> m_localBeforeDeleteShapeList;

    // Constructors
    public CmdDeleteShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_localBeforeDeleteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdDeleteShape");

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();

        this.m_localBeforeDeleteShapeList.addAll(selectedList);

        this.m_shapeRepo.remove(m_localBeforeDeleteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("----------------> undo() CmdDeleteShape");
        this.m_shapeRepo.add(this.m_localBeforeDeleteShapeList);
    }

    @Override
    public void redo() {
        System.out.println("----------------> redo() CmdDeleteShape");
        this.m_shapeRepo.remove(this.m_localBeforeDeleteShapeList);
    }
}
