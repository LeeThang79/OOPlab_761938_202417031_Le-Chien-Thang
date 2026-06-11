package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewStoreController {

    @FXML
    private GridPane gridPane;

    private Store store;
    private Cart cart;

    // Cập nhật constructor để nhận cả Store và Cart
    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            // Nạp file giao diện Cart.fxml
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));

            // Thiết lập controller và truyền store, cart sang
            fxmlLoader.setController(new CartController(store, cart));
            Parent root = fxmlLoader.load();

            // Lấy Stage hiện tại
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

            // Chuyển Scene
            stage.setScene(new Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Đường dẫn tới file Item.fxml của bạn
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        // Duyệt qua danh sách các Media có trong Cửa hàng
        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));

                // Khởi tạo ItemController và truyền giỏ hàng vào
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);

                // Tải giao diện của một Item lên
                AnchorPane anchorPane = fxmlLoader.load();

                // Nạp dữ liệu thực tế cho Item đó
                itemController.setData(store.getItemsInStore().get(i));

                // Thuật toán để sắp xếp các sản phẩm thành dạng lưới (Grid) 3 cột
                if (column == 3) {
                    column = 0;
                    row++;
                }

                // Thêm sản phẩm vào lưới và chỉnh lề
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}