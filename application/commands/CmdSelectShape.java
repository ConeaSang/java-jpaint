package application.commands;

import application.Point;
import application.shapes.ShapeRepository;
import model.interfaces.IApplicationState;

public class CmdSelectShape implements ICommand {
    // Data
    private Point topLeftCollision;
    private Point bottomRightCollision;

    // Constructors
    public CmdSelectShape(IApplicationState _appState, application.Point _pressedPoint, application.Point _releasedPoint) {
        this.topLeftCollision = new Point(0, 0);
        this.bottomRightCollision = new Point(0, 0);

        if (_pressedPoint.getX() < _releasedPoint.getX()) {
            this.topLeftCollision.setX(_pressedPoint.getX());
            this.bottomRightCollision.setX(_releasedPoint.getX());
        } else {
            this.topLeftCollision.setX(_releasedPoint.getX());
            this.bottomRightCollision.setX(_pressedPoint.getX());
        }

        if (_pressedPoint.getY() < _releasedPoint.getY()) {
            this.topLeftCollision.setY(_pressedPoint.getY());
            this.bottomRightCollision.setY(_releasedPoint.getY());
        } else {
            this.topLeftCollision.setY(_releasedPoint.getY());
            this.bottomRightCollision.setY(_pressedPoint.getY());
        }
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdSelectShape");

        ShapeRepository.updateSelectedShapeListForCollision(this.topLeftCollision, this.bottomRightCollision);
    }
}
