package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AddArea extends Area {

    private final Button addButton = new Button("Add");

    public Button getAddButton() {
        return addButton;
    }

    public AddArea() {

        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setRightAnchor(addButton, 10.0);
        AnchorPane.setBottomAnchor(addButton, 10.0);
        addButton.setPrefWidth(70.0);

        pane.getChildren().addAll(addButton);
    }

}
