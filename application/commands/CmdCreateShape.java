package application.commands;

import application.Point;
import application.color.ColorTranslation;
import application.observers.ShapeRepository;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class CmdCreateShape implements ICommand, IUndoable {
    // Data
    private ShapeInfo shapeInfo;
    private ShapeRepository shapeRepo;
    private IShape shape;

    // Constructors
    public CmdCreateShape(IApplicationState _appState, ShapeRepository _shapeRepo, application.Point _pressedPoint, application.Point _releasedPoint) {
        this.shapeInfo = new ShapeInfo();

        this.shapeRepo = _shapeRepo;

        // Set values
        this.shapeInfo.setShapeType(_appState.getActiveShapeType())
                      .setPrimaryColor(ColorTranslation.getColor(_appState.getActivePrimaryColor()))
                      .setSecondaryColor(ColorTranslation.getColor(_appState.getActiveSecondaryColor()))
                      .setShadingType(_appState.getActiveShapeShadingType())
                      .setPressedPoint(_pressedPoint)
                      .setReleasedPoint(_releasedPoint);
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCreateShape");

        // Call the ShapeFactory
        if (this.shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
            this.shape = ShapeFactory.createShapeEllipse(this.shapeInfo);
        } else if (this.shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
            this.shape = ShapeFactory.createShapeRectangle(this.shapeInfo);
        } else {
            this.shape = ShapeFactory.createShapeTriangle(this.shapeInfo);
        }

        this.shapeRepo.add(this.shape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.shapeRepo.remove(this.shape);
    }

    @Override
    public void redo() {
        this.shapeRepo.add(this.shape);
    }
}
