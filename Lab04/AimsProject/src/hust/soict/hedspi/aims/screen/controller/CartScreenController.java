package hust.soict.hedspi.aims.screen.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;

public class CartScreenController {
    private Cart cart;
    private Store store;

    @FXML
    private TextField tfFilter;
    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, String> colTitle;
    @FXML
    private TableColumn<Media, String> colCategory;
    @FXML
    private TableColumn<Media, Float> colCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private Label totalCostLabel;
    @FXML
    private Button btnPlaceOrder;

    public CartScreenController(Cart cart, Store store) {
        super();
        this.cart = cart;
        this.store = store;
    }

    @FXML
    private void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        FilteredList<Media> filteredItems = new FilteredList<>(cart.getItemsOrdered(), p -> true);

        tblMedia.setItems(filteredItems);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if (newValue != null) {
                    btnPlay.setDisable(!(newValue instanceof Playable));
                    btnRemove.setDisable(false);
                }
            }
        });

        totalCostLabel.setText(cart.totalCost() + " $");

        cart.getItemsOrdered().addListener((javafx.collections.ListChangeListener<Media>) c -> {
            totalCostLabel.setText(cart.totalCost() + " $");
        });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        btnPlay.setDisable(true);
        btnRemove.setDisable(true);
    }

    private void showFilteredMedia(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }

        FilteredList<Media> filteredItems = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        filteredItems.setPredicate(media -> {
            RadioButton selected = (RadioButton) tfFilter.getScene().lookup("#filterCategory");
            ToggleGroup group = (ToggleGroup) tfFilter.getScene().lookup("#filterCategory");
            if (group == null) return true;
            RadioButton selectedRadio = (RadioButton) group.getSelectedToggle();
            if (selectedRadio == null) return true;

            if (selectedRadio.getText().equals("By ID")) {
                return String.valueOf(media.getId()).contains(filterText);
            } else {
                return media.getTitle().toLowerCase().contains(filterText.toLowerCase());
            }
        });
        tblMedia.setItems(filteredItems);
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeMedia(selected);
            totalCostLabel.setText(cart.totalCost() + " $");
        }
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
            try {
                selected.playGUI();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing: " + selected.getTitle());
                alert.setHeaderText("Now Playing: " + selected.getTitle());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cart is empty");
            alert.setHeaderText("Your cart is empty!");
            alert.showAndWait();
            return;
        }
        cart.placeOrder();
        totalCostLabel.setText("0.0 $");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Order placed successfully!");
        alert.showAndWait();
    }
}
