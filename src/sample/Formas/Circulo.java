package sample.Formas;

import java.awt.*;

public class Circulo extends Desenhar {
    private double raio;
    private double xCentral;
    private double yCentral;

    public Circulo(Point pontoInicial, Point pontoFinal){
        setPonto(pontoInicial, pontoFinal);
        System.out.println(getPontoInicial());
        System.out.println(getPontoFinal());
        setRaio((pontoFinal.x - pontoInicial.x)/2);
        setxCentral(pontoFinal.x - raio);
        setyCentral(pontoFinal.y - raio);
    }

    double getxCentral() {
        return xCentral;
    }

    private void setxCentral(double xCentral) {
        this.xCentral = xCentral;
    }

    double getyCentral() {
        return yCentral;
    }

    private void setyCentral(double yCentral) {
        this.yCentral = yCentral;
    }

    double getRaio() {
        return raio;
    }

    private void setRaio(double raio) {
        this.raio = raio;
    }
}
