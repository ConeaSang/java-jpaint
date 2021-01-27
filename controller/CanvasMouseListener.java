package controller;

import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CanvasMouseListener implements MouseListener{
    private PaintCanvasBase paintCanvas;
    private Point pointPressed;
    private Point pointReleased;

    public CanvasMouseListener(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        pointPressed.setX(e.getX());
        pointPressed.setY(e.getY());

        System.out.println("Pressed: " + pointPressed.getX() + ", " + pointPressed.getY());
    }

    public void mouseReleased(MouseEvent e) {
        pointReleased.setX(e.getX());
        pointReleased.setY(e.getY());

        System.out.println("Released: " + pointReleased.getX() + ", " + pointReleased.getY());
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
