package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;
    @FXML
    private ToggleGroup tools;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Circle circle = new Circle(event.getX(), event.getY(), 4);
        RadioButton selected = (RadioButton) tools.getSelectedToggle();
        if (selected != null && selected.getText().equals("Eraser")) {
            circle.setFill(Color.WHITE);
        } else {
            circle.setFill(Color.BLACK);
        }
        drawingAreaPane.getChildren().add(circle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}
