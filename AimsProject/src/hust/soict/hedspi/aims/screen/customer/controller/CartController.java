package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.*;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    private Cart cart;
    private Store store;

    public CartController(Store store, Cart cart) {
        this.store = store;
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
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                // Thử phát đĩa
                ((Playable) media).play();

                // Nếu không có lỗi gì, hiện popup bình thường
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing: " + media.getTitle());
                alert.showAndWait();

            } catch (PlayerException e) {
                // Nếu bắt được quả bom lỗi, bật cảnh báo đỏ!
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText("Media cannot be played");
                alert.setContentText(e.getMessage()); // Hiển thị dòng "ERROR: DVD length is..."
                alert.showAndWait();
            }
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
        try {
            // Nạp file giao diện Store.fxml
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));

            // Thiết lập controller và truyền lại store, cart
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();

            // Lấy Stage (cửa sổ) hiện tại từ sự kiện nhấp chuột
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

            // Chuyển Scene
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Cart is empty");
            alert.setContentText("Please add some media to your cart before placing an order.");
            alert.showAndWait();
        } else {
            // Thông báo đặt hàng thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Notification");
            alert.setHeaderText("Order Placed");
            alert.setContentText("Your order has been placed successfully! Total cost: " + cart.totalCost() + " $");
            alert.showAndWait();

            // Xóa sạch giỏ hàng sau khi mua (bạn cần đảm bảo class Cart có hàm clear() cho ObservableList)
            cart.getItemsOrdered().clear();
        }
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

        // Khởi tạo một danh sách lọc (FilteredList) bọc lấy danh sách gốc của giỏ hàng
        FilteredList<Media> filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true);

        // Lắng nghe mỗi khi bạn gõ một chữ cái vào thanh tìm kiếm (tfFilter)
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(media -> {
                // Nếu ô tìm kiếm trống, hiển thị toàn bộ sản phẩm
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Ép chữ người dùng gõ thành chữ thường để dễ so sánh
                String lowerCaseFilter = newValue.toLowerCase();

                // Nếu người dùng đang chọn tìm theo ID
                if (radioBtnFilterId.isSelected()) {
                    // Kiểm tra xem ID của sản phẩm có chứa số người dùng gõ không
                    return String.valueOf(media.getId()).contains(lowerCaseFilter);
                }
                // Nếu người dùng đang chọn tìm theo Tiêu đề (Title)
                else if (radioBtnFilterTitle.isSelected()) {
                    // Kiểm tra xem Tiêu đề có chứa chữ người dùng gõ không
                    if (media.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                return false; // Không khớp thì ẩn đi
            });
        });

        tblMedia.setItems(filteredData);
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