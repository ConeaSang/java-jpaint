package application.observers;

import application.Point;
import application.shapes.IShape;
import application.shapes.ShapeFactory;
import application.shapes.ShapeInfo;
import model.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class ShapeRepository implements ISubject {
    // Data
    private final List<IShape> mainShapeList = new ArrayList<>();
    private final List<IShape> selectedShapeList = new ArrayList<>();
    private final List<IShape> clipboardShapeList = new ArrayList<>();

    private final List<IObserver> observerList = new ArrayList<>();

    // Constructors
    public ShapeRepository() {
    }

    // Methods
    // public Methods
    @Override
    public void registerObserver(IObserver _observer) {
        this.observerList.add(_observer);
    }

    @Override
    public void removeObserver(IObserver _observer) {
        this.observerList.remove(_observer);
    }

    public void add(IShape _shape) {
        this.mainShapeList.add(_shape);

        this.reDrawAllShapes();

        System.out.print("add(_shape) - ");
        this.printSizeOfAllList();
    }

    public void add(List<IShape> _shapeList) {
        this.mainShapeList.addAll(_shapeList);

        this.reDrawAllShapes();

        System.out.print("add(_list) - ");
        this.printSizeOfAllList();
    }

    public void remove(IShape _shape) {
        this.mainShapeList.remove(_shape);

        this.selectedShapeList.remove(_shape);

        this.reDrawAllShapes();

        System.out.print("remove(_shape) - ");
        this.printSizeOfAllList();
    }

    public void remove(List<IShape> _shapeList) {
        for (IShape s : _shapeList) {

            this.mainShapeList.remove(s);

            this.selectedShapeList.remove(s);
        }

        this.reDrawAllShapes();

        System.out.print("remove(_list) - ");
        this.printSizeOfAllList();
    }

    public void setMainShapeList(List<IShape> _shapeList) {
        this.mainShapeList.clear();

//        for (IShape s : _shapeList) {
//            this.mainShapeList.add(s);
//        }

        this.mainShapeList.addAll(_shapeList);
    }

    public List<IShape> getMainShapeList() {
        return this.mainShapeList;
    }

    public void setSelectedShapeList(List<IShape> _shapeList) {
        this.selectedShapeList.clear();

//        for (IShape s : _shapeList) {
//            this.selectedShapeList.add(s);
//        }

        this.selectedShapeList.addAll(_shapeList);
    }

    public List<IShape> getSelectedShapeList() {
        return this.selectedShapeList;
    }

    public void setClipboardShapeList(List<IShape> _shapeList) {
        this.clipboardShapeList.clear();

//        for (IShape s : _shapeList) {
//            this.clipboardShapeList.add(s);
//        }

        this.clipboardShapeList.addAll(_shapeList);
    }

    public List<IShape> getClipboardShapeList() {
        return this.clipboardShapeList;
    }

    public void moveSelectedShapes(int _deltaX, int _deltaY) {
        // This loop will also update the mainShapeList
        for (IShape s : this.selectedShapeList) {
            s.translateAllPoint(_deltaX, _deltaY);
        }

        reDrawAllShapes();

        System.out.println("updateForMove() - mainShapeList size: " + this.mainShapeList.size());
        System.out.println("updateForMove() - selectedShapeList size: " + this.selectedShapeList.size());
    }

    public void updateSelectedShapeListForCollision(Point _topLeftCollision, Point _bottomRightCollision) {
        this.selectedShapeList.clear();

        for (IShape s : this.mainShapeList) {
            if (this.checkCollision(s, _topLeftCollision, _bottomRightCollision)) {
                System.out.println("Collided");
                this.selectedShapeList.add(s);
            } else {
                System.out.println("Not collided");
            }
        }

        System.out.println("updateForCollision() - selectedShapeList size: " + this.selectedShapeList.size());
    }

    public void reDrawAllShapes() {
        this.notifyObservers();
    }

    public void printSizeOfAllList() {
        System.out.println("size: (" + this.mainShapeList.size() + ", " + this.selectedShapeList.size() + ", " + this.clipboardShapeList.size() + ")");
    }

    // private Methods
    private void notifyObservers() {
        for (IObserver observer : this.observerList) {
            observer.update();
        }
    }

    private boolean checkCollision(IShape shape, Point _topLeftCollision, Point _bottomRightCollision) {
        return shape.getTopLeftPoint().getX() < _bottomRightCollision.getX()
                && shape.getBottomRightPoint().getX() > _topLeftCollision.getX()
                && shape.getTopLeftPoint().getY() < _bottomRightCollision.getY()
                && shape.getBottomRightPoint().getY() > _topLeftCollision.getY();
    }
}
