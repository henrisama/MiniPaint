package MenuBar;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import sample.Main;

public class FileMenuBar {
    public void newWindow(){
        try {
            Stage stage = new Stage();
            new Main().start(stage);
        }catch (Exception e){}
    }

    public void openImage(AnchorPane anchorPane){
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if(file != null){
                if(file.getName().endsWith(".jpeg") || file.getName().endsWith(".jpg") || file.getName().endsWith(".png")){
                    anchorPane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(file.toURI().toString())), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }catch (Exception e){}
    }

    public void saveImage(AnchorPane anchorPane){
        try {
            WritableImage image = anchorPane.snapshot(new SnapshotParameters(), null);
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);
            if(file != null){
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            }
        }catch (IOException e){}
    }

    public void close(){
        System.exit(0);
    }
}
