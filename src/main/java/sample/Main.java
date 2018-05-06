package sample;

import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.*;

import java.util.List;


public class Main extends Application {

    EntryArea lastFocusedTable;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableList<TelefonEntry> mainList = FXCollections.observableArrayList();
        ObservableList<TelefonEntry> guestList = FXCollections.observableArrayList();
        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        DeleteArea deleteArea = new DeleteArea();
        AddArea addArea = new AddArea();
        ImportArea importArea = new ImportArea();
        ExportArea exportArea = new ExportArea();

        HBox topHBox = new HBox();
        topHBox.getChildren().add(importArea.getPane());
        topHBox.getChildren().add(exportArea.getPane());
        topHBox.getChildren().add(searchArea.getPane());
        topHBox.setAlignment(Pos.CENTER_LEFT);

        HBox bottomHBox = new HBox();
        TextField focusedTableLabel = new TextField("No table selected");
        focusedTableLabel.setPrefWidth(400.0);
        focusedTableLabel.setAlignment(Pos.CENTER_LEFT);
        focusedTableLabel.setBackground(Background.EMPTY);
        focusedTableLabel.fontProperty().set(Font.font("Consolas", 10));
        bottomHBox.getChildren().add(focusedTableLabel);
        bottomHBox.getChildren().add(deleteArea.getPane());
        bottomHBox.getChildren().add(addArea.getPane());
        bottomHBox.setAlignment(Pos.CENTER);

        VBox centerVBox = new VBox();
        centerVBox.getChildren().add(new Button());
        centerVBox.getChildren().add(new Button());
        centerVBox.setAlignment(Pos.CENTER);

        // Contains ALL, even FILTERED-OUT entries
        EntryArea mainArea = new EntryArea(mainList);
        EntryArea guestArea = new EntryArea(FXCollections.observableArrayList());
        mainList.add(new TelefonEntry("Kostas", "Patrick", "213981204809"));
        mainList.add(new TelefonEntry("Test", "Der", "213981204809"));
        mainList.add(new TelefonEntry("Einer", "Noch", "213981204809"));
        guestArea.add(new TelefonEntry());

        // Set Event-handlers
        searchArea.getSearchField().setOnKeyReleased((e) -> {
            mainArea.filter(searchArea.getSearchText());
            guestArea.filter(searchArea.getSearchText());
        });

        searchArea.getSearchButton().setOnMouseClicked((e) -> {
            mainArea.filter(searchArea.getSearchText());
            guestArea.filter(searchArea.getSearchText());
        });
        deleteArea.getDeleteButton().setOnMouseClicked((e) -> {
            mainArea.removeSelectedEntries();
            guestArea.removeSelectedEntries();
        });
        addArea.getAddButton().setOnMouseClicked((e) -> {
            mainArea.add(new TelefonEntry());
        });
        importArea.getImportButton().setOnMouseClicked((e) -> {
            if (lastFocusedTable == null)
                return;
            List<TelefonEntry> importedEntries = importArea.importFile();
            for (int i = 0; i < importedEntries.size(); i++) {
                TelefonEntry iterEntry = importedEntries.get(i);
                if (!lastFocusedTable.getTableView().getItems().contains(iterEntry))
                    lastFocusedTable.add(iterEntry);
            }
        });

        exportArea.getExportButton().setOnMouseClicked((e) -> {
            exportArea.exportFile(mainArea.getTableView().getItems());
        });

        root.setTop(topHBox);
        root.setBottom(bottomHBox);
        root.setCenter(centerVBox);
        root.setLeft(mainArea.getPane());
        root.setRight(guestArea.getPane());

        primaryStage.setTitle("Telefonbuch");
        Scene scene = new Scene(root, 600, 500);
        scene.focusOwnerProperty().addListener((x,y, focusedControl) -> {
            if (focusedControl == (Node)mainArea.getTableView()) {
                lastFocusedTable = mainArea;
                focusedTableLabel.setText("Main table selected");
            }
            else if (focusedControl == (Node)guestArea.getTableView()) {
                lastFocusedTable = guestArea;
                focusedTableLabel.setText("Guest table selected");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
