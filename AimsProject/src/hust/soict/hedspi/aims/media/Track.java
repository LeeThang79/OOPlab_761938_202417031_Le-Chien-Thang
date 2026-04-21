package hust.soict.hedspi.aims.media;

public class Track {
    private int length;
    private String title;

    public Track() {
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }
}
