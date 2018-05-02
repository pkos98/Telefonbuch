package ui;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class EntryArea extends Area {
    private final TableView<TelefonEntry> tableView;
    private ObservableList<TelefonEntry> rootList;
    private FilteredList<TelefonEntry> filteredList;

    public EntryArea(ObservableList<TelefonEntry> telefonEntries) {
        rootList = telefonEntries;
        filteredList = new FilteredList<>(rootList, p -> true);
        tableView = new TableView<>();
        tableView.setItems(filteredList);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        pane.getChildren().addAll(tableView);

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

        tableView.getColumns().add(firstNameCol);
        tableView.getColumns().add(lastNameCol);
        tableView.getColumns().add(emailCol);
        tableView.setItems(telefonEntries);
        tableView.setEditable(true);
    }

    public void filter(String filter) {
        String lowerCaseFilter = filter.toLowerCase();
        filteredList.setPredicate(telefonEntry -> {
            if (lowerCaseFilter == null || lowerCaseFilter.isEmpty())
                return true;
            boolean filterFirstName = telefonEntry.getFirstName().toLowerCase().contains(lowerCaseFilter);
            boolean filterLastName = telefonEntry.getLastName().toLowerCase().contains(lowerCaseFilter);
            boolean filterNumber = telefonEntry.getNumber().toLowerCase().contains(lowerCaseFilter);
            return (filterFirstName || filterLastName|| filterNumber);
        });
        tableView.setItems(filteredList);
    }

    public void removeSelectedEntries() {
        rootList.removeAll(getSelectedEntries());
    }

    public ObservableList<TelefonEntry> getSelectedEntries() {
        return tableView.getSelectionModel().getSelectedItems();
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