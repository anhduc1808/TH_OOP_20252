package hust.soict.dsai.aims.test;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Track;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        // Test unique element for Media.equals() (same title => considered equal)
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "DVD Title", "DVD", "Director 1", 10, 5.0f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "DVD Title", "DVD", "Director 2", 12, 5.0f);


        cart.addMedia(dvd1);
        cart.addMedia(dvd2); 

        cart.print();
    }
}

