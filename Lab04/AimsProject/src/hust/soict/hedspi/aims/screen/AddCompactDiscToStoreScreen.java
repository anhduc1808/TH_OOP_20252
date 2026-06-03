package hust.soict.hedspi.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;

import hust.soict.hedspi.aims.screen.controller.AddCDScreenController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddCompactDiscToStoreScreen extends JFrame {
    private Store store;

    public AddCompactDiscToStoreScreen(Store store) {
        super();
        this.store = store;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add CD");
        this.setSize(400, 400);
        this.setVisible(true);

        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/addCD.fxml"));
                AddCDScreenController controller = new AddCDScreenController(store);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
