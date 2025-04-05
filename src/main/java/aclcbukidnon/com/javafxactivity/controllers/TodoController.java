package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    @FXML
    private Button deleteButton;

    private ObservableList<String> todoItems;

    @FXML
    public void initialize() {

        todoItems = FXCollections.observableArrayList();


        todoItems.add("Remove Me");


        todoList.setItems(todoItems);


        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        todoList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> handleSelectionChange(newVal)
        );
    }

    private void handleSelectionChange(String selectedItem) {

        boolean isItemSelected = selectedItem != null;
        deleteButton.setDisable(!isItemSelected);
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
                todoItems.remove(selectedItem);
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
        String selectedItem = todoList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String updatedValue = showTextInputDialog(selectedItem, "Edit Todo", "Edit your selected todo item:");
            if (updatedValue != null && !updatedValue.isEmpty()) {
                int index = todoItems.indexOf(selectedItem);
                if (index >= 0) {
                    todoItems.set(index, updatedValue);
                }
            }
        } else {
            showWarningDialog("No Selection", "No Todo Selected", "Please select a todo item to edit.");
        }
    }

    }

