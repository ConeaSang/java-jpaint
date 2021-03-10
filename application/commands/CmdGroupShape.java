package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;
import application.shapes.ShapeFactory;

import java.util.ArrayList;
import java.util.List;

public class CmdGroupShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private IShape m_shapeGroup;

    // Constructors
    public CmdGroupShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdGroupShape");

        // Check edge case
        if (this.m_shapeRepo.getSelectedShapeList().size() == 0) {
            System.out.println("Cannot group: No shape selected");
            return;
        }

        // Create ShapeGroup instance
        List<IShape> tmpGroupList = new ArrayList<>();

        for (IShape s : this.m_shapeRepo.getSelectedShapeList()) {
            tmpGroupList.add(s);
        }

        this.m_shapeGroup = ShapeFactory.createShapeGroup(tmpGroupList);

        // Update selectedShapeList and mainShapeList in the shapeRepo
        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.removeAll(this.m_shapeGroup.getChildren());
        selectedList.add(this.m_shapeGroup);

        this.m_shapeRepo.remove(this.m_shapeGroup.getChildren());
        this.m_shapeRepo.add(this.m_shapeGroup);

        CommandHistory.add(this);

        System.out.print("____________   - ");
        this.m_shapeRepo.printSizeOfAllList();
    }

    @Override
    public void undo() {
        // Update selectedShapeList and mainShapeList in the shapeRepo
        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.remove(this.m_shapeGroup);
        selectedList.addAll(this.m_shapeGroup.getChildren());

        this.m_shapeRepo.remove(this.m_shapeGroup);
        this.m_shapeRepo.add(this.m_shapeGroup.getChildren());
    }

    @Override
    public void redo() {
        // Update selectedShapeList and mainShapeList in the shapeRepo
        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.removeAll(this.m_shapeGroup.getChildren());
        selectedList.add(this.m_shapeGroup);

        this.m_shapeRepo.remove(this.m_shapeGroup.getChildren());
        this.m_shapeRepo.add(this.m_shapeGroup);
    }
}
