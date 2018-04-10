package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class SearchArea {
  
  private final AnchorPane pane = new AnchorPane();
  private final TextField searchField = new TextField("halo, gib text");
  private final Button searchButton = new Button("Search");
  
  
  public SearchArea() {
    
    AnchorPane.setLeftAnchor(searchField, 10.0);
    AnchorPane.setTopAnchor(searchField, 10.0);
    AnchorPane.setBottomAnchor(searchField, 10.0);
    AnchorPane.setRightAnchor(searchField, 90.0);
    
    AnchorPane.setTopAnchor(searchButton, 10.0);
    AnchorPane.setRightAnchor(searchButton, 10.0);
    AnchorPane.setBottomAnchor(searchButton, 10.0);
    searchButton.setPrefWidth(70.0);
    
    pane.getChildren().addAll(searchField, searchButton);
  }
  
  public Node getPane() {
    return pane;
  }
}
