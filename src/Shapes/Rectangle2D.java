package Shapes;

import java.awt.*;

public class Rectangle2D extends Draw implements SetToDraw {
    private double width;
    private double heigth;

    public Rectangle2D(Point initPoint, Point finalPoint){
        setPoint(initPoint, finalPoint);
        setVariableToDraw();
    }

    public double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    public double getHeigth() {
        return heigth;
    }

    private void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    @Override
    public void setVariableToDraw() {
        setWidth(getFinalPoint().x - getInitPoint().x);
        setHeigth(getFinalPoint().y - getInitPoint().y);
    }
}
