package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media implements Comparator<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

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

    @Override
    public boolean equals(Object obj) {
        // Nếu cùng trỏ đến 1 vùng nhớ -> chắc chắn giống nhau
        if (this == obj) {
            return true;
        }
        // Kiểm tra an toàn: Nếu obj bị null hoặc không phải là Media -> loại luôn, tránh lỗi ClassCastException
        if (!(obj instanceof Media)) {
            return false;
        }

        // Ép kiểu an toàn về Media
        Media other = (Media) obj;

        // So sánh 2 tiêu chí: Title và Cost
        boolean isTitleEqual = (this.getTitle() != null && this.getTitle().equals(other.getTitle()));
        boolean isCostEqual = (this.getCost() == other.getCost());

        return isTitleEqual && isCostEqual;
    }

    public int compareTo(Media other) {
        // Chặn lỗi NullPointerException
        if (other == null) {
            throw new NullPointerException("ERROR: Cannot compare with a null object.");
        }

        // 1. So sánh theo Title (Xếp theo bảng chữ cái ABC)
        int titleComparison = this.getTitle().compareToIgnoreCase(other.getTitle());
        if (titleComparison != 0) {
            return titleComparison; // Nếu title khác nhau thì trả về kết quả luôn
        }

        // 2. Nếu Title giống hệt nhau, thì so sánh theo Cost (Giá)
        return Float.compare(this.getCost(), other.getCost());
    }
}
