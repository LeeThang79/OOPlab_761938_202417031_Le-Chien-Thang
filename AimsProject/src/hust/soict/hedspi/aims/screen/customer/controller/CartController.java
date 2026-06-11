package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.beans.value.*;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {

    private Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        // Lấy sản phẩm đang được chọn
        Media media = tblMedia.getSelectionModel().getSelectedItem();

        // Kiểm tra xem sản phẩm có phát được không
        if (media instanceof Playable) {
            // Tạo một cửa sổ thông báo (Alert) để giả lập việc Play trên GUI
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText("Now playing: " + media.getTitle());
            alert.setContentText("Enjoy the show!");

            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();

        // Xóa sản phẩm đó khỏi danh sách của Giỏ hàng (cart)
        if (media != null) {
            cart.removeMedia(media);
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
    }

    @FXML
    public void initialize() {
        // Cài đặt cách cột lấy dữ liệu từ các thuộc tính của đối tượng Media
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        // Nạp danh sách ObservableList từ Cart vào TableView
        if (cart.getItemsOrdered() != null) {
            tblMedia.setItems(cart.getItemsOrdered());
        }

        // Ẩn 2 nút Play và Remove lúc ban đầu khi chưa chọn gì cả
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        // Gắn Listener để theo dõi sự kiện người dùng click chọn hàng trong bảng
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue); // Gọi hàm cập nhật nút bên dưới
                        }
                    }
                }
        );

        cart.getItemsOrdered().addListener(new ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> c) {
                // Giả sử hàm tính tổng tiền của bạn trong class Cart là totalCost()
                // Cập nhật lại nhãn text bằng tổng tiền mới
                costLabel.setText(cart.totalCost() + " $");
            }
        });
    }

    // Hàm phụ trợ để ẩn/hiện nút dựa trên loại Media được chọn
    private void updateButtonBar(Media media) {
        // Đã chọn một sản phẩm thì chắc chắn cho phép Remove
        btnRemove.setVisible(true);

        // Nếu sản phẩm đó phát được (CD/DVD), thì hiện nút Play. Nếu là Sách thì ẩn.
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }
}