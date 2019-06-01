package MenuBar;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class EditMenuBar {
    public void drawClear(AnchorPane anchorPane, ArrayList<Shape> shapeArrayList1, ArrayList<Shape> shapeArrayList2){
        anchorPane.getChildren().clear();
        shapeArrayList1.clear();
        shapeArrayList2.clear();
    }
}
