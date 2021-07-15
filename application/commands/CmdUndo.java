package application.commands;

public class CmdUndo implements ICommand {
    @Override
    public void execute() {
        System.out.println("---> execute() CmdUndo");

        CommandHistory.undo();
    }
}
