package ui;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class EntryArea extends Area {
    private final TelephoneTableView tableView;
    private ObservableList<TelefonEntry> rootList;
    private FilteredList<TelefonEntry> filteredList;

    public EntryArea(ObservableList<TelefonEntry> telefonEntries) {
        rootList = telefonEntries;
        filteredList = new FilteredList<>(rootList, p -> true);
        tableView = new TelephoneTableView(filteredList);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        pane.getChildren().addAll(tableView);
        tableView.setOnKeyPressed((e) -> {
            KeyEvent event = (KeyEvent) e;
            if (event.getCode() == KeyCode.DELETE) {
                removeSelectedEntries();
            }
        });
        tableView.setOnKeyPressed((e) -> {
            KeyEvent event = (KeyEvent) e;
            if (event.getCode() == KeyCode.INSERT) {
                rootList.add(new TelefonEntry());
            }
        });
    }

    public void filter(String filter) {
        String lowerCaseFilter = filter.toLowerCase();
        filteredList.setPredicate(telefonEntry -> {
            if (lowerCaseFilter == null || lowerCaseFilter.isEmpty())
                return true;
            boolean filterFirstName = telefonEntry.getFirstName().toLowerCase().contains(lowerCaseFilter);
            boolean filterLastName = telefonEntry.getLastName().toLowerCase().contains(lowerCaseFilter);
            boolean filterNumber = telefonEntry.getNumber().toLowerCase().contains(lowerCaseFilter);
            return (filterFirstName || filterLastName || filterNumber);
        });
        tableView.setItems(filteredList);
    }

    public void removeSelectedEntries() {
        rootList.removeAll(getSelectedEntries());
    }

    public ObservableList<TelefonEntry> getSelectedEntries() {
        return tableView.getSelectionModel().getSelectedItems();
    }

    public void add(TelefonEntry entry) {
        rootList.add(entry);
    }

    public TelephoneTableView getTableView() {
        return tableView;
    }
}