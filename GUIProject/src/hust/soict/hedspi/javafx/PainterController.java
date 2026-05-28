package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    // Khai báo 2 nút RadioButton
    @FXML
    private RadioButton penRadio;

    @FXML
    private RadioButton eraserRadio;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Tạo biến lưu màu mực, mặc định là màu đen
        Color inkColor = Color.BLACK;

        // Kiểm tra xem nút nào đang được chọn để đổi màu mực
        if (eraserRadio.isSelected()) {
            inkColor = Color.WHITE; // Nếu chọn tẩy thì dùng màu trắng
        } else {
            inkColor = Color.BLACK; // Nếu chọn bút thì dùng màu đen
        }

        // Vẽ hình tròn với màu mực đã được quyết định ở trên
        Circle newCircle = new Circle(event.getX(), event.getY(), 4, inkColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}