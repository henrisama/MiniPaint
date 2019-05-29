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

    private int geometricForm = 0;
    private int aux = 0;
    private Point initPoint = new Point(0,0);
    private Point finalPoint = new Point(0,0);
    private Color cor1 = Color.BLACK;
    private Color cor2 = Color.TRANSPARENT;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Shape> shapesRebuild = new ArrayList<Shape>();

    ///////////////////////////////////////////////////////////////// MOUSE EVENT
    public void onPressed(MouseEvent event){
        initPoint.x = (int) event.getX();
        initPoint.y = (int) event.getY();
        if(initPoint.x < 0 || initPoint.y < 0){
            aux = geometricForm;
            geometricForm = 5;
        }
    }

    public void onReleased(MouseEvent event){
        finalPoint.x = (int) event.getX();
        finalPoint.y = (int) event.getY();
        if(finalPoint.x < 0 || finalPoint.y < 0){
            aux = geometricForm;
            geometricForm = 5;
        }
        drawDrawPane();
    }

    ///////////////////////////////////////////////////////////////// VBOX - ARQUIVO
    public void newWindow() throws Exception{
        Stage stage = new Stage();
        new Main().start(stage);
    }

    public void openImage(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            if(file.getName().endsWith(".jpeg") || file.getName().endsWith(".jpg") || file.getName().endsWith(".png")){
                drawPane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(file.toURI().toString())), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    public void saveImage() throws IOException {
        WritableImage image = drawPane.snapshot(new SnapshotParameters(), null);
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }
    }

    public void closeMiniPaint(){
        System.exit(0);
    }

    ///////////////////////////////////////////////////////////////// VBOX - EIDTAR
    public void clearDrawPane(){
        drawPane.getChildren().clear();
        shapes.clear();
        shapesRebuild.clear();
    }

    ///////////////////////////////////////////////////////////////// KEYBOARD EVENT
    public void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.Z && event.isControlDown() && event.isShiftDown() && !shapesRebuild.isEmpty()){
            shapes.add(shapesRebuild.get(shapesRebuild.size() - 1));
            shapesRebuild.remove(shapesRebuild.size() - 1);
            addDraw();
        }
        else if (event.getCode() == KeyCode.Z && event.isControlDown() && !shapes.isEmpty()) {
            shapesRebuild.add(shapes.get(shapes.size()-1));
            shapes.remove(shapes.size() - 1);
            addDraw();
        }
    }

    ///////////////////////////////////////////////////////////////// DRAW IN DRAW PANE
    private void addDraw(){
        drawPane.getChildren().clear();
        drawPane.getChildren().addAll(shapes);
    }

    private Shape initShape(Shape shape){
        shape.setStroke(cor1);
        shape.setFill(cor2);
        return shape;
    }

    private void drawDrawPane(){
        switch (geometricForm){
            case 0:
                break;
            case 1:
                shapes.add(initShape(new Line(initPoint.x, initPoint.y, finalPoint.x, finalPoint.y)));
                break;
            case 2:
                Retangulo retangulo = new Retangulo(initPoint, finalPoint);
                shapes.add(initShape(new Rectangle(retangulo.getPontoInicial().x,retangulo.getPontoInicial().y,retangulo.getWidth(), retangulo.getHeigth())));
                break;
            case 3:
                Circulo circulo = new Circulo(initPoint, finalPoint);
                shapes.add(initShape(new Circle(circulo.getxCentral(), circulo.getyCentral(), circulo.getRaio())));
                break;
            case 4:
                break;
            case 5:
                System.out.println("Fora da Area");
                geometricForm = aux;
                break;
            default:
                System.out.println("Forma nao selecionada");
                break;
        }
        addDraw();
    }

    ///////////////////////////////////////////////////////////////// SET VARIABLES
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

    public void selectedDrawing(){
        geometricForm = 0;
    }

    public void straightSelected(){
        geometricForm = 1;
    }

    public void selectedRectangle(){
        geometricForm = 2;
    }

    public void selectedCircle(){
        geometricForm = 3;
    }

    public void selectedText(){
        geometricForm = 4;
    }
}
