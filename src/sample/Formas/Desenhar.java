package sample.Formas;

import java.awt.*;

abstract class Desenhar {
    private Point pontoInicial = null;
    private Point pontoFinal = null;

    public Point getPontoInicial() {
        return pontoInicial;
    }

    private void setPontoInicial(Point pontoInicial) {
        this.pontoInicial = pontoInicial;
    }


    public Point getPontoFinal() {
        return pontoFinal;
    }

    private void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }


    void setPonto(Point pontoInicial, Point pontoFinal){
        if(pontoInicial.x > pontoFinal.x && pontoInicial.y > pontoFinal.y){
            setPontoInicial(pontoFinal.getLocation());
            setPontoFinal(pontoInicial.getLocation());
        }
        else if(pontoInicial.x < pontoFinal.x && pontoInicial.y < pontoFinal.y){
            setPontoInicial(pontoInicial.getLocation());
            setPontoFinal(pontoFinal.getLocation());
        }
        else{
            Point ponto1 = new Point((int)pontoInicial.getX(), (int) pontoFinal.getY());
            Point ponto2 = new Point((int)pontoFinal.getX(), (int) pontoInicial.getY());
            if(pontoInicial.x < pontoFinal.x && pontoInicial.y > pontoFinal.y){
                setPontoInicial(ponto1.getLocation());
                setPontoFinal(ponto2.getLocation());
            }
            else if(pontoInicial.x > pontoFinal.x && pontoInicial.y < pontoFinal.y){
                setPontoInicial(ponto2.getLocation());
                setPontoFinal(ponto1.getLocation());
            }
        }
    }
}
