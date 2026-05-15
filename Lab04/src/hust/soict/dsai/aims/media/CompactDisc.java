package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(int id, String title, String category, String artist, String director, float cost) {
        super(id, title, category, cost, 0, director);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addTrack(Track t) {
        if (t == null) return;
        if (tracks.contains(t)) {
            System.out.println("Track already exists: " + t.getTitle());
        } else {
            tracks.add(t);
            System.out.println("Track added: " + t.getTitle());
        }
    }

    public void removeTrack(Track t) {
        if (t == null) return;
        if (tracks.remove(t)) {
            System.out.println("Track removed: " + t.getTitle());
        } else {
            System.out.println("Track not found: " + t.getTitle());
        }
    }

    public int getLength() {
        int sum = 0;
        for (Track t : tracks) sum += t.getLength();
        return sum;
    }

    @Override
    public void play() {
        int cdLength = getLength();
        if (cdLength <= 0) {
            System.out.println("Cannot play CD: " + getTitle() + " (invalid length: " + cdLength + ")");
            return;
        }
        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD length: " + cdLength);
        for (Track t : tracks) {
            t.play();
        }
    }

    @Override
    public String toString() {
        return "CD: " + getTitle() + " - Category: " + getCategory() + " - Artist: " + artist + " - Length: " + getLength() + " - Cost: " + getCost();
    }
}
