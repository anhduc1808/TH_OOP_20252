package hust.soict.hedspi.aims.screen.controller;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTrackScreenController {
    private CompactDisc cd;

    @FXML
    private TextField tfTrackTitle;
    @FXML
    private TextField tfTrackLength;
    @FXML
    private Label statusLabel;

    public AddTrackScreenController(CompactDisc cd) {
        this.cd = cd;
    }

    @FXML
    void addTrackPressed(ActionEvent event) {
        String title = tfTrackTitle.getText();
        int length = Integer.parseInt(tfTrackLength.getText());

        Track track = new Track(title, length);
        cd.addTrack(track);
        statusLabel.setText("Track added: " + title);
        tfTrackTitle.clear();
        tfTrackLength.clear();
    }
}
