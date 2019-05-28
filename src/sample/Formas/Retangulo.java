package sample.Formas;

import java.awt.*;

public class Retangulo extends Desenhar{
    private double width;
    private double heigth;

    public Retangulo(Point pontoInicial, Point pontoFinal){
        setPonto(pontoInicial, pontoFinal);
        setWidth(getPontoFinal().x - getPontoInicial().x);
        setHeigth(getPontoFinal().y - getPontoInicial().y);
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
