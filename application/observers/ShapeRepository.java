package application.observers;

import application.Point;
import application.shapes.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeRepository implements ISubject {
    // Data
    private final List<IShape> m_mainShapeList = new ArrayList<>();
    private final List<IShape> m_selectedShapeList = new ArrayList<>();
    private final List<IShape> m_clipboardShapeList = new ArrayList<>();

    private final List<IObserver> m_observerList = new ArrayList<>();

    // Constructors
    public ShapeRepository() {
    }

    // Methods
    // public Methods
    @Override
    public void registerObserver(IObserver observer) {
        this.m_observerList.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.m_observerList.remove(observer);
    }

    public void add(IShape shape) {
        this.m_mainShapeList.add(shape);

        this.reDrawAllShapes();

        System.out.print("add(shape) - ");
        this.printSizeOfAllList();
    }

    public void add(List<IShape> shapeList) {
        this.m_mainShapeList.addAll(shapeList);

        this.reDrawAllShapes();

        System.out.print("add(list) - ");
        this.printSizeOfAllList();
    }

    public void remove(IShape shape) {
        this.m_mainShapeList.remove(shape);

        this.m_selectedShapeList.remove(shape);

        this.reDrawAllShapes();

        System.out.print("remove(shape) - ");
        this.printSizeOfAllList();
    }

    public void remove(List<IShape> shapeList) {
        for (IShape s : shapeList) {

            this.m_mainShapeList.remove(s);

            this.m_selectedShapeList.remove(s);
        }

        this.reDrawAllShapes();

        System.out.print("remove(list) - ");
        this.printSizeOfAllList();
    }

    public void setMainShapeList(List<IShape> shapeList) {
        this.m_mainShapeList.clear();

        this.m_mainShapeList.addAll(shapeList);
    }

    public List<IShape> getMainShapeList() {
        return this.m_mainShapeList;
    }

    public void setSelectedShapeList(List<IShape> shapeList) {
        this.m_selectedShapeList.clear();

        this.m_selectedShapeList.addAll(shapeList);
    }

    public List<IShape> getSelectedShapeList() {
        return this.m_selectedShapeList;
    }

    public void setClipboardShapeList(List<IShape> shapeList) {
        this.m_clipboardShapeList.clear();

        this.m_clipboardShapeList.addAll(shapeList);
    }

    public List<IShape> getClipboardShapeList() {
        return this.m_clipboardShapeList;
    }

    public void moveSelectedShapes(int deltaX, int deltaY) {
        // This loop will also update the mainShapeList
        for (IShape s : this.m_selectedShapeList) {
            s.translateAllPoint(deltaX, deltaY);
        }

        reDrawAllShapes();

        System.out.println("updateForMove() - mainShapeList size: " + this.m_mainShapeList.size());
        System.out.println("updateForMove() - selectedShapeList size: " + this.m_selectedShapeList.size());
    }

    public void updateSelectedShapeListForCollision(Point topLeftCollision, Point bottomRightCollision) {
        this.m_selectedShapeList.clear();

        for (IShape s : this.m_mainShapeList) {
            if (this.checkCollision(s, topLeftCollision, bottomRightCollision)) {
                System.out.println("Collided");
                this.m_selectedShapeList.add(s);
            } else {
                System.out.println("Not collided");
            }
        }

        reDrawAllShapes();

        System.out.println("updateForCollision() - selectedShapeList size: " + this.m_selectedShapeList.size());
    }

    public void reDrawAllShapes() {
        this.notifyObservers();
    }

    public void printSizeOfAllList() {
        System.out.println("size: (" + this.m_mainShapeList.size() + ", " + this.m_selectedShapeList.size() + ", " + this.m_clipboardShapeList.size() + ")");
    }

    // private Methods
    private void notifyObservers() {
        for (IObserver observer : this.m_observerList) {
            observer.update();
        }
    }

    private boolean checkCollision(IShape shape, Point topLeftCollision, Point bottomRightCollision) {
        return shape.getTopLeftPoint().getX() < bottomRightCollision.getX()
                && shape.getBottomRightPoint().getX() > topLeftCollision.getX()
                && shape.getTopLeftPoint().getY() < bottomRightCollision.getY()
                && shape.getBottomRightPoint().getY() > topLeftCollision.getY();
    }
}
