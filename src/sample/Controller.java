package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Formas.Circulo;
import sample.Formas.Retangulo;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller{
    @FXML
    AnchorPane drawPane;

    private int formaGeometrica = 0;
    private Point pontoInicial = new Point(0,0);
    private Point pontoFinal = new Point(0,0);
    private int aux = 0;
    private Color cor1 = Color.BLACK;
    private Color cor2 = Color.TRANSPARENT;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Shape> shapesRefazer = new ArrayList<Shape>();

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

    public void novaJanela() throws Exception{
        Stage stage = new Stage();
        new Main().start(stage);
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

    public void fecharMiniPaint(){
        System.exit(0);
    }

    public void limparAnchorPane(){
        drawPane.getChildren().clear();
        shapes.clear();
        shapesRefazer.clear();
    }

    public void metodoDesfazer(KeyEvent event) {
        KeyCode keyCodeZ = KeyCode.Z;
        if(event.getCode() == keyCodeZ && event.isControlDown() && event.isShiftDown() && !shapesRefazer.isEmpty()){
            shapes.add(shapesRefazer.get(shapesRefazer.size() - 1));
            shapesRefazer.remove(shapesRefazer.size() - 1);
            addDesenho();
        }
        else if (event.getCode() == keyCodeZ && event.isControlDown() && !shapes.isEmpty()) {
            shapesRefazer.add(shapes.get(shapes.size()-1));
            shapes.remove(shapes.size() - 1);
            addDesenho();
        }
    }

    private void addDesenho(){
        drawPane.getChildren().clear();
        drawPane.getChildren().addAll(shapes);
    }

    private Shape initShape(Shape shape){
        shape.setStroke(cor1);
        shape.setFill(cor2);
        return shape;
    }

    private void desenhaAnchorPane(){
        switch (formaGeometrica){
            case 0:
                break;
            case 1:
                shapes.add(initShape(new Line(pontoInicial.x, pontoInicial.y, pontoFinal.x, pontoFinal.y)));
                break;
            case 2:
                Retangulo retangulo = new Retangulo(pontoInicial, pontoFinal);
                shapes.add(initShape(new Rectangle(retangulo.getPontoInicial().x,retangulo.getPontoInicial().y,retangulo.getWidth(), retangulo.getHeigth())));
                break;
            case 3:
                Circulo circulo = new Circulo(pontoInicial, pontoFinal);
                shapes.add(initShape(new Circle(circulo.getxCentral(), circulo.getyCentral(), circulo.getRaio())));
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
        addDesenho();
    }

    public void setCor1_Yellow(){
        cor1 = Color.YELLOW;
    }
    public void setCor1_Black(){
        cor1 = Color.BLACK;
    }
    public void setCor1_RED(){
        cor1 = Color.RED;
    }
    public void setCor1_Green(){
        cor1 = Color.GREEN;
    }
    public void setCor1_Blue(){
        cor1 = Color.BLUE;
    }
    public void setCor1_Gray(){
        cor1 = Color.GRAY;
    }
    public void setCor1_Pink(){
        cor1 = Color.PINK;
    }
    public void setCor1_Purple(){
        cor1 = Color.PURPLE;
    }

    public void setCor2_Yellow(){
        cor2 = Color.YELLOW;
    }
    public void setCor2_Black(){
        cor2 = Color.BLACK;
    }
    public void setCor2_RED(){
        cor2 = Color.RED;
    }
    public void setCor2_Green(){
        cor2 = Color.GREEN;
    }
    public void setCor2_Blue(){
        cor2 = Color.BLUE;
    }
    public void setCor2_Gray(){
        cor2 = Color.GRAY;
    }
    public void setCor2_Pink(){
        cor2 = Color.PINK;
    }
    public void setCor2_Purple(){
        cor2 = Color.PURPLE;
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
