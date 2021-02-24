package application.commands;

import application.shapes.IShape;
import application.observers.ShapeRepository;
import model.interfaces.IApplicationState;

import java.util.ArrayList;
import java.util.List;

public class CmdMoveShape implements ICommand, IUndoable {
    // Data
    private ShapeRepository shapeRepo;
    private int deltaX;
    private int deltaY;
    private List<IShape> localMoveShapeList;

    // Constructors
    public CmdMoveShape(IApplicationState _appState, ShapeRepository _shapeRepo, application.Point _pressedPoint, application.Point _releasedPoint) {
        this.shapeRepo = _shapeRepo;
        this.deltaX = _releasedPoint.getX() - _pressedPoint.getX();
        this.deltaY = _releasedPoint.getY() - _pressedPoint.getY();
        this.localMoveShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdMoveShape");

        this.shapeRepo.moveSelectedShapes(this.deltaX, this.deltaY);

        this.setLocalMoveShapeList(shapeRepo.getSelectedShapeList());

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.localMoveShapeList) {
            s.translateAllPoint(-this.deltaX, -this.deltaY);
        }

        this.shapeRepo.reDrawAllShapes();
    }

    @Override
    public void redo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.localMoveShapeList) {
            s.translateAllPoint(this.deltaX, this.deltaY);
        }

        this.shapeRepo.reDrawAllShapes();
    }

    public void setLocalMoveShapeList(List<IShape> _shapeList) {
        this.localMoveShapeList.clear();

        for (IShape s : _shapeList) {
            this.localMoveShapeList.add(s);
        }
    }
}

