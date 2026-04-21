package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.*;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new Book("Java Programming", "Education", 29.99f));

        int choice;
        while (true) {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Xử lý dòng trống sau khi nhập số

            switch (choice) {
                case 1: viewStore(); break;
                case 2: updateStore(); break;
                case 3: viewCart(); break;
                case 0: System.out.println("End system."); return;
                default: System.out.println("Unvalid!");
            }
        }
    }

    //menu
    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    public static void viewStore() {
        store.displayItems();
        while (true) {
            storeMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) { // See media details
                System.out.print("Nhập tiêu đề Media: ");
                String title = scanner.nextLine();
                Media m = store.searchByTitle(title);
                if (m != null) {
                    System.out.println(m.toString());
                    handleMediaDetails(m);
                } else {
                    System.out.println("Không tìm thấy sản phẩm!");
                }
            } else if (choice == 2) {
            } else if (choice == 3) {
            } else if (choice == 4) {
                viewCart();
            } else if (choice == 0) break;
        }
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    public static void handleMediaDetails(Media media) {
        while (true) {
            mediaDetailsMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                cart.addMedia(media);
                System.out.println("Added");
            } else if (choice == 2 && media instanceof Playable) {
                ((Playable) media).play();
            } else if (choice == 0) break;
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

    public static void viewCart() {
        cart.printCart();
    }

    public static void updateStore() {
    }
}