package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {

    private List<String> authors = new ArrayList<>();

    public void addAuthor(String name) {
        if (!authors.contains(name)) {
            authors.add(name);
            System.out.println("Added author: " + name);
        } else {
            System.out.println("Author " + name + " has already been in the list");
        }
    }
    public void removeAuthor(String name) {
        if (authors.contains(name)) {
            authors.remove(name);
            System.out.println("Deleted author: " + name);
        } else {
            System.out.println("Can't found author " + name + " to delete");
        }
    }
    public Book() {}

    public Book(String title, String category, float cost) {
        // Dùng lệnh super để truyền 3 thông tin này lên cho lớp cha Media xử lý
        super(title, category, cost);
    }

    @Override
    public String toString() {
        return "Book - " + this.getTitle() + " - " + this.getCategory() +
                " - Authors: " + String.join(", ", authors) + ": " + this.getCost() + " $";
    }
}
