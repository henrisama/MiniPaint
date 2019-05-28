package Teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Reta2D extends JFrame{

    boolean mouseClick = false;
    int x1, y1;
    Point ponto = null;


    public Reta2D (){


        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                mouseClick = true;
            }

            public void mouseReleased(MouseEvent e) {
                mouseClick = false;
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        new mapaDesenho().start();

        setSize(1200,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g) {
        if(x1 != -1 && y1 != -1){
            g.drawLine(x1, y1, ponto.x, ponto.y);
        }

        x1 = y1 = -1;
    }
    /*public static void main(String[] args) {
        new Reta2D();
    }*/

    public class mapaDesenho extends Thread {
        public void run() {
            x1 = y1 = -1;
            while(true){
                if(mouseClick){
                    try {
                        ponto = getMousePosition();
                        if(x1 == -1 && y1 == -1){
                            x1 = ponto.x;
                            y1 = ponto.y;
                        }

                    }catch(Exception erro){}
                }else{ repaint();}
            }
        }
    }

}
