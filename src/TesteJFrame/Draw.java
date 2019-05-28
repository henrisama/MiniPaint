package TesteJFrame;

import java.awt.*;

abstract class Draw {
    private Point pontoInicial = null;
    private Point pontoFinal = null;

    public Point getPontoInicial() {
        return pontoInicial;
    }

    public void setPontoInicial(Point pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public Point getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
}
