package sample;


import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Controller{
    @FXML
    Pane drawPane;

    //0: Desenho Livre, 1: Reta, 2: Retangulo/Quadrado, 3: Circulo, 4: Texto;
    private int formaGeometrica = 0;


    private double x1 = 0;
    private double y1 = 0;

    public void onPressed(MouseEvent event){
        x1 = event.getX();
        y1 = event.getY();
    }

    public void onReleased(MouseEvent event){
        switch (formaGeometrica){
            case 0:
                break;
            case 1:
                Line line = new Line(x1, y1, event.getX(), event.getY());
                drawPane.getChildren().addAll(line);
                break;
            case 2:
            case 3:
            case 4:
                break;
            default:
                System.out.println("Forma nao selecionada");
                break;
        }
    }

    public void livreSelecionado(){
        formaGeometrica = 0;
    }

    public void retaSelecionado(){
        formaGeometrica = 1;
    }

    public void retanguloSelecionado(){ formaGeometrica = 2; }

    public void circuloSelecionado(){ formaGeometrica = 3; }

    public void textoSelecionado(){formaGeometrica = 4; }

}
