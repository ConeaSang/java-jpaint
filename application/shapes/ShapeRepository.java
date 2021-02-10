package application.shapes;

import application.Point;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeRepository {
    // Data
    private static final ArrayList<IShape> mainShapeList = new ArrayList<>();
    private static final ArrayList<IShape> selectedShapeList = new ArrayList<>();
    private static PaintCanvasBase paintCanvas;

    // Constructors
    public ShapeRepository(PaintCanvasBase _paintCanvas) {
        ShapeRepository.paintCanvas = _paintCanvas;
    }

    // Methods
    // public Methods
    public static void add(IShape _shape)
    {
        ShapeRepository.mainShapeList.add(_shape);

        System.out.println("add() - shapeList size: " + ShapeRepository.mainShapeList.size());

        //ShapeRepository.deleteAll();
        //ShapeRepository.drawAll();
        _shape.draw(ShapeRepository.paintCanvas.getGraphics2D());
        //_shape.getPaintCanvas().repaint();
    }

    public static void remove(IShape _shape) {
        if (ShapeRepository.mainShapeList.contains(_shape)) {
            //shapeList.remove(ShapeRepository.find(_shape));
            ShapeRepository.mainShapeList.remove(_shape);
        }

        System.out.println("remove() - shapeList size: " + ShapeRepository.mainShapeList.size());

        ShapeRepository.deleteAll();

        ShapeRepository.drawAll();
    }

    public static void updateSelectedShapeList(Point _topLeftCollision, Point _bottomRightCollision) {
        ShapeRepository.clearSelectedShapeList();

        for (IShape s :ShapeRepository.mainShapeList) {
            if (ShapeRepository.checkCollision(s, _topLeftCollision, _bottomRightCollision)) {
                ShapeRepository.selectedShapeList.add(s);
            } 
        }
    }

    // private Methods
    private static void deleteAll()
    {
        //shapeList.get(0).getPaintCanvas().repaint();
        //PaintCanvasBase paintCanvas = shapeList.get(0).getPaintCanvas();
        Graphics2D graphics2D = ShapeRepository.paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, ShapeRepository.paintCanvas.getWidth(), ShapeRepository.paintCanvas.getHeight());
    }

    private static void drawAll()
    {
        for (IShape s : ShapeRepository.mainShapeList) {
            s.draw(ShapeRepository.paintCanvas.getGraphics2D());
        }
    }

    private static void clearSelectedShapeList() {
        ShapeRepository.selectedShapeList.clear();
    }

    private static boolean checkCollision(IShape shape, Point _topLeftCollision, Point _bottomRightCollision) {
        return shape.getTopLeftPoint().getX() < _bottomRightCollision.getX()
                && shape.getBottomRightPoint().getX() > _topLeftCollision.getX()
                && shape.getTopLeftPoint().getY() < _bottomRightCollision.getY()
                && shape.getBottomRightPoint().getY() > _topLeftCollision.getY();
    }
}
