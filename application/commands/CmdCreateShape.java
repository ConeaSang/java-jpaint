package application.commands;

import application.Point;
import application.color.ColorTranslation;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class CmdCreateShape implements ICommand {
    // Data
    private ShapeInfo shapeInfo;

    // Constructors
    public CmdCreateShape(IApplicationState _appState, application.Point _pressedPoint, application.Point _releasedPoint) {
        //this.shapeInfo = new ShapeInfo(_paintCanvas);
        this.shapeInfo = new ShapeInfo();

        // Set values
        this.shapeInfo.setShapeType(_appState.getActiveShapeType())
                      .setPrimaryColor(ColorTranslation.getColor(_appState.getActivePrimaryColor()))
                      .setSecondaryColor(ColorTranslation.getColor(_appState.getActiveSecondaryColor()))
                      .setShadingType(_appState.getActiveShapeShadingType())
                      .setPressedPoint(_pressedPoint)
                      .setReleasedPoint(_releasedPoint);

//        Point pointTmp = new Point(0,0);
//
//        if (_pressedPoint.getX() < _releasedPoint.getX()) {
//            pointTmp.setX(_pressedPoint.getX());
//        } else {
//            pointTmp.setX(_releasedPoint.getX());
//        }
//
//        if (_pressedPoint.getY() < _releasedPoint.getY()) {
//            pointTmp.setY(_pressedPoint.getY());
//        } else {
//            pointTmp.setY(_releasedPoint.getY());
//        }
//
//        this.shapeInfo.setPointTopLeft(pointTmp);
//
//        this.shapeInfo.setWidth(Math.abs(_releasedPoint.getX() - _pressedPoint.getX()));
//        this.shapeInfo.setHeight(Math.abs(_releasedPoint.getY() - _pressedPoint.getY()));
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

        ICommand cmd = new CmdAddToRepo(shape);
        cmd.execute();
    }
}
