package TesteJFrame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Projeto extends Application {
    Pane root = new Pane ( );
    Scene scene = new Scene ( root, 600, 310 );
    Canvas canvas = new Canvas ( 600, 310 );
    GraphicsContext ctx = canvas.getGraphicsContext2D ( );
    double x1, y1;

    EventHandler < MouseEvent > mouseHandler =
            new EventHandler < MouseEvent > ( ) {
                @Override
                public void handle ( MouseEvent mouseEvent ) {
                    /*ctx.setFont ( Font.font ( "Helvetica", FontWeight.BOLD,
                            FontPosture.ITALIC, 15 ) );

                    ctx.clearRect (  30, 45, 120, 50 );

                    ctx.setFill ( Color.BLACK );
                    ctx.fillText ( "Y => ", 40, 60 );
                    ctx.fillText ( "X => ", 40, 80 );
                    ctx.setFill ( Color.RED );
                    ctx.fillText ( " " + mouseEvent.getY ( ), 80, 60 );
                    ctx.fillText ( " " + mouseEvent.getX ( ), 80, 80 );*/
                    x1 = mouseEvent.getX();
                    y1 = mouseEvent.getY();
                    System.out.println(x1 + " " + y1);
                }
            };

    public void detecta_coord ( ) {
        scene.setOnMouseMoved ( mouseHandler );
        scene.setOnMouseReleased ( mouseHandler );
    }

    @Override
    public void start ( Stage stage ) {
        // Configurando um título para o stage
        stage.setTitle ( "JAVAFX - CAPTURANDO AS COORDENADAS DO MOUSE" );
        // Criando uma moldura retangular em canvas
        /*ctx.setStroke ( Color.BLACK );
        ctx.setLineWidth ( 10.0 );
        ctx.strokeRoundRect ( 10, 10, 580, 290, 10, 10 );
        ctx.setFont ( Font.font ( "Helvetica", FontWeight.BOLD,
                FontPosture.ITALIC, 15 ) );
        ctx.setFill ( Color.RED );
        ctx.fillText ( "JAVAFX - CAPTURANDO AS COORDENADAS DO MOUSE", 110, 40 );*/

        detecta_coord ( );
        /*root.setStyle (
                "-fx-background-color:"
                        + " radial-gradient(center 50% 50% ,"
                        + " radius 5% , repeat,"
                        + " #fffaf0, LIGHTGRAY, CYAN);");
        root.getChildren ( ).addAll ( canvas );*/
        stage.setScene ( scene );
        stage.show ( );
    }

    public static void main ( String [ ] args ) {
        launch ( args );
    }
}
