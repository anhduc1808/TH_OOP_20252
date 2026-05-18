package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public ArrayList<Media> getItems() {
        return itemsOrdered;
    }


    public void addMedia(Media media) {
        if (media == null) return;
        if (itemsOrdered.contains(media)) {
            System.out.println("Item already in cart: " + media.getTitle());
            return;
        }
        itemsOrdered.add(media);
        System.out.println("The media has been added: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed: " + media.getTitle());
        } else {
            System.out.println("The media is not found in the cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("**************************CART***********************");
        System.out.println("Ordered Items:");

        ArrayList<Media> sorted = new ArrayList<Media>(itemsOrdered);
        sorted.sort((a, b) -> {
            int cmpTitle = a.getTitle().compareToIgnoreCase(b.getTitle());
            if (cmpTitle != 0) return cmpTitle;
            return Float.compare(b.getCost(), a.getCost());
        });


        for (Media m : sorted) {
            System.out.println(m);
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("*****************************************************");
    }


    public void searchByID(int id) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                System.out.println("Found " + m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no media were found that match the ID provided!");
        }
    }

    public void searchByTitle(String keyword) {
        boolean matchFound = false;
        for (Media m : itemsOrdered) {
            if (m.getTitle() != null && m.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found " + m);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Sorry, no media were found with \"" + keyword + "\" in the title!");
        }
    }

    public void searchByCategory(String category) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getCategory() != null && m.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Found " + m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no media matching the \"" + category + "\" category were found!");
        }
    }

    public void searchByPrice(float maxCost) {
        boolean matchFound = false;
        for (Media m : itemsOrdered) {
            if (m.getCost() <= maxCost) {
                System.out.println("Found " + m);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Sorry, no media were found that match the maximum cost provided!");
        }
    }

    public void searchByPrice(float minCost, float maxCost) {
        boolean matchFound = false;
        for (Media m : itemsOrdered) {
            if (m.getCost() >= minCost && m.getCost() <= maxCost) {
                System.out.println("Found " + m);
                matchFound = true;
            }
        }
        if (!matchFound) {
            System.out.println("Sorry, no media were found that match the cost range between your specified minimum and maximum!");
        }
    }
}
