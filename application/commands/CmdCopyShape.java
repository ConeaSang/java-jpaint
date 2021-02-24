package application.commands;

import application.observers.ShapeRepository;
import application.shapes.IShape;

import java.util.List;

public class CmdCopyShape implements ICommand {
    // Data
    private ShapeRepository shapeRepo;

    // Constructors
    public CmdCopyShape(ShapeRepository _shapeRepo) {
        this.shapeRepo = _shapeRepo;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdCopyShape");

        this.shapeRepo.updateClipboardShapeListForCopy();
    }
}
