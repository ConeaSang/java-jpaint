package application.commands;

import application.Point;
import view.interfaces.PaintCanvasBase;

public class CmdCreateShape implements ICommand, IUndoable {
    // Data
    private PaintCanvasBase paintCanvas;
    private application.Point pointTopLeft;
    private int width;
    private int height;

    // Constructors
    public CmdCreateShape(PaintCanvasBase _paintCanvas, application.Point _pointPressed, application.Point _pointReleased) {
        this.paintCanvas = _paintCanvas;

        this.pointTopLeft = new Point(0,0);

        if (_pointPressed.getX() < _pointReleased.getX()) {
            this.pointTopLeft.setX(_pointPressed.getX());
        } else {
            this.pointTopLeft.setX(_pointReleased.getX());
        }

        if (_pointPressed.getY() < _pointReleased.getY()) {
            this.pointTopLeft.setY(_pointPressed.getY());
        } else {
            this.pointTopLeft.setY(_pointReleased.getY());
        }

        this.width = Math.abs(_pointReleased.getX() - _pointPressed.getX());
        this.height = Math.abs(_pointReleased.getY() - _pointPressed.getY());
    }

    // Methods
    @Override
    public void execute() {
        // Add to the ShapeList here
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
