package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc {

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


}
