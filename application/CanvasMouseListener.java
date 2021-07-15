package application;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import application.commands.CmdCreateShape;
import application.commands.CmdMoveShape;
import application.commands.CmdSelectShape;
import application.commands.ICommand;
import application.observers.ShapeRepository;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class CanvasMouseListener implements MouseListener {
    // Data
    private final PaintCanvasBase m_paintCanvas;
    private final IApplicationState m_appState;
    private final ShapeRepository m_shapeRepo;
    private final Point m_pointPressed;
    private final Point m_pointReleased;

    // Constructors
    public CanvasMouseListener(PaintCanvasBase paintCanvas, IApplicationState appState, ShapeRepository shapeRepo) {
        this.m_paintCanvas = paintCanvas;
        this.m_appState = appState;
        this.m_shapeRepo = shapeRepo;
        this.m_pointPressed = new Point(0,0);
        this.m_pointReleased = new Point(0,0);
    }

    // Methods
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("--------------------------------------------------");
        System.out.println("mousePressed: " + e.getX() + ", " + e.getY());

        this.m_pointPressed.setX(e.getX());
        this.m_pointPressed.setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased: " + e.getX() + ", " + e.getY());

        this.m_pointReleased.setX(e.getX());
        this.m_pointReleased.setY(e.getY());

        ICommand cmd;

        // MouseMode
        switch (this.m_appState.getActiveMouseMode()) {
            case DRAW:
                // MouseMode.DRAW
                if ((this.m_pointPressed.getX() != this.m_pointReleased.getX()) || (this.m_pointPressed.getY() != this.m_pointReleased.getY()))
                {
                    cmd = new CmdCreateShape(this.m_appState, this.m_shapeRepo, this.m_pointPressed, this.m_pointReleased);
                    cmd.execute();
                }
                break;

            case SELECT:
                // MouseMode.SELECT
                cmd = new CmdSelectShape(this.m_appState, this.m_shapeRepo, this.m_pointPressed, this.m_pointReleased);
                cmd.execute();
                break;

            case MOVE:
                // MouseMode.MOVE
                cmd = new CmdMoveShape(this.m_appState, this.m_shapeRepo, this.m_pointPressed, this.m_pointReleased);
                cmd.execute();
                break;
        }

        // Reset
        this.m_pointPressed.setX(0);
        this.m_pointPressed.setY(0);
        this.m_pointReleased.setX(0);
        this.m_pointReleased.setY(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Nothing
    }
}
