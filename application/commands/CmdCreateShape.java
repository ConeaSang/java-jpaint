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

public class CmdCreateShape implements ICommand {
    // Data
    private ShapeInfo shapeInfo;
    private ShapeRepository shapeRepo;

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

        IShape shape;

        // Call the ShapeFactory
        if (this.shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
            shape = ShapeFactory.createShapeEllipse(this.shapeInfo);
        } else if (this.shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
            shape = ShapeFactory.createShapeRectangle(this.shapeInfo);
        } else {
            shape = ShapeFactory.createShapeTriangle(this.shapeInfo);
        }

        ICommand cmd = new CmdAddToRepo(this.shapeRepo, shape);
        cmd.execute();
    }
}
