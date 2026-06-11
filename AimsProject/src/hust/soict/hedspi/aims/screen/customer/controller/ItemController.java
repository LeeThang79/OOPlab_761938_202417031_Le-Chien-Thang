package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;

    private Media media;

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (cart != null && media != null) {
            try {
                // Thử thêm sản phẩm vào giỏ
                cart.addMedia(media);

                // Nếu thành công, hiện popup báo đã thêm
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cart Notification");
                alert.setHeaderText("Success");
                alert.setContentText(media.getTitle() + " has been added to your cart!");
                alert.showAndWait();

            } catch (LimitExceededException e) {
                // Nếu bắt được lỗi giỏ hàng đầy, hiện cảnh báo ĐỎ!
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cart Error");
                alert.setHeaderText("Cannot add media");
                alert.setContentText(e.getMessage()); // Sẽ hiện dòng chữ "ERROR: The number of media..."
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
    }

    private Cart cart;

    public ItemController(Cart cart) {
        this.cart = cart;
    }


    // Gọi để nạp dữ liệu của Media vào giao diện Item
    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");

        // Hiện nút Play
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            // Ẩn nút Play và căn giữa nút Add to Cart
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }
}