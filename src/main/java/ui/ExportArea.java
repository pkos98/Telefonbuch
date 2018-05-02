package ui;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import data.TelefonEntry;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportArea extends Area {
    private final Button exportButton = new Button("Save");

    public ExportArea() {

        AnchorPane.setLeftAnchor(exportButton, 20.0);
        AnchorPane.setTopAnchor(exportButton, 10.0);
        AnchorPane.setRightAnchor(exportButton, 10.0);
        AnchorPane.setBottomAnchor(exportButton, 10.0);
        pane.getChildren().addAll(exportButton);
    }

    public void exportFile(List<TelefonEntry> entriesToSave) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose directory to save addressbook in");
        Path path = directoryChooser.showDialog((Stage) exportButton.getScene().getWindow()).toPath();
        path = new File(path.toString() + "/address.json" ).toPath();
        JsonFactory factory = new JsonFactory();
        try (OutputStream os = Files.newOutputStream(path);
             JsonGenerator jg = factory.createGenerator(os)) {
            // Verwenden Sie jg um fuer jeden Eintrag im Telefonbuch
            // entsprechende Objekte im JSON zu erzeugen
            jg.writeStartArray();
            for (int i = 0; i < entriesToSave.size(); i++) {
                TelefonEntry entry = entriesToSave.get(i);
                jg.writeStartObject();
                jg.writeStringField("firstName", entry.getFirstName());
                jg.writeStringField("lastName", entry.getLastName());
                jg.writeStringField("number", entry.getNumber());
                jg.writeEndObject();
            }
            jg.writeEndArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Button getExportButton() {
        return exportButton;
    }
}
