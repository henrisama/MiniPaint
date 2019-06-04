package MenuBar;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
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
                    recentFile(file);
                }
            }
        }catch (Exception e){}
    }

    private void recentFile(File file){
        if(!isOverflowRecentFile()){
            saveRecentFile(file);
        }
        else{
            remakeRecentFile(file);
        }
    }

    private boolean isOverflowRecentFile(){
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File("recentFile")));
            while(lineNumberReader.readLine() != null);
            if(lineNumberReader.getLineNumber() > 4){ return true; }
        }catch (Exception e){}
        return false;
    }

    private int checkFile(){
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(new File("recentFile")));
            while(lineNumberReader.readLine() != null);
            if(lineNumberReader.getLineNumber() > 0){ return lineNumberReader.getLineNumber(); }
        }catch (Exception e){}
        return 0;
    }

    public void importRecentFile(Menu menu, AnchorPane anchorPane){
        int i = checkFile();
        if(i > 0){
            try {
                File file = new File("recentFile");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                menu.getItems().clear();
                for(; i> 0; i--){
                    MenuItem menuItem = new MenuItem(bufferedReader.readLine());
                    menuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            anchorPane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(menuItem.getText())), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    });
                    menu.getItems().addAll(menuItem);
                }
            }catch (Exception e){}
        }
    }

    private void saveRecentFile(File saveFile){
        try {
            File recentFile = new File("recentFile");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(recentFile, true));
            bufferedWriter.write(saveFile.toURI().toString() + "\n");
            bufferedWriter.close();
        }
        catch (Exception e){}
    }

    private void remakeRecentFile(File file){
        try {
            File recentFile = new File("recentFile");

            //part 1
            BufferedReader bufferedReader = new BufferedReader(new FileReader(recentFile));
            String[] paths = new String[5];
            for(int i = 0; i < 5; i++){
                paths[i] = bufferedReader.readLine();
            }
            bufferedReader.close();

            //part 2
            Writer clean = new FileWriter(recentFile);
            clean.write("");
            clean.close();

            //part 3
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(recentFile, true));
            for(int i = 0; i < 4; i++){
                bufferedWriter.write(paths[i+1] + "\n");
            }
            bufferedWriter.close();

            //part 4
            saveRecentFile(file);
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
