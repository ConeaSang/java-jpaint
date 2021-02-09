package application.commands;

import application.Point;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import application.shapes.ShapeRepository;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public class CmdCreateShape implements ICommand {
    // Data
    private ShapeInfo shapeInfo;

    // Constructors
    public CmdCreateShape(PaintCanvasBase _paintCanvas, application.Point _pointPressed, application.Point _pointReleased) {
        this.shapeInfo = new ShapeInfo(_paintCanvas);

        // Assume (Need to fix)
        this.shapeInfo.setShapeType(ShapeType.RECTANGLE);

        Point pointTmp = new Point(0,0);

        if (_pointPressed.getX() < _pointReleased.getX()) {
            pointTmp.setX(_pointPressed.getX());
        } else {
            pointTmp.setX(_pointReleased.getX());
        }

        if (_pointPressed.getY() < _pointReleased.getY()) {
            pointTmp.setY(_pointPressed.getY());
        } else {
            pointTmp.setY(_pointReleased.getY());
        }

        this.shapeInfo.setPointTopLeft(pointTmp);

        this.shapeInfo.setWidth(Math.abs(_pointReleased.getX() - _pointPressed.getX()));
        this.shapeInfo.setHeight(Math.abs(_pointReleased.getY() - _pointPressed.getY()));
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCreateShape");

        IShape shape;

        // Call the ShapeFactory
        if (this.shapeInfo.getShapeType() == ShapeType.ELLIPSE) {
            shape = ShapeFactory.getShapeEllipse(this.shapeInfo);
        } else if (this.shapeInfo.getShapeType() == ShapeType.RECTANGLE) {
            shape = ShapeFactory.getShapeRectangle(this.shapeInfo);
        } else {
            shape = ShapeFactory.getShapeTriangle(this.shapeInfo);
        }

        ICommand cmd = new CmdAddToRepo(shape);
        cmd.execute();
    }
}
