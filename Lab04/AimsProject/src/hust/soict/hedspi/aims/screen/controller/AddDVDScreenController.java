package hust.soict.hedspi.aims.screen.controller;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDVDScreenController {
    private Store store;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfDirector;
    @FXML
    private TextField tfLength;
    @FXML
    private TextField tfCost;

    public AddDVDScreenController(Store store) {
        this.store = store;
    }

    @FXML
    void addDVDPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        String director = tfDirector.getText();
        int length = Integer.parseInt(tfLength.getText());
        float cost = Float.parseFloat(tfCost.getText());

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
        store.addMedia(dvd);
    }
}
