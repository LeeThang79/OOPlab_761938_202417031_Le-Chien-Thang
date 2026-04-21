package hust.soict.hedspi.aims.media;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isMatch(String title) {
        if (this.getTitle() == null || title == null) {
            return false;
        }
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public Media() {

    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
}
