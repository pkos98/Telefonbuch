package ui;

import javafx.scene.layout.AnchorPane;

public abstract class Area {

    protected final AnchorPane pane = new AnchorPane();

    public AnchorPane getPane() {
        return pane;
    }

}
