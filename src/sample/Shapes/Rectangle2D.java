package sample.Shapes;

import java.awt.*;

public class Rectangle2D extends Draw {
    private double width;
    private double heigth;

    public Rectangle2D(Point pontoInicial, Point pontoFinal){
        setPonto(pontoInicial, pontoFinal);
        setWidth(getFinalPoint().x - getInitPoint().x);
        setHeigth(getFinalPoint().y - getInitPoint().y);
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
}
