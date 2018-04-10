package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
  
  private final AnchorPane pane = new AnchorPane();
  private final Button deleteButton = new Button("Delete");
  
  public DeleteArea() {
  
    AnchorPane.setTopAnchor(deleteButton, 10.0);
    AnchorPane.setRightAnchor(deleteButton, 10.0);
    AnchorPane.setBottomAnchor(deleteButton, 10.0);
    deleteButton.setPrefWidth(70.0);
    
    pane.getChildren().addAll(deleteButton);
  }
  
  public Node getPane() {
    return pane;
  }
}
