package Teste;

import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class DesenhoLivre extends JFrame {

    boolean mouseClick = false;

    public DesenhoLivre(){


            addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {

                }

                public void mousePressed(MouseEvent e) {
                    mouseClick = true;
                    desenhos.clear();
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

    ArrayList<Desenho> desenhos = new ArrayList<>();

    public class Desenho {
        int x, y;
        public Desenho(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void paint(Graphics g) {
        for(int i = 1; i < desenhos.size(); i++){
            int x = desenhos.get(i).x;
            int x2 = desenhos.get(i-1).x;
            int y = desenhos.get(i).y;
            int y2 = desenhos.get(i-1).y;
            g.drawLine(x2,y2,x,y);
        }

    }

    public static void main(String[] args) {
        new DesenhoLivre();
    }


    public class mapaDesenho extends Thread {
        public void run() {
            while(true){
                if(mouseClick){
                    try {
                        Point ponto = getMousePosition();
                        desenhos.add(new Desenho(ponto.x, ponto.y));
                        System.out.println(ponto);
                    }catch(Exception erro){}
                }
                repaint();
            }
        }
    }


}