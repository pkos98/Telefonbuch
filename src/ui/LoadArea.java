package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoadArea extends Area {

    private final Button loadButton = new Button("...");

    public Button getLoadButton() {
        return loadButton;
    }

    public LoadArea() {
        AnchorPane.setTopAnchor(loadButton, 10.0);
        AnchorPane.setRightAnchor(loadButton, 10.0);
        AnchorPane.setBottomAnchor(loadButton, 10.0);
        loadButton.setPrefWidth(30.0);
        loadButton.setMaxHeight(15);

        pane.getChildren().addAll(loadButton);
    }

}
