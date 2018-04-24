package sample;

import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ui.*;

import java.security.Key;
import java.util.LinkedList;


// was ist das boot sdk
// und was ist der unterschied zum platform setting sdk


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        DeleteArea deleteArea = new DeleteArea();
        AddArea addArea = new AddArea();
        HBox hBox = new HBox();
        ObservableList<TelefonEntry> rootList = FXCollections.observableArrayList();
        EntryArea entryArea = new EntryArea(rootList);
        rootList.add(new TelefonEntry("Patrick", "Kostas", "213981204809"));
        rootList.add(new TelefonEntry("Der", "Test", "213981204809"));
        rootList.add(new TelefonEntry("Ein", "Anderer", "213981204809"));
        searchArea.getSearchButton().setOnMouseClicked((e) -> {
            String s = searchArea.getSearchText();
            LinkedList<TelefonEntry> viewList = new LinkedList<>();
            if (s.isEmpty()) {
                entryArea.setItems(rootList);
                return;
            }
            for (int i = 0; i < rootList.size(); i++) {
                TelefonEntry iterEntry = rootList.get(i);
                if (iterEntry.getFirstName().contains(s) || iterEntry.getLastName().contains(s))
                    viewList.add(iterEntry);
            }
            entryArea.setItems(viewList);
        });
        deleteArea.getDeleteButton().setOnMouseClicked((e) -> {
            entryArea.removeEntries(entryArea.getSelectedEntries());
        });
        addArea.getAddButton().setOnMouseClicked((e) -> {
            rootList.add(new TelefonEntry());
        });

        hBox.getChildren().add(deleteArea.getPane());
        hBox.getChildren().add(addArea.getPane());
        root.setTop(searchArea.getPane());
        root.setBottom(hBox);
        //root.setBottom(deleteArea.getPane());
        root.setCenter(entryArea.getAnchorPane());

        primaryStage.setTitle("Telefonbuch");
        Scene scene = new Scene(root, 600, 500);
        /*scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() != KeyCode.DELETE)
                    return;
                entryArea.removeEntries(entryArea.getSelectedEntries());
            }
        });*/
        scene.setOnKeyPressed((e) -> {
            KeyEvent event = (KeyEvent) e;
            if (event.getCode() != KeyCode.DELETE)
                return;
            entryArea.removeEntries(entryArea.getSelectedEntries());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
