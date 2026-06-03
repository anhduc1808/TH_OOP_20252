package hust.soict.hedspi.aims.screen.controller;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddBookScreenController {
    private Store store;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfCost;
    @FXML
    private TextField tfAuthors;

    public AddBookScreenController(Store store) {
        this.store = store;
    }

    @FXML
    void addBookPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String authorsStr = tfAuthors.getText();

        Book book = new Book(title, category, cost);
        if (authorsStr != null && !authorsStr.isEmpty()) {
            String[] authors = authorsStr.split(",");
            for (String author : authors) {
                book.addAuthor(author.trim());
            }
        }
        store.addMedia(book);
    }
}
