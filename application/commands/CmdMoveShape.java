package application.commands;

import application.shapes.ShapeRepository;
import model.interfaces.IApplicationState;

public class CmdMoveShape implements ICommand {
    // Data
    private int deltaX;
    private int deltaY;

    // Constructors
    public CmdMoveShape(IApplicationState _appState, application.Point _pressedPoint, application.Point _releasedPoint) {
        this.deltaX = _releasedPoint.getX() - _pressedPoint.getX();
        this.deltaY = _releasedPoint.getY() - _pressedPoint.getY();


    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdMoveShape");

        ShapeRepository.updateMainShapeListForMove(this.deltaX, this.deltaY);
    }
}
