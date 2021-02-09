package application.commands;

import application.shapes.IShape;
import application.shapes.ShapeRepository;

public class CmdAddToRepo implements ICommand, IUndoable {
    // Data
    private IShape shape;

    // Constructors
    public CmdAddToRepo(IShape _shape) {
        this.shape = _shape;
    }

    // Methods
    @Override
    public void execute() {
        System.out.println("---> execute() CmdAddToRepo");

        ShapeRepository.add(this.shape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        ShapeRepository.remove(this.shape);
    }

    @Override
    public void redo() {
        ShapeRepository.add(this.shape);
    }
}
