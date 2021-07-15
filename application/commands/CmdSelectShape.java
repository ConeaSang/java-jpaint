package application.commands;

import application.Point;
import application.observers.ShapeRepository;
import application.shapes.IShape;
import model.interfaces.IApplicationState;

import java.util.List;

public class CmdSelectShape implements ICommand {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final Point m_topLeftCollision;
    private final Point m_bottomRightCollision;

    // Constructors
    public CmdSelectShape(IApplicationState appState, ShapeRepository shapeRepo, application.Point pressedPoint, application.Point releasedPoint) {
        this.m_shapeRepo = shapeRepo;

        this.m_topLeftCollision = new Point(0, 0);
        this.m_bottomRightCollision = new Point(0, 0);

        if (pressedPoint.getX() < releasedPoint.getX()) {
            this.m_topLeftCollision.setX(pressedPoint.getX());
            this.m_bottomRightCollision.setX(releasedPoint.getX());
        } else {
            this.m_topLeftCollision.setX(releasedPoint.getX());
            this.m_bottomRightCollision.setX(pressedPoint.getX());
        }

        if (pressedPoint.getY() < releasedPoint.getY()) {
            this.m_topLeftCollision.setY(pressedPoint.getY());
            this.m_bottomRightCollision.setY(releasedPoint.getY());
        } else {
            this.m_topLeftCollision.setY(releasedPoint.getY());
            this.m_bottomRightCollision.setY(pressedPoint.getY());
        }
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdSelectShape");

        List<IShape> selectedList = this.m_shapeRepo.getSelectedShapeList();

        selectedList.clear();

        for (IShape s : this.m_shapeRepo.getMainShapeList()) {
            if (this.m_shapeRepo.checkCollision(s, this.m_topLeftCollision, this.m_bottomRightCollision)) {
                System.out.println("Collided");
                selectedList.add(s);
            } else {
                System.out.println("Not collided");
            }
        }

        this.m_shapeRepo.reDrawAllShapes();

        System.out.print("____________   - ");
        this.m_shapeRepo.printSizeOfAllList();
    }
}
