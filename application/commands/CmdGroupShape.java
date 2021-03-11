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
            System.out.println("Cannot group!: No shape selected");
            return;
        }

        if (this.m_shapeRepo.getSelectedShapeList().size() == 1) {
            System.out.println("Cannot group!: Only 1 shape selected");
            return;
        }

        // Create ShapeGroup instance by calling ShapeFactory
        //List<IShape> tmpGroupList = new ArrayList<>();
        //tmpGroupList.addAll(this.m_shapeRepo.getSelectedShapeList());
        List<IShape> tmpGroupList = new ArrayList<>(this.m_shapeRepo.getSelectedShapeList());

        this.m_shapeGroup = ShapeFactory.createShapeGroup(tmpGroupList);

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_shapeGroup.getChildren());

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.add(this.m_shapeGroup);

        this.m_shapeRepo.add(this.m_shapeGroup);

        CommandHistory.add(this);

        //System.out.print("____________   - ");
        //this.m_shapeRepo.printSizeOfAllList();
    }

    @Override
    public void undo() {
        System.out.println("----------------> undo() CmdGroupShape");

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_shapeGroup);

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();

        //if (selectedList.contains(this.m_shapeGroup)) {
            selectedList.addAll(this.m_shapeGroup.getChildren());
       // }

        this.m_shapeRepo.add(this.m_shapeGroup.getChildren());
    }

    @Override
    public void redo() {
        System.out.println("----------------> redo() CmdGroupShape");

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_shapeGroup.getChildren());

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();

        //if (selectedList.containsAll(this.m_shapeGroup.getChildren())) {
            selectedList.add(this.m_shapeGroup);
        //}

        this.m_shapeRepo.add(this.m_shapeGroup);
    }
}
