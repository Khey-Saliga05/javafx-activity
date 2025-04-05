package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    @FXML
    private Button deleteButton; // Make sure to connect this to your FXML

    private ObservableList<String> todoItems;

    @FXML
    public void initialize() {
        // Initialize the ObservableList
        todoItems = FXCollections.observableArrayList();

        // Optionally add a sample item to start with
        todoItems.add("Remove Me");

        // Set the ObservableList to the ListView
        todoList.setItems(todoItems);

        // Set selection mode to SINGLE to allow only one item to be selected
        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Listener to handle item click events
        todoList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> handleSelectionChange(newVal)
        );
    }

    private void handleSelectionChange(String selectedItem) {
        // Enable/Disable Delete button based on selection
        boolean isItemSelected = selectedItem != null;
        deleteButton.setDisable(!isItemSelected); // Update delete button enablement

        if (isItemSelected) {
            onTodoListItemClick(selectedItem);
        }
    }

    private String showTextInputDialog(String initialValue, String title, String header) {
        TextInputDialog dialog = new TextInputDialog(initialValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText("Please enter your todo item:");
        return dialog.showAndWait().orElse(null);
    }

    private void onTodoListItemClick(String value) {
        String updatedValue = showTextInputDialog(value, "Update Todo", "Update your todo item:");
        if (updatedValue != null && !updatedValue.isEmpty()) {
            int index = todoItems.indexOf(value);
            if (index >= 0) {
                todoItems.set(index, updatedValue);
            }
        }
    }

    @FXML
    protected void onCreateClick() {
        String newItem = showTextInputDialog("", "Create New Todo", "Enter new todo item:");
        if (newItem != null && !newItem.isEmpty()) {
            todoItems.add(newItem);
        }
    }

    @FXML
    protected void onDeleteClick() {
        String selectedItem = todoList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            showDeleteConfirmationDialog(selectedItem);
        } else {
            showWarningDialog("No Selection", "No Todo Selected", "Please select a todo item to delete.");
        }
    }

    private void showDeleteConfirmationDialog(String selectedItem) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation Dialog");
        confirm.setHeaderText("Are you sure you want to delete this todo?");
        confirm.setContentText("This action cannot be undone.");

        confirm.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                todoItems.remove(selectedItem); // Remove the selected item
            }
        });
    }

    private void showWarningDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void onListEdit() {
        // Placeholder method for any future functionality (e.g., bulk edit or advanced options)
    }
}
