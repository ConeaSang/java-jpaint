package controller;

import application.commands.*;
import application.observers.ShapeRepository;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule m_uiModule;
    private final IApplicationState m_applicationState;
    private ShapeRepository m_shapeRepo;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.m_uiModule = uiModule;
        this.m_applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    @Override
    public void setShapeRepo(ShapeRepository shapeRepo) {
        this.m_shapeRepo = shapeRepo;
    }

    private void setupEvents() {
        m_uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> m_applicationState.setActiveShape());
        m_uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> m_applicationState.setActivePrimaryColor());
        m_uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> m_applicationState.setActiveSecondaryColor());
        m_uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> m_applicationState.setActiveShadingType());
        m_uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> m_applicationState.setActiveStartAndEndPointMode());

        IEventCallback callback = new IEventCallback() {
            @Override
            public void run() {
                ICommand cmd = new CmdUndo();
                cmd.execute();
            }
        };
        m_uiModule.addEvent(EventName.UNDO, callback);

        //m_uiModule.addEvent(EventName.UNDO, () -> new CmdUndo().execute());
        m_uiModule.addEvent(EventName.REDO, () -> new CmdRedo().execute());

        m_uiModule.addEvent(EventName.COPY, () -> new CmdCopyShape(this.m_shapeRepo).execute());
        m_uiModule.addEvent(EventName.PASTE, () -> new CmdPasteShape(this.m_shapeRepo).execute());
        m_uiModule.addEvent(EventName.DELETE, () -> new CmdDeleteShape(this.m_shapeRepo).execute());
    }
}
