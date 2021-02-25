package application.commands;

import application.shapes.IShape;
import application.observers.ShapeRepository;
import model.interfaces.IApplicationState;

import java.util.ArrayList;
import java.util.List;

public class CmdMoveShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final int m_deltaX;
    private final int m_deltaY;
    private final List<IShape> m_localMoveShapeList;

    // Constructors
    public CmdMoveShape(IApplicationState appState, ShapeRepository shapeRepo, application.Point pressedPoint, application.Point releasedPoint) {
        this.m_shapeRepo = shapeRepo;
        this.m_deltaX = releasedPoint.getX() - pressedPoint.getX();
        this.m_deltaY = releasedPoint.getY() - pressedPoint.getY();
        this.m_localMoveShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdMoveShape");

        //this.m_shapeRepo.moveSelectedShapes(this.m_deltaX, this.m_deltaY);

        for (IShape s : this.m_shapeRepo.getSelectedShapeList()) {
            s.translateAllPoint(this.m_deltaX, this.m_deltaY);
        }

        this.m_shapeRepo.reDrawAllShapes();

        this.setLocalMoveShapeList(m_shapeRepo.getSelectedShapeList());

        CommandHistory.add(this);

        System.out.print("____________   - ");
        this.m_shapeRepo.printSizeOfAllList();
    }

    @Override
    public void undo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.m_localMoveShapeList) {
            s.translateAllPoint(-this.m_deltaX, -this.m_deltaY);
        }

        this.m_shapeRepo.reDrawAllShapes();
    }

    @Override
    public void redo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.m_localMoveShapeList) {
            s.translateAllPoint(this.m_deltaX, this.m_deltaY);
        }

        this.m_shapeRepo.reDrawAllShapes();
    }

    public void setLocalMoveShapeList(List<IShape> shapeList) {
        this.m_localMoveShapeList.clear();

        this.m_localMoveShapeList.addAll(shapeList);
    }
}

