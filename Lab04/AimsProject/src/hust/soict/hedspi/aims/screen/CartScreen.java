package hust.soict.hedspi.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.screen.controller.CartScreenController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {
    private Cart cart;
    private Store store;

    public CartScreen(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setVisible(true);

        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("view/cart.fxml"));
                CartScreenController controller = new CartScreenController(cart, store);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
