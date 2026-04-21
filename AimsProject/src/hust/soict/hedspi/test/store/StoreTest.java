package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        // Tạo một cửa hàng mới
        Store store = new Store();

        // Tạo một vài đĩa DVD mới
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);

        // Test thêm đĩa vào cửa hàng
        System.out.println("--- Thêm đĩa vào cửa hàng ---");
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        // Test xóa đĩa khỏi cửa hàng
        System.out.println("\n--- Xóa đĩa khỏi cửa hàng ---");
        store.removeMedia(dvd2); // Xóa đĩa có trong cửa hàng
        store.removeMedia(dvd2); // Thử xóa lại đĩa vừa xóa xem code xử lý thế nào
    }
}
