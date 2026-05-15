package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) return;
        if (itemsInStore.contains(media)) {
            System.out.println("Item already in store: " + media.getTitle());
            return;
        }
        itemsInStore.add(media);
        System.out.println("The media has been added to store: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (itemsInStore.remove(media)) {
            System.out.println("The media has been removed from store: " + media.getTitle());
        } else {
            System.out.println("The media is not found in the store.");
        }
    }

    public void print() {
        System.out.println("================== STORE ITEMS ==================");
        for (Media m : itemsInStore) {
            System.out.println(m);
        }
        System.out.println("=================================================");
    }
}
