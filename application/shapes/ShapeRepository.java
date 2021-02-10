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

        //ShapeRepository.deleteAll();
        //ShapeRepository.drawAll();
        _shape.draw(ShapeRepository.paintCanvas.getGraphics2D());
        //_shape.getPaintCanvas().repaint();

        System.out.println("add() - mainShapeList size: " + ShapeRepository.mainShapeList.size());
    }

    public static void remove(IShape _shape) {
        if (ShapeRepository.mainShapeList.contains(_shape)) {
            //shapeList.remove(ShapeRepository.find(_shape));
            ShapeRepository.mainShapeList.remove(_shape);
        }

        ShapeRepository.deleteAll();
        ShapeRepository.drawAll();

        System.out.println("remove() - mainShapeList size: " + ShapeRepository.mainShapeList.size());
    }

    public static void setMainShapeList(ArrayList<IShape> _shapeList) {
        ShapeRepository.mainShapeList.clear();

        for (IShape s : _shapeList) {
            ShapeRepository.mainShapeList.add(s);
        }
    }

    public static ArrayList<IShape> getMainShapeList() {
        return ShapeRepository.mainShapeList;
    }

    public static void updateMainShapeListForMove(int _deltaX, int _deltaY) {
        // This loop will also update the mainShapeList
        for (IShape s : ShapeRepository.selectedShapeList) {
            s.translateAllPoint(_deltaX, _deltaY);
        }

        ShapeRepository.deleteAll();
        ShapeRepository.drawAll();

        System.out.println("updateForMove() - mainShapeList size: " + ShapeRepository.mainShapeList.size());
        System.out.println("updateForMove() - selectedShapeList size: " + ShapeRepository.selectedShapeList.size());
    }

    public static void setSelectedShapeList(ArrayList<IShape> _shapeList) {
        ShapeRepository.selectedShapeList.clear();

        for (IShape s : _shapeList) {
            ShapeRepository.selectedShapeList.add(s);
        }
    }

    public static ArrayList<IShape> getSelectedShapeList() {
        return ShapeRepository.selectedShapeList;
    }

    public static void updateSelectedShapeListForCollision(Point _topLeftCollision, Point _bottomRightCollision) {
        ShapeRepository.clearSelectedShapeList();

        for (IShape s : ShapeRepository.mainShapeList) {
            if (ShapeRepository.checkCollision(s, _topLeftCollision, _bottomRightCollision)) {
                System.out.println("Collided");
                ShapeRepository.selectedShapeList.add(s);
            } else {
                System.out.println("Not collided");
            }
        }

        System.out.println("updateForCollision() - selectedShapeList size: " + ShapeRepository.selectedShapeList.size());
    }

    public static void deleteAll()
    {
        //shapeList.get(0).getPaintCanvas().repaint();
        //PaintCanvasBase paintCanvas = shapeList.get(0).getPaintCanvas();
        Graphics2D g2D = ShapeRepository.paintCanvas.getGraphics2D();
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, ShapeRepository.paintCanvas.getWidth(), ShapeRepository.paintCanvas.getHeight());
    }

    public static void drawAll()
    {
        for (IShape s : ShapeRepository.mainShapeList) {
            s.draw(ShapeRepository.paintCanvas.getGraphics2D());
        }
    }

    // private Methods
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
