package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea extends Area {

    private final Button deleteButton = new Button("Delete");

    public Button getDeleteButton() {
        return deleteButton;
    }

    public DeleteArea() {

        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setRightAnchor(deleteButton, 10.0);
        AnchorPane.setBottomAnchor(deleteButton, 10.0);
        deleteButton.setPrefWidth(70.0);

        pane.getChildren().addAll(deleteButton);
    }
}
