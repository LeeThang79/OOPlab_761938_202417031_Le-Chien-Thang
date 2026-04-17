package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String category;
    private float cost;
    private List<String> authors = new ArrayList<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

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
}
