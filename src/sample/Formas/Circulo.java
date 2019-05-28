package sample.Formas;

import java.awt.*;

public class Circulo extends Desenhar {
    private double raio;
    private double xCentral;
    private double yCentral;

    public Circulo(Point pontoInicial, Point pontoFinal){
        setPonto(pontoInicial, pontoFinal);
        setRaio((getPontoFinal().x - getPontoInicial().x)/2);
        setxCentral(getPontoFinal().x - raio);
        setyCentral(getPontoFinal().y - raio);
    }

    public double getxCentral() {
        return xCentral;
    }

    private void setxCentral(double xCentral) {
        this.xCentral = xCentral;
    }

    public double getyCentral() {
        return yCentral;
    }

    private void setyCentral(double yCentral) {
        this.yCentral = yCentral;
    }

    public double getRaio() {
        return raio;
    }

    private void setRaio(double raio) {
        this.raio = raio;
    }
}
