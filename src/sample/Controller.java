package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextField;
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
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Shapes.Circle2D;
import sample.Shapes.Rectangle;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller{
    @FXML
    AnchorPane drawPane;

    @FXML
    Pane imgColor1;

    @FXML
    Pane imgColor2;

    @FXML
    TextField textFieldPane;

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
                Rectangle rectangle = new Rectangle(initPoint, finalPoint);
                shapes.add(initShape(new javafx.scene.shape.Rectangle(rectangle.getInitPoint().x,rectangle.getInitPoint().y,rectangle.getWidth(), rectangle.getHeigth())));
                break;
            case 3:
                Circle2D circle2D = new Circle2D(initPoint, finalPoint);
                shapes.add(initShape(new Circle(circle2D.getxCentral(), circle2D.getyCentral(), circle2D.getRadius())));
                break;
            case 4:
                shapes.add(new Text(initPoint.x, initPoint.y, textFieldPane.getText()));
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
        imgColor1.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Black(){
        cor1 = Color.BLACK;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_RED(){
        cor1 = Color.RED;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Green(){
        cor1 = Color.GREEN;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Blue(){
        cor1 = Color.BLUE;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Gray(){
        cor1 = Color.GRAY;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Pink(){
        cor1 = Color.PINK;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor1_Purple(){
        cor1 = Color.PURPLE;
        imgColor1.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    public void setCor2_na(){
        cor2 = Color.TRANSPARENT;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setCor2_Yellow(){
        cor2 = Color.YELLOW;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Black(){
        cor2 = Color.BLACK;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_RED(){
        cor2 = Color.RED;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Green(){
        cor2 = Color.GREEN;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Blue(){
        cor2 = Color.BLUE;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Gray(){
        cor2 = Color.GRAY;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Pink(){
        cor2 = Color.PINK;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public void setCor2_Purple(){
        cor2 = Color.PURPLE;
        imgColor2.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void selectedDrawing(){
        geometricForm = 0;
        textFieldPane.setVisible(false);
    }

    public void straightSelected(){
        geometricForm = 1;
        textFieldPane.setVisible(false);
    }

    public void selectedRectangle(){
        geometricForm = 2;
        textFieldPane.setVisible(false);
    }

    public void selectedCircle(){
        geometricForm = 3;
        textFieldPane.setVisible(false);
    }

    public void selectedText(){
        geometricForm = 4;
        textFieldPane.setVisible(true);
    }
}
