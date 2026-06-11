package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.*;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    public static final int MAX_NUMBERS_ORDERED = 20;

    public Cart() {
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void addMedia(Media mediaName) throws LimitExceededException{
        // Kiểm tra xem số lượng đã vượt quá giới hạn chưa
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            if (!itemsOrdered.contains(mediaName)) {
                itemsOrdered.add(mediaName);
                System.out.println("Added " + mediaName.getTitle() + " to the Cart.");
            } else {
                System.out.println(mediaName.getTitle() + " is already in the Cart.");
            }
        } else {
            // Nếu giỏ hàng đã đạt giới hạn, ném ra ngoại lệ
            throw new LimitExceededException("ERROR: The number of media has reached its limit");
        }
    }

    public void removeMedia(Media mediaName) {
        if (itemsOrdered.contains(mediaName)) {
            itemsOrdered.remove(mediaName);
            System.out.println(mediaName.getTitle() + " has been removed from the cart.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }
    public void searchByTitle(String keyword) {
        boolean matchFound = false;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).isMatch(keyword)) {
                System.out.println("Found: " + itemsOrdered.get(i).toString());
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("No disc is found with title: " + keyword);
        }
    }
}

