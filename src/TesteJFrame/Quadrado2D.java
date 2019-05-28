package TesteJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Quadrado2D extends JFrame{
    private boolean pressionado = false;
    int x = -1;
    int y = -1;
    Point ponto = null;
    /*biblioteca com as funçoes que tem as açoes do mouse*/
    private Quadrado2D(){

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                pressionado = true;
            }

            public void mouseReleased(MouseEvent e) {
                pressionado = false;
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {
            }
        });


        new Time().start();

        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /* paint trata todas os casos onde o xInicial eh maior que o xFinal e vice-versa, e o mesmo vale pro yInicial e yFinal*/

    public void paint(Graphics g){
        //g.drawRect(325,288,128,78);
        if(x != -1 && y != -1) {
            if (ponto.x < x && ponto.y < y) {
                g.drawRect(ponto.x, ponto.y, x - ponto.x, y - ponto.y);
            } else if (ponto.x > x && ponto.y < y) {
                g.drawRect(x, ponto.y, ponto.x - x, y - ponto.y);
            } else if (x > ponto.x && y < ponto.y) {
                g.drawRect(ponto.x, y, x - ponto.x, ponto.y - y);
            } else {
                g.drawRect(x, y, ponto.x - x, ponto.y - y);
            }
        }
        x = y = -1;
    }

    public static void main(String[] args) {
        new Quadrado2D();
    }

    public class Time extends Thread {
        public void run() {
            while(true){
                if(pressionado){
                    try {
                        ponto = getMousePosition();
                        if(x == -1 && y == -1){
                            x = ponto.x;
                            y = ponto.y;
                        }
                        System.out.println(ponto);
                        System.out.println(x +" "+y);
                    }catch(Exception erro){}
                }else {
                    repaint();
                }
            }
        }
    }
}
