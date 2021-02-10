package application.shapes;

import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ShapeRepository {
    // Data
    private static final ArrayList<IShape> shapeList = new ArrayList<>();

    // Constructors
    private ShapeRepository() {
    }

    // Methods
    public static void add(IShape _shape)
    {
        shapeList.add(_shape);
        System.out.println("shapeList size: " + shapeList.size());
        //ShapeRepository.deleteAll();
        //ShapeRepository.drawAll();
        _shape.draw();
    }

    public static void remove(IShape _shape) {
        ShapeRepository.deleteAll();

        if (shapeList.contains(_shape)) {
            //shapeList.remove(ShapeRepository.find(_shape));
            shapeList.remove(_shape);
        }

        ShapeRepository.drawAll();
    }

    private static int find(IShape _shape)
    {
        int i = 0;

        for (; i < shapeList.size(); i++) {
            if (_shape == shapeList.get(i)) {
                break;
            }
        }

        return i;
    }

    private static void deleteAll()
    {
        //shapeList.get(0).getPaintCanvas().repaint();
        PaintCanvasBase paintCanvas = shapeList.get(0).getPaintCanvas();
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

    }

    private static void drawAll()
    {
        for (IShape s : shapeList) {
            s.draw();
        }
    }
}
