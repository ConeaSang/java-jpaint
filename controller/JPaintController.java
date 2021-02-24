package controller;

import application.commands.*;
import application.observers.ShapeRepository;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeRepository shapeRepo;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    @Override
    public void setShapeRepo(ShapeRepository _shapeRepo) {
        this.shapeRepo = _shapeRepo;
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());

//        IEventCallback callback = new IEventCallback() {
//            @Override
//            public void run() {
//                ICommand cmd = new UndoCmd();
//                cmd.execute();
//            }
//        };
//        uiModule.addEvent(EventName.UNDO, callback);

        uiModule.addEvent(EventName.UNDO, () -> new CmdUndo().execute());
        uiModule.addEvent(EventName.REDO, () -> new CmdRedo().execute());

        uiModule.addEvent(EventName.COPY, () -> new CmdCopyShape(this.shapeRepo).execute());
        uiModule.addEvent(EventName.PASTE, () -> new CmdPasteShape(this.shapeRepo).execute());
        uiModule.addEvent(EventName.DELETE, () -> new CmdDeleteShape(this.shapeRepo).execute());

        //--------------------------------------------------
        // Add event here

        //uiModule.addEvent(EventName.DELETE, () -> new UndoCommand().run());
        //uiModule.addEvent(EventName.COPY, () -> new UndoCommand().run());
        //uiModule.addEvent(EventName.PASTE, () -> new UndoCommand().run());
        //uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        //uiModule.addEvent(EventName.REDO, () -> new UndoCommand().run());
        //uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
    }
}
