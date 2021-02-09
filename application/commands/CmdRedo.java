package application.commands;

public class CmdRedo implements ICommand {
    @Override
    public void execute() {
        System.out.println("---> execute() CmdRedo");

        CommandHistory.redo();
    }
}
