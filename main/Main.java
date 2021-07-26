package main;

import application.CanvasMouseListener;
import application.observers.ShapeDrawer;
import application.observers.ShapeRepository;
import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
	public static void main(String[] args) {
		// Setup
		PaintCanvasBase paintCanvas = new PaintCanvas();
		IGuiWindow guiWindow = new GuiWindow(paintCanvas);
		IUiModule uiModule = new Gui(guiWindow);
		ApplicationState appState = new ApplicationState(uiModule);
		IJPaintController controller = new JPaintController(uiModule, appState);
		controller.setup();

		// Setup Observers
		ShapeRepository shapeRepo = new ShapeRepository();
		ShapeDrawer shapeDrawerObserver = new ShapeDrawer(paintCanvas, shapeRepo);
		shapeRepo.registerObserver(shapeDrawerObserver);

		// Set ShapeRepo for JPaintController instance
		controller.setShapeRepo(shapeRepo);

		// Setup Mouse Listeners
		CanvasMouseListener mouseListener = new CanvasMouseListener(paintCanvas, appState, shapeRepo);
		paintCanvas.addMouseListener(mouseListener);
		
		//--------------------------------------------------
		// Testing GitHub
		// - After changed GitHub username

		//--------------------------------------------------
		// Testing

		//paintCanvas.addMouseListener(new );
		
		//ShapeType shapeType = appState.getActiveShapeType();

		//if (shapeType == ShapeType.RECTANGLE) {
		//}

//		//EnumMap<ShapeColor, java.awt.Color> mapColor = new EnumMap<ShapeColor, Color>(ShapeColor.class);
//		EnumMap<ShapeColor, java.awt.Color> mapColor = new EnumMap<>(ShapeColor.class);
//		mapColor.put(ShapeColor.BLUE, Color.BLUE);

		//--------------------------------------------------
		// For example purposes only; remove all lines below from your final project.

		//try {
		//	Thread.sleep(500);
		//	//Thread.sleep(1500);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}

		// Filled in rectangle
		//Graphics2D graphics2d = paintCanvas.getGraphics2D();
		//graphics2d.setColor(Color.GREEN);
		//graphics2d.fillRect(12, 13, 200, 400);
		//graphics2d.fillRect(42, 43, 200, 600);

		// Outlined rectangle
		//graphics2d.setStroke(new BasicStroke(5));
		//graphics2d.setStroke(new BasicStroke(8));
		//graphics2d.setColor(Color.BLUE);
		//graphics2d.drawRect(12, 13, 200, 400);

		// Selected Shape
		//Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[] { 9 }, 0);
		//Stroke stroke = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 4, new float[] { 15 }, 0);
		//graphics2d.setStroke(stroke);
		//graphics2d.setColor(Color.BLACK);
		//graphics2d.drawRect(7, 8, 210, 410);

		//--------------------------------------------------

	}
}
