package application.commands;

import application.shapes.IShape;
import application.shapes.ShapeRepository;
import model.interfaces.IApplicationState;

import java.util.ArrayList;

public class CmdMoveShape implements ICommand, IUndoable {
    // Data
    private int deltaX;
    private int deltaY;
    private ArrayList<IShape> localMoveShapeList;

    // Constructors
    public CmdMoveShape(IApplicationState _appState, application.Point _pressedPoint, application.Point _releasedPoint) {
        this.deltaX = _releasedPoint.getX() - _pressedPoint.getX();
        this.deltaY = _releasedPoint.getY() - _pressedPoint.getY();
        this.localMoveShapeList = new ArrayList<>();
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdMoveShape");

        ShapeRepository.updateMainShapeListForMove(this.deltaX, this.deltaY);

        this.setLocalMoveShapeList(ShapeRepository.getSelectedShapeList());

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.localMoveShapeList) {
            s.translateAllPoint(-this.deltaX, -this.deltaY);
        }

        ShapeRepository.deleteAll();
        ShapeRepository.drawAll();
    }

    @Override
    public void redo() {
        // This loop will also update the mainShapeList in ShapeRepository
        for (IShape s : this.localMoveShapeList) {
            s.translateAllPoint(this.deltaX, this.deltaY);
        }

        ShapeRepository.deleteAll();
        ShapeRepository.drawAll();
    }

    public void setLocalMoveShapeList(ArrayList<IShape> _shapeList) {
        this.localMoveShapeList.clear();

        for (IShape s : _shapeList) {
            this.localMoveShapeList.add(s);
        }
    }
}

