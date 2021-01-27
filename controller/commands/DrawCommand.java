package controller.commands;

import java.awt.*;
import java.io.IOException;
import view.interfaces.PaintCanvasBase;
import controller.Point;

public class DrawCommand implements ICommand, IUndoable {
    // Data
    private PaintCanvasBase paintCanvas;
    private Point pointTopLeft;
    private int width;
    private int height;

    // Constructors
    public DrawCommand(PaintCanvasBase _paintCanvas, Point _pointPressed, Point _pointReleased) {
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
        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();

        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(this.pointTopLeft.getX(), this.pointTopLeft.getY(), this.width, this.height);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
