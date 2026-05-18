package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Disc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aims {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearConsole();

        Store store = new Store();
        Cart cart = new Cart();

        // Seed store with sample media (CD-Track, DVD, Book)
        // DVD
        store.addMedia(new DigitalVideoDisc(1, "DSA DVD", "DVD", "Director 1", 120, 15.0f));
        store.addMedia(new DigitalVideoDisc(2, "Java DVD", "DVD", "Director 2", 90, 20.0f));

        // CD
        CompactDisc cd = new CompactDisc(3, "CD Music", "Music", "Artist A", "Director A", 30.0f);
        cd.addTrack(new Track("Track 1", 180));
        cd.addTrack(new Track("Track 2", 210));
        store.addMedia(cd);

        // Book
        Book book = new Book(4, "Effective Java", "Book", 25.0f);
        book.addAuthor("Joshua Bloch");
        store.addMedia(book);

        showMainMenu();
        boolean running = true;
        while (running) {
            int choice = readInt("Please choose a number: ");
            switch (choice) {
                case 1:
                    viewStoreMenu(store, cart);
                    break;
                case 2:
                    updateStore(store);
                    break;
                case 3:
                    cartMenu(store, cart);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            if (running) {
                showMainMenu();
            }
        }
        scanner.close();
    }

    public static void clearConsole() {
        System.out.println();
    }

    public static void showMainMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    private static void showStoreMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    private static void showMediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    private static void showCartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    private static void viewStoreMenu(Store store, Cart cart) {
        clearConsole();
        store.print();

        boolean back = false;
        while (!back) {
            showStoreMenu();
            int choice = readInt(" ");
            switch (choice) {
                case 1:
                    seeMediaDetails(store, cart);
                    break;
                case 2:
                    addMediaToCart(store, cart);
                    break;
                case 3:
                    playMediaFromStore(store);
                    break;
                case 4:
                    cart.print();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            if (!back) {
                clearConsole();
                store.print();
            }
        }
    }

    private static void updateStore(Store store) {
        boolean back = false;
        while (!back) {
            System.out.println("Update store options:");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            int choice = readInt("Please choose: ");
            switch (choice) {
                case 1:
                    Media newMedia = createMediaFromUser();
                    store.addMedia(newMedia);
                    break;
                case 2:
                    String title = readString("Enter title to remove: ");
                    Media toRemove = findMediaByTitle(store, title);
                    if (toRemove != null) {
                        store.removeMedia(toRemove);
                    } else {
                        System.out.println("Media not found!");
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void cartMenu(Store store, Cart cart) {
        boolean back = false;
        while (!back) {
            clearConsole();
            cart.print();
            showCartMenu();
            int choice = readInt(" ");
            switch (choice) {
                case 1:
                    filterMediasInCart(cart);
                    break;
                case 2:
                    // Sorting is handled by Cart.print() in this implementation.
                    // We just re-print.
                    break;
                case 3:
                    removeMediaFromCart(cart);
                    break;
                case 4:
                    playMediaFromCart(cart);
                    break;
                case 5:
                    System.out.println("Order created! Emptying current cart...");
                    clearCart(cart);
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void seeMediaDetails(Store store, Cart cart) {
        String title = readString("Enter media title: ");
        Media media = findMediaByTitle(store, title);
        if (media == null) {
            System.out.println("Sorry, no media were found with \"" + title + "\" in the title!");
            return;
        }

        System.out.println(media);
        showMediaDetailsMenu();

        int choice = readInt(" ");
        switch (choice) {
            case 1:
                cart.addMedia(media);
                break;
            case 2:
                playSpecificMedia(media);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void addMediaToCart(Store store, Cart cart) {
        String title = readString("Enter media title to add: ");
        Media media = findMediaByTitle(store, title);
        if (media == null) {
            System.out.println("Sorry, no media were found with \"" + title + "\" in the title!");
            return;
        }
        cart.addMedia(media);
    }

    private static void playMediaFromStore(Store store) {
        String title = readString("Enter media title to play: ");
        Media media = findMediaByTitle(store, title);
        if (media == null) {
            System.out.println("Sorry, no media were found with \"" + title + "\" in the title!");
            return;
        }
        playSpecificMedia(media);
    }

    private static void playMediaFromCart(Cart cart) {
        String title = readString("Enter media title to play: ");
        Media media = null;
        for (Media m : cart.getItems()) {
            if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(title)) {
                media = m;
                break;
            }
        }
        if (media == null) {
            System.out.println("Media not found in cart!");
            return;
        }
        playSpecificMedia(media);
    }

    private static void filterMediasInCart(Cart cart) {
        System.out.println("Filter options:");
        System.out.println("1. Filter by id");
        System.out.println("2. Filter by title");
        int choice = readInt("Please choose: ");
        switch (choice) {
            case 1:
                int id = readInt("Enter id: ");
                cart.searchByID(id);
                break;
            case 2:
                String keyword = readString("Enter title keyword: ");
                cart.searchByTitle(keyword);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void removeMediaFromCart(Cart cart) {
        String title = readString("Enter title to remove from cart: ");
        Media target = null;
        for (Media m : cart.getItems()) {
            if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(title)) {
                target = m;
                break;
            }
        }
        if (target == null) {
            System.out.println("Media not found in cart!");
            return;
        }
        cart.removeMedia(target);
    }

    private static void playSpecificMedia(Media media) {
        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void clearCart(Cart cart) {
        cart.getItems().clear();
    }

    private static Media findMediaByTitle(Store store, String title) {
       
        return null;
    }

    private static Media createMediaFromUser() {
        int type = readInt("Choose media type: 1) Book 2) CD 3) DVD : ");
        String title = readString("Title: ");
        String category = readString("Category: ");
        float cost = readFloat("Cost: ");
        int id = readInt("Id: ");

        switch (type) {
            case 1: {
                Book b = new Book(id, title, category, cost);
                String author;
                do {
                    author = readString("Author name (empty to stop): ");
                    if (author != null && !author.isEmpty()) b.addAuthor(author);
                } while (author != null && !author.isEmpty());
                return b;
            }
            case 2: {
                String artist = readString("Artist: ");
                String director = readString("Director: ");
                CompactDisc cd = new CompactDisc(id, title, category, artist, director, cost);
                int n = readInt("How many tracks? ");
                for (int i = 0; i < n; i++) {
                    String trackTitle = readString("  Track " + (i + 1) + " title: ");
                    int len = readInt("  Track " + (i + 1) + " length: ");
                    cd.addTrack(new Track(trackTitle, len));
                }
                return cd;
            }
            case 3: {
                String director = readString("Director: ");
                int length = readInt("DVD length: ");
                return new DigitalVideoDisc(id, title, category, director, length, cost);
            }
            default:
                return new Book(id, title, category, cost);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid float.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}

