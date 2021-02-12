package application.commands;

import application.shapes.IShape;
import application.observers.ShapeRepository;

public class CmdAddToRepo implements ICommand, IUndoable {
    // Data
    private ShapeRepository shapeRepo;
    private IShape shape;

    // Constructors
    public CmdAddToRepo(ShapeRepository _shapeRepo, IShape _shape) {
        this.shapeRepo = _shapeRepo;
        this.shape = _shape;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdAddToRepo");

        this.shapeRepo.add(this.shape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        this.shapeRepo.remove(this.shape);
    }

    @Override
    public void redo() {
        this.shapeRepo.add(this.shape);
    }
}
