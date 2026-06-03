package hust.soict.hedspi.aims.screen.controller;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCDScreenController {
    private Store store;
    private CompactDisc currentCD;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfCategory;
    @FXML
    private TextField tfArtist;
    @FXML
    private TextField tfCost;

    public AddCDScreenController(Store store) {
        this.store = store;
    }

    @FXML
    void addCDPressed(ActionEvent event) {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        String artist = tfArtist.getText();
        float cost = Float.parseFloat(tfCost.getText());

        currentCD = new CompactDisc(title, category, artist, cost);
        store.addMedia(currentCD);
    }

    @FXML
    void addTracksPressed(ActionEvent event) {
        if (currentCD == null) {
            addCDPressed(event);
        }
    }

    public CompactDisc getCurrentCD() {
        return currentCD;
    }
}
