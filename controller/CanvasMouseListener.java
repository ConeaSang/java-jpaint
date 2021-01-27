package controller;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import view.interfaces.PaintCanvasBase;

public class CanvasMouseListener implements MouseListener{
    private PaintCanvasBase paintCanvas;
    private Point pointPressed;
    private Point pointReleased;

    public CanvasMouseListener(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;
        pointPressed = new Point(0,0);
        pointReleased = new Point(0,0);
    }

    public void mouseClicked(MouseEvent e) {
        // Nothing
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

        Point pointTopLeft = new Point(0,0);

        if (pointPressed.getX() < pointReleased.getX()) {
            pointTopLeft.setX(pointPressed.getX());
        } else {
            pointTopLeft.setX(pointReleased.getX());
        }

        if (pointPressed.getY() < pointReleased.getY()) {
            pointTopLeft.setY(pointPressed.getY());
        } else {
            pointTopLeft.setY(pointReleased.getY());
        }

        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();
        int width = Math.abs(pointReleased.getX() - pointPressed.getX());
        int height = Math.abs(pointReleased.getY() - pointPressed.getY());
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(pointTopLeft.getX(), pointTopLeft.getY(), width, height);

        pointPressed.setX(0);
        pointPressed.setY(0);
        pointReleased.setX(0);
        pointReleased.setY(0);
    }

    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    public void mouseExited(MouseEvent e) {
        // Nothing
    }
}
