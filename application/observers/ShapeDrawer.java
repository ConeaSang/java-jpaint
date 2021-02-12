package application.observers;

import application.shapes.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.Graphics2D;
import java.awt.Color;

public class ShapeDrawer implements IObserver {
    // Data
    private PaintCanvasBase paintCanvas;

    // Constructors
    public ShapeDrawer(PaintCanvasBase _paintCanvas) {
        this.paintCanvas = _paintCanvas;
    }

    // Methods
    @Override
    public void update() {
        this.drawAll();
    }

    private void drawAll() {
        //shapeList.get(0).getPaintCanvas().repaint();
        Graphics2D g2D = this.paintCanvas.getGraphics2D();
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, this.paintCanvas.getWidth(), this.paintCanvas.getHeight());

        for (IShape s : ShapeRepository.getMainShapeList()) {
            s.draw(this.paintCanvas.getGraphics2D());
        }
    }
}
