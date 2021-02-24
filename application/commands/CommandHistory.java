package application.commands;

import java.util.Stack;

public class CommandHistory {
    private static final Stack<IUndoable> m_undoStack = new Stack<>();
    private static final Stack<IUndoable> m_redoStack = new Stack<>();

    public static void add(IUndoable cmd) {
        m_undoStack.push(cmd);
        m_redoStack.clear();
    }

    public static boolean undo() {
        boolean result = !m_undoStack.empty();
        if (result) {
            IUndoable c = m_undoStack.pop();
            m_redoStack.push(c);
            c.undo();
        }
        return result;
    }

    public static boolean redo() {
        boolean result = !m_redoStack.empty();
        if (result) {
            IUndoable c = m_redoStack.pop();
            m_undoStack.push(c);
            c.redo();
        }
        return result;
    }
}
