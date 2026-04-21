package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media mediaName) {
        if (!itemsOrdered.contains(mediaName)) {
            itemsOrdered.add(mediaName);
            System.out.println("Added " + mediaName.getTitle() + " to the Cart.");
        } else {
            System.out.println(mediaName.getTitle() + " has already been in the Cart. Can't be added.");
        }
    }

    public void removeMedia(Media mediaName) {
        if (itemsOrdered.contains(mediaName)) {
            itemsOrdered.remove(mediaName);
            System.out.println("Removed " + mediaName.getTitle() + " from the Cart.");
        } else {
            System.out.println("Couldn't find " + mediaName.getTitle() + " in the Cart. Can't be removed.");
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

