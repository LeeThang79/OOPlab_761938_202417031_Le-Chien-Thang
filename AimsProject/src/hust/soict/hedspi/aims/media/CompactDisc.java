package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.Iterator;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public String getArtist() {
        return artist;
    }

    public CompactDisc(String title, String category, String artist, String director, int length, float cost) {
        super(title,category,director,length,cost);
        this.artist = artist;
    }

    public void addTrack(Track trackName) {
        if(!tracks.contains(trackName)) {
            tracks.add(trackName);
            System.out.println("Added track " + trackName.getTitle() + " to the Disc");
        }
        else {
            System.out.println("Track " +trackName.getTitle() + " has already existed in the Disc");
        }
    }

    public void removeTrack (Track trackName) {
        if(tracks.contains(trackName)) {
            tracks.remove(trackName);
            System.out.println("Track " + trackName.getTitle() + " has been removed from the Disc");
        }
        else {
            System.out.println("Track " + trackName.getTitle() + " is not in the the Disc. Can't be removed.");
        }
    }

    public int getLength () {
        int totalLength = 0;
        for(Track track:tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            // Nếu đĩa xịn, thời lượng lớn hơn 0 thì cho phát
            System.out.println("Playing CD: " + this.getTitle());
            System.out.println("CD length: " + this.getLength());
            Iterator iter = tracks.iterator();
            Track nextTrack;
            while(iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    // Cố gắng phát từng bài hát
                    nextTrack.play();
                } catch(PlayerException e) {
                    // Nếu gặp bài hát bị lỗi, bắt lấy lỗi đó và ném ngược ra ngoài để giao diện xử lý
                    throw e;
                }
            }
        } else {
            // Nếu đĩa hỏng (thời lượng <= 0), ném ra "quả bom" PlayerException
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() +
                " - Artist: " + this.getArtist() + " - Director: " + this.getDirector() +
                " - Length: " + this.getLength() + ": " + this.getCost() + " $";
    }
}
