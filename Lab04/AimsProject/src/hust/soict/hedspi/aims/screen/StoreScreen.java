package hust.soict.hedspi.aims.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void openCartScreen() {
        CartScreen cartScreen = new CartScreen(cart, store);
        cartScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BorderLayout());
        north.add(createMenuBar(), BorderLayout.NORTH);
        north.add(createHeader(), BorderLayout.CENTER);
        return north;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> new AddBookToStoreScreen(store));
        smUpdateStore.add(addBookItem);
        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store));
        smUpdateStore.add(addCDItem);
        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        JMenuItem viewCartItem = new JMenuItem("View cart");
        viewCartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCartScreen();
            }
        });
        menu.add(viewCartItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("All media items are here!");
        subtitle.setFont(new Font(subtitle.getFont().getName(), Font.PLAIN, 25));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        header.add(title);
        header.add(subtitle);

        return header;
    }

    private JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media, cart);
            center.add(cell);
        }

        return center;
    }
}
