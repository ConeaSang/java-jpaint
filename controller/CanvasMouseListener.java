package controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import controller.commands.DrawCommand;
import controller.commands.ICommand;
import view.interfaces.PaintCanvasBase;

public class CanvasMouseListener implements MouseListener{
    // Data
    private PaintCanvasBase paintCanvas;
    private Point pointPressed;
    private Point pointReleased;

    // Constructors
    public CanvasMouseListener(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;
        this.pointPressed = new Point(0,0);
        this.pointReleased = new Point(0,0);
    }

    // Methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pointPressed.setX(e.getX());
        this.pointPressed.setY(e.getY());
        System.out.println("Pressed: " + this.pointPressed.getX() + ", " + this.pointPressed.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.pointReleased.setX(e.getX());
        this.pointReleased.setY(e.getY());
        System.out.println("Released: " + this.pointReleased.getX() + ", " + this.pointReleased.getY());

        ICommand cmd = new DrawCommand(this.paintCanvas, this.pointPressed, this.pointReleased);
        cmd.execute();

        this.pointPressed.setX(0);
        this.pointPressed.setY(0);
        this.pointReleased.setX(0);
        this.pointReleased.setY(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Nothing
    }
}
