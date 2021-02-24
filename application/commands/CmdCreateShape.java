package application.commands;

import application.Point;
import application.color.ColorTranslation;
import application.observers.ShapeRepository;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;
import model.interfaces.IApplicationState;

public class CmdCreateShape implements ICommand, IUndoable {
    // Data
    private final ShapeRepository m_shapeRepo;
    private final ShapeInfo m_shapeInfo;
    private IShape m_shape;

    // Constructors
    public CmdCreateShape(IApplicationState appState, ShapeRepository shapeRepo, application.Point pressedPoint, application.Point releasedPoint) {
        this.m_shapeRepo = shapeRepo;

        this.m_shapeInfo = new ShapeInfo();

        // Set values
        this.m_shapeInfo.setShapeType(appState.getActiveShapeType())
                      .setPrimaryColor(ColorTranslation.getColor(appState.getActivePrimaryColor()))
                      .setSecondaryColor(ColorTranslation.getColor(appState.getActiveSecondaryColor()))
                      .setShadingType(appState.getActiveShapeShadingType())
                      .setPressedPoint(new Point(pressedPoint))
                      .setReleasedPoint(new Point(releasedPoint));
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCreateShape");

        // Call the ShapeFactory
        if (this.m_shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
            this.m_shape = ShapeFactory.createShapeEllipse(this.m_shapeInfo);
        } else if (this.m_shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
            this.m_shape = ShapeFactory.createShapeRectangle(this.m_shapeInfo);
        } else {
            this.m_shape = ShapeFactory.createShapeTriangle(this.m_shapeInfo);
        }

        this.m_shapeRepo.add(this.m_shape);

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.m_shapeRepo.remove(this.m_shape);
    }

    @Override
    public void redo() {
        this.m_shapeRepo.add(this.m_shape);
    }
}
