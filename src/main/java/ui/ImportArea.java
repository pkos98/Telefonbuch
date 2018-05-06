package ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TelefonEntry;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ImportArea extends Area {

    private final Button importButton = new Button("Import");

    public Button getImportButton() {
        return importButton;
    }

    public ImportArea() {


        AnchorPane.setLeftAnchor(importButton, 10.0);
        AnchorPane.setTopAnchor(importButton, 10.0);
        AnchorPane.setBottomAnchor(importButton, 10.0);
        importButton.setMaxHeight(15);

        pane.getChildren().addAll(importButton);
    }

    public List<TelefonEntry> importFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open address book");
        File chosenFile = fileChooser.showOpenDialog(importButton.getScene().getWindow());
        if (chosenFile == null)
            return new LinkedList<>();
        List<TelefonEntry> entries = new LinkedList<>();
        try (InputStream is = Files.newInputStream(chosenFile.toPath())) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(is);
            for (Iterator<JsonNode> it = rootNode.elements(); it.hasNext(); ) {
                JsonNode node = it.next();
                String firstName = node.get("firstName").textValue();
                String lastName = node.get("lastName").textValue();
                String number = node.get("number").textValue();
                TelefonEntry newEntry = new TelefonEntry(lastName, firstName, number);
                entries.add(newEntry);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return entries;
    }
}
