package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.awt.*;
import MenuBar.*;
import Shapes.*;

public class Controller{
    private FileMenuBar fileMenuBar = new FileMenuBar();
    private EditMenuBar editMenuBar = new EditMenuBar();

    @FXML
    AnchorPane drawPane;
    @FXML
    Pane imgColor;
    @FXML
    TextField textFieldPane;
    @FXML
    Label statusLabel;
    @FXML
    Menu recentFile;

    private int geometricForm = 0;
    private int aux = 0;
    private String selectForm = "";
    private Point initPoint = new Point(0,0);
    private Point finalPoint = new Point(0,0);
    private Color color = Color.TRANSPARENT;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Shape> ctrlShiftZ = new ArrayList<Shape>();


    ///////////////////////////////////////////////////////////////// MOUSE EVENT
    public void onPressed(MouseEvent event){
        initPoint.x = (int) event.getX();
        initPoint.y = (int) event.getY();
        if(initPoint.x < 0 || initPoint.y < 0){
            aux = geometricForm;
            geometricForm = 6;
        }
    }

    public void onReleased(MouseEvent event){
        finalPoint.x = (int) event.getX();
        finalPoint.y = (int) event.getY();
        if(finalPoint.x < 0 || finalPoint.y < 0){
            aux = geometricForm;
            geometricForm = 6;
        }
        drawDrawPane();
    }

    public void onDragged(MouseEvent event){
        if(event.getX() > 0 && event.getY() > 0 && geometricForm == 0) {
            shapes.add(new Line(initPoint.x, initPoint.y, event.getX(), event.getY()));
            initPoint.x = (int) event.getX();
            initPoint.y = (int) event.getY();
            addDraw();
        }
        else if(event.getX() > 20 && event.getY() > 20 && geometricForm == 5){
            Circle circle = new Circle(event.getX() - 11, event.getY() - 11, 10);
            circle.setFill(Color.WHITE);
            shapes.add(circle);
            addDraw();
        }
    }

    ///////////////////////////////////////////////////////////////// RECENT FILE INIT
    @FXML
    private void insertRecentFile(){
        fileMenuBar.importRecentFile(recentFile, drawPane);
    }

    ///////////////////////////////////////////////////////////////// VBOX - FILE
    @FXML
    private void eventFileMenuBar(Event event){
        if(event.toString().contains("id=fileNewWindow")){
            fileMenuBar.newWindow();
        }else if(event.toString().contains("id=fileOpenImage")){
            fileMenuBar.openImage(drawPane);
        }else if(event.toString().contains("id=fileSaveImage")){
            fileMenuBar.saveImage(drawPane);
        }else if(event.toString().contains("id=fileClose")){
            fileMenuBar.close();
        }
    }

    ///////////////////////////////////////////////////////////////// UNDO - REDO

    private void undo(){
        ctrlShiftZ.add(shapes.get(shapes.size()-1));
        shapes.remove(shapes.size() - 1);
        addDraw();
    }

    private void redo(){
        shapes.add(ctrlShiftZ.get(ctrlShiftZ.size() - 1));
        ctrlShiftZ.remove(ctrlShiftZ.size() - 1);
        addDraw();
    }

    ///////////////////////////////////////////////////////////////// VBOX - EDIT

    @FXML
    private void eventEditMenuBar(Event event){
        if(event.toString().contains("id=drawClear")){
            editMenuBar.drawClear(drawPane, shapes, ctrlShiftZ);
        }else if(event.toString().contains("id=undo")){
            undo();
        }else if(event.toString().contains("id=redo")){
            redo();
        }
    }

    ///////////////////////////////////////////////////////////////// KEYBOARD EVENT
    public void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.Z && event.isControlDown() && event.isShiftDown() && !ctrlShiftZ.isEmpty()){
            redo();
        }else if (event.getCode() == KeyCode.Z && event.isControlDown() && !shapes.isEmpty()) {
            undo();
        }
    }

    ///////////////////////////////////////////////////////////////// DRAW IN DRAW PANE
    private void addDraw(){
        drawPane.getChildren().clear();
        drawPane.getChildren().addAll(shapes);
    }

    private Shape initShape(Shape shape){
        shape.setStroke(Color.BLACK);
        shape.setFill(color);
        return shape;
    }

    private void drawDrawPane(){
        switch (geometricForm){
            case 1:
                Line2D line2D = new Line2D(initPoint, finalPoint);
                shapes.add(initShape(new Line(line2D.getInitPoint().x, line2D.getInitPoint().y, line2D.getFinalPoint().x, line2D.getFinalPoint().y)));
                break;
            case 2:
                Rectangle2D rectangle2D = new Rectangle2D(initPoint, finalPoint);
                shapes.add(initShape(new Rectangle(rectangle2D.getInitPoint().x, rectangle2D.getInitPoint().y, rectangle2D.getWidth(), rectangle2D.getHeigth())));
                break;
            case 3:
                Circle2D circle2D = new Circle2D(initPoint, finalPoint);
                shapes.add(initShape(new Circle(circle2D.getxCentral(), circle2D.getyCentral(), circle2D.getRadius())));
                break;
            case 4:
                Text2D text2D = new Text2D(initPoint, finalPoint);
                shapes.add(new Text(text2D.getInitPoint().x, text2D.getInitPoint().y, textFieldPane.getText()));
                break;
            case 0:
            case 5:
                break;
            case 6:
                geometricForm = aux;
                statusLabel.setText("Fora da area");
                break;
            default:
                statusLabel.setText("Forma nao selecionada");
                break;
        }
        addDraw();
    }

    ///////////////////////////////////////////////////////////////// SET COLOR
    @FXML
    private void setColorEvent(Event event){
        if(event.toString().contains("id=na")){
            setColor(Color.TRANSPARENT);
        }else if(event.toString().contains("id=yellow")){
            setColor(Color.YELLOW);
        }else if(event.toString().contains("id=black")){
            setColor(Color.BLACK);
        }else if(event.toString().contains("id=red")){
            setColor(Color.RED);
        }else if(event.toString().contains("id=green")){
            setColor(Color.GREEN);
        }else if(event.toString().contains("id=blue")){
            setColor(Color.BLUE);
        }else if(event.toString().contains("id=gray")){
            setColor(Color.GRAY);
        }else if(event.toString().contains("id=pink")){
            setColor(Color.PINK);
        }else if(event.toString().contains("id=purple")){
            setColor(Color.PURPLE);
        }
    }

    private void setColor(Color color){
        this.color = color;
        imgColor.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    ///////////////////////////////////////////////////////////////// SET FORM
    @FXML
    private void selectedForm(Event event){
        if(event.toString().contains("id=drawing")){
            selectFormAndSetCursor(0);
            selectForm = "Desenho Livre";
        }else if(event.toString().contains("id=line")){
            selectFormAndSetCursor(1);
            selectForm = "Reta";
        }else if(event.toString().contains("id=rectangle")){
            selectFormAndSetCursor(2);
            selectForm = "Retangulo";
        }else if(event.toString().contains("id=circle")){
            selectFormAndSetCursor(3);
            selectForm = "Circulo";
        }else if(event.toString().contains("id=text")){
            selectFormAndSetCursor(4);
            selectForm = "Texto";
        }else if(event.toString().contains("id=eraser")){
            selectFormAndSetCursor(5);
            selectForm = "Borracha";
        }
        statusLabel.setText(selectForm + " selecionado");
    }

    private void selectFormAndSetCursor(int num){
        geometricForm = num;
        if(num == 5){
            Image image = new Image(getClass().getResourceAsStream("/Images/er500px.png"));
            drawPane.setCursor(new ImageCursor(image, 500, 500));
        }
        else if(num == 4){
            textFieldPane.setVisible(true);
            drawPane.setCursor(Cursor.DEFAULT);
        }
        else{
            if(num == 0){
                drawPane.setCursor(Cursor.DEFAULT);
            }else if(num > 0 && num < 4){
                drawPane.setCursor(Cursor.CROSSHAIR);
            }
            textFieldPane.setVisible(false);
        }
    }
}
