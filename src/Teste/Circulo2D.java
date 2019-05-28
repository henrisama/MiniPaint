package Teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Circulo2D extends JFrame {
    boolean mouseClick = false;
    int x1, y1;
    Point ponto = null;


    public Circulo2D(){


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
            if(x1 < ponto.x && y1 < ponto.y){
                g.drawOval(x1, y1, ponto.x - x1, ponto.y - y1);
            }
            else if(x1 > ponto.x && y1 > ponto.y){
                g.drawOval(ponto.x, ponto.y, x1 - ponto.x, y1 - ponto.y);
            }
            else if(x1 < ponto.x && y1 > ponto.y){
                g.setColor(Color.WHITE);
                g.fillOval(x1, ponto.y, ponto.x - x1, y1 - ponto.y);
            }
            else{
                g.setColor(Color.BLACK);
                g.fillOval(ponto.x, y1, x1 - ponto.x, ponto.y - y1);
            }

        }

        x1 = y1 = -1;
    }
    public static void main(String[] args) {
        new Circulo2D();
    }


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
                        System.out.println(x1 + " " + y1);
                        System.out.println(ponto);
                    }catch(Exception erro){}
                }else{ repaint();}
            }
        }
    }
}
