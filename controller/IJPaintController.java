package controller;

import application.observers.ShapeRepository;

public interface IJPaintController {
    void setup();

    void setShapeRepo(ShapeRepository shapeRepo);
}
