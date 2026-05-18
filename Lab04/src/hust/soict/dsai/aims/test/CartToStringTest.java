package hust.soict.dsai.aims.test;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class CartToStringTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        Book book = new Book(1, "Java Basics", "Book", 25.0f);
        book.addAuthor("James Gosling");
        book.addAuthor("Joshua Bloch");

        Track track1 = new Track("Track 1", 180);
        Track track2 = new Track("Track 2", 210);

        CompactDisc cd = new CompactDisc(2, "Best Hits", "Music", "Artist A", "Director A", 30.0f);
        cd.addTrack(track1);
        cd.addTrack(track2);

        DigitalVideoDisc dvd = new DigitalVideoDisc(3, "Tutorial DVD", "DVD", "Director DVD", 120, 40.0f);

        cart.addMedia(book);
        cart.addMedia(cd);
        cart.addMedia(dvd);

        cart.print();
    }
}

