package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdDeleteShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final List<IShape> m_localDeleteShapeList;

    // Constructors
    public CmdDeleteShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_localDeleteShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdDeleteShape");

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();

        this.m_localDeleteShapeList.addAll(selectedList);

        this.m_shapeRepo.remove(m_localDeleteShapeList);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.m_shapeRepo.add(this.m_localDeleteShapeList);
    }

    @Override
    public void redo() {
        this.m_shapeRepo.remove(this.m_localDeleteShapeList);
    }
}
