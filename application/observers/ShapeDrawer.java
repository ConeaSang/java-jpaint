package application.observers;

import application.shapes.IShape;
import application.shapes.ShapeOutlineProxy;
import view.interfaces.PaintCanvasBase;

import java.awt.Graphics2D;
import java.awt.Color;

public class ShapeDrawer implements IObserver {
    // Data
    private final PaintCanvasBase m_paintCanvas;
    private final ShapeRepository m_shapeRepo;

    // Constructors
    public ShapeDrawer(PaintCanvasBase paintCanvas, ShapeRepository shapeRepo) {
        this.m_paintCanvas = paintCanvas;
        this.m_shapeRepo = shapeRepo;
    }

    // Methods
    @Override
    public void update() {
        this.drawAll();
    }

    private void drawAll() {
        //shapeList.get(0).getPaintCanvas().repaint();
        Graphics2D g2D = this.m_paintCanvas.getGraphics2D();
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, this.m_paintCanvas.getWidth(), this.m_paintCanvas.getHeight());

        for (IShape s : this.m_shapeRepo.getMainShapeList()) {
            s.draw(this.m_paintCanvas.getGraphics2D());
        }

        // Draw shape outlines
        for (IShape t : this.m_shapeRepo.getSelectedShapeList()) {
            IShape shapeProxy = new ShapeOutlineProxy(t);
            shapeProxy.draw(this.m_paintCanvas.getGraphics2D());
        }
    }
}
