package sample;

import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ui.*;

import java.util.LinkedList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        DeleteArea deleteArea = new DeleteArea();
        AddArea addArea = new AddArea();
        LoadArea loadArea = new LoadArea();
        HBox addDeleteHBox = new HBox();

        addDeleteHBox.setAlignment(Pos.CENTER_RIGHT);
        // Contains ALL, even OUT-FILTERED entries
        ObservableList<TelefonEntry> rootList = FXCollections.observableArrayList();
        EntryArea entryArea = new EntryArea(rootList);
        rootList.add(new TelefonEntry("Patrick", "Kostas", "213981204809"));
        rootList.add(new TelefonEntry("Der", "Test", "213981204809"));
        rootList.add(new TelefonEntry("Ein", "Anderer", "213981204809"));

        // Set Event-handlers
        searchArea.getSearchField().setOnKeyReleased((e) -> {
            entryArea.filter(searchArea.getSearchText());
        });
        searchArea.getSearchButton().setOnMouseClicked((e) -> {
            entryArea.filter(searchArea.getSearchText());
        });
        deleteArea.getDeleteButton().setOnMouseClicked((e) -> {
            entryArea.removeSelectedEntries();
        });
        addArea.getAddButton().setOnMouseClicked((e) -> {
            rootList.add(new TelefonEntry());
        });
        loadArea.getLoadButton().setOnMouseClicked((e) -> {
            //rootList.clear();
        });
        entryArea.getPane().setOnKeyPressed((e) -> {
            KeyEvent event = (KeyEvent) e;
            if (event.getCode() == KeyCode.DELETE)
                entryArea.removeSelectedEntries();
        });


        addDeleteHBox.getChildren().add(deleteArea.getPane());
        addDeleteHBox.getChildren().add(addArea.getPane());

        root.setTop(searchArea.getPane());
        root.setBottom(addDeleteHBox);
        root.setCenter(entryArea.getPane());
        root.setRight(loadArea.getPane());

        primaryStage.setTitle("Telefonbuch");
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
