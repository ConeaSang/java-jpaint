package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class CmdUngroupShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final List<IShape> m_localBeforeUngroupShapeList;
    private final List<IShape> m_localAfterUngroupShapeList;

    // Constructors
    public CmdUngroupShape(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
        this.m_localBeforeUngroupShapeList = new ArrayList<>();
        this.m_localAfterUngroupShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdUngroupShape");

        // Check edge case
        if (this.m_shapeRepo.getSelectedShapeList().size() == 0) {
            System.out.println("Cannot ungroup!: No shape selected");
            return;
        }

        this.m_localBeforeUngroupShapeList.addAll(this.m_shapeRepo.getSelectedShapeList());

        for (IShape s : this.m_localBeforeUngroupShapeList) {
            this.m_localAfterUngroupShapeList.addAll(s.ungroup());
        }

        // Check another edge case
        if (this.m_localBeforeUngroupShapeList.size() == this.m_localAfterUngroupShapeList.size()) {
            System.out.println("Cannot ungroup!: No shapeGroup selected");
            return;
        }

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_localBeforeUngroupShapeList);

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.addAll(this.m_localAfterUngroupShapeList);

        this.m_shapeRepo.add(this.m_localAfterUngroupShapeList);

        CommandHistory.add(this);

        //System.out.print("____________   - ");
        //this.m_shapeRepo.printSizeOfAllList();
    }

    @Override
    public void undo() {
        System.out.println("----------------> undo() CmdUngroupShape");

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_localAfterUngroupShapeList);

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.addAll((this.m_localBeforeUngroupShapeList));

        this.m_shapeRepo.add(this.m_localBeforeUngroupShapeList);
    }

    @Override
    public void redo() {
        System.out.println("----------------> redo() CmdUngroupShape");

        // Update mainShapeList and selectedShapeList in the shapeRepo
        this.m_shapeRepo.remove(this.m_localBeforeUngroupShapeList);

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();
        selectedList.addAll(this.m_localAfterUngroupShapeList);

        this.m_shapeRepo.add(this.m_localAfterUngroupShapeList);
    }
}
