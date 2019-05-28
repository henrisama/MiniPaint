package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Formas.Circulo;
import sample.Formas.Retangulo;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller{
    @FXML
    AnchorPane drawPane;
    private int formaGeometrica = 0;
    private Point pontoInicial = new Point(0,0);
    private Point pontoFinal = new Point(0,0);
    private int aux = 0;

    public void onPressed(MouseEvent event){
        pontoInicial.x = (int) event.getX();
        pontoInicial.y = (int) event.getY();
        if(pontoInicial.x < 0 || pontoInicial.y < 0){
            aux = formaGeometrica;
            formaGeometrica = 5;
        }
    }

    public void onReleased(MouseEvent event){
        pontoFinal.x = (int) event.getX();
        pontoFinal.y = (int) event.getY();
        if(pontoFinal.x < 0 || pontoFinal.y < 0){
            aux = formaGeometrica;
            formaGeometrica = 5;
        }
        desenhaAnchorPane();
    }

    public void abrirImagem(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            if(file.getName().endsWith(".jpeg") || file.getName().endsWith(".jpg") || file.getName().endsWith(".png")){
                drawPane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(file.toURI().toString())), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    public void salvarImagem() throws IOException {
        WritableImage image = drawPane.snapshot(new SnapshotParameters(), null);
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }
    }

    public void limparAnchorPane(){
        drawPane.getChildren().clear();
    }

    public void fecharMiniPaint(){
        System.exit(1);
    }

    private void desenhaAnchorPane(){
        switch (formaGeometrica){
            case 0:
                break;
            case 1:
                Line line = new Line(pontoInicial.x, pontoInicial.y, pontoFinal.x, pontoFinal.y);
                drawPane.getChildren().addAll(line);
                break;
            case 2:
                Retangulo retangulo = new Retangulo(pontoInicial, pontoFinal);
                Rectangle rectangle = new Rectangle(retangulo.getPontoInicial().x,retangulo.getPontoInicial().y,retangulo.getWidth(), retangulo.getHeigth());
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.TRANSPARENT);
                drawPane.getChildren().addAll(rectangle);
                break;
            case 3:
                Circulo circulo = new Circulo(pontoInicial, pontoFinal);
                Circle circle = new Circle(circulo.getxCentral(), circulo.getyCentral(), circulo.getRaio());
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.TRANSPARENT);
                drawPane.getChildren().addAll(circle);
                break;
            case 4:
                break;
            case 5:
                System.out.println("Fora da Area");
                formaGeometrica = aux;
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
