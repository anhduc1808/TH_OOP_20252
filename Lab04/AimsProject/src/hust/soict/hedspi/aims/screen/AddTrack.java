package hust.soict.hedspi.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.screen.controller.AddTrackScreenController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddTrack extends JFrame {
    private CompactDisc cd;

    public AddTrack(CompactDisc cd) {
        super();
        this.cd = cd;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add Track");
        this.setSize(400, 300);
        this.setVisible(true);

        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/addTracks.fxml"));
                AddTrackScreenController controller = new AddTrackScreenController(cd);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
