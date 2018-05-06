package ui;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TelephoneTableView extends TableView<TelefonEntry> {

    public TelephoneTableView(ObservableList<TelefonEntry> entries) {
        super();
        Callback<TableColumn<TelefonEntry, String>, TableCell<TelefonEntry, String>> cellFactory = p -> new EditingCell();

        TableColumn<TelefonEntry, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(t -> getCurrentRow(t).setLastName(t.getNewValue()));

        TableColumn<TelefonEntry, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(t -> getCurrentRow(t).setFirstName(t.getNewValue()));

        TableColumn<TelefonEntry, String> emailCol = new TableColumn<>("Number");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(t -> getCurrentRow(t).setNumber(t.getNewValue()));

        getColumns().add(firstNameCol);
        getColumns().add(lastNameCol);
        getColumns().add(emailCol);
        setItems(entries);
        setEditable(true);
    }

    private static class EditingCell extends TableCell<TelefonEntry, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText(getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
                if (!arg2) {
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

    private static TelefonEntry getCurrentRow(TableColumn.CellEditEvent<TelefonEntry, String> t) {
        return t.getTableView().getItems().get(t.getTablePosition().getRow());
    }

}
