package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<String>();

    public Book() {
        super();
    }

    public Book(int id, String title, String category, float costs) {
        super(id, title, category, costs);
    }

    public void addAuthor(String authorName) {
        if (authorName == null) return;
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Author already exists: " + authorName);
        }
    }

    public void removeAuthor(String authorName) {
        if (authorName == null) return;
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        } else {
            System.out.println("Author not found: " + authorName);
        }
    }

    @Override
    public String toString() {
        return "Book: " + getTitle() + " - Category: " + getCategory() + " - Authors: " + authors + " - Cost: " + getCost();
    }
}
