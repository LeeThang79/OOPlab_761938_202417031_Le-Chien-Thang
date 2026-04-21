package hust.soict.hedspi.aims.media;

public class Track implements Playable{
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

    @Override
    public void play() {
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }

        Track track = (Track) obj;

        return this.getTitle() != null &&
                this.getTitle().equals(track.getTitle()) &&
                this.getLength() == track.getLength();
    }
}
