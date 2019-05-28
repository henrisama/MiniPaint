package sample.Formas;

import java.awt.*;

abstract class Desenhar {
    private Point pontoInicial;
    private Point pontoFinal;

    Point getPontoInicial() {
        return pontoInicial;
    }

    private void setPontoInicial(Point pontoInicial) {
        this.pontoInicial = pontoInicial;
    }


    Point getPontoFinal() {
        return pontoFinal;
    }

    private void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }


    void setPonto(Point pontoInicial, Point pontoFinal){
        if(pontoInicial.x > pontoFinal.x && pontoInicial.y > pontoFinal.y){
            System.out.println(pontoInicial);
            System.out.println(pontoFinal);
            setPontoInicial(pontoFinal.getLocation());
            setPontoFinal(pontoInicial.getLocation());
        }
        else if(pontoInicial.x < pontoFinal.x && pontoInicial.y < pontoFinal.y){
            setPontoInicial(pontoInicial.getLocation());
            setPontoFinal(pontoFinal.getLocation());
        }
    }
}
