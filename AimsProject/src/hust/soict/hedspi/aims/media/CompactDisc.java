package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

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

    public void play() {
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD length: " + this.getLength());

        for (Track track : tracks) {
            track.play();
        }
    }
}
