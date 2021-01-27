package application.shapes;

import java.awt.*;

public class Rectangle extends Shape implements IShape{
    @Override
    public void draw() {
        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(this.pointTopLeft.getX(), this.pointTopLeft.getY(), this.width, this.height);
    }
}
