package application.commands;

public class CmdUndo implements ICommand {
    @Override
    public void execute() {
        CommandHistory.undo();
    }
}
