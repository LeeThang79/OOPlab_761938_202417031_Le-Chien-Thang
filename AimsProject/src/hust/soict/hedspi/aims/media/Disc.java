package hust.soict.hedspi.aims.media;

public class Disc extends Media{
    private int length;
    private String director;

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public Disc() {
        super();
    }

    public Disc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }
}
