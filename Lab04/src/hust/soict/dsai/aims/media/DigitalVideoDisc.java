package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc() {
        super();
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, cost, length, director);
    }

    @Override
    public String toString() {
        return getId() + ". DVD: " + getTitle() + " - Category: " + getCategory() + " - Director: " + getDirector() + " - DVD length: " + getLength() + " - Cost: " + getCost() + "$";
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play DVD: " + getTitle() + " (invalid length: " + getLength() + ")");
            return;
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength());
    }
}
