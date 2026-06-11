package hust.soict.hedspi.aims.exception;

public class PlayerException extends Exception {
    public PlayerException(String message) {
        super(message); // Kế thừa hàm ghi nhận thông báo lỗi của class Exception gốc
    }
}