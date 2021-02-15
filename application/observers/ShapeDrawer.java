package application.observers;

import application.shapes.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.Graphics2D;
import java.awt.Color;

public class ShapeDrawer implements IObserver {
    // Data
    private PaintCanvasBase paintCanvas;
    private ShapeRepository shapeRepo;

    // Constructors
    public ShapeDrawer(PaintCanvasBase _paintCanvas, ShapeRepository _shapeRepo) {
        this.paintCanvas = _paintCanvas;
        this.shapeRepo = _shapeRepo;
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

        for (IShape s : this.shapeRepo.getMainShapeList()) {
            s.draw(this.paintCanvas.getGraphics2D());
        }
    }
}
