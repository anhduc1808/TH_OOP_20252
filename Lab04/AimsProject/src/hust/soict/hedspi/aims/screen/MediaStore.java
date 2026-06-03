package hust.soict.hedspi.aims.screen;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.add(title);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);
        this.add(cost);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(e -> {
            cart.addMedia(media);
        });
        buttonPanel.add(addToCart);

        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(e -> {
                media.playGUI();

                JDialog playDialog = new JDialog();
                playDialog.setTitle("Playing: " + media.getTitle());
                playDialog.setSize(400, 150);
                playDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                JLabel playingLabel = new JLabel("Now Playing: " + media.getTitle());
                playingLabel.setAlignmentX(CENTER_ALIGNMENT);
                content.add(playingLabel);

                JButton close = new JButton("OK");
                close.setAlignmentX(CENTER_ALIGNMENT);
                close.addActionListener(ev -> playDialog.dispose());
                content.add(close);

                playDialog.add(content);
                playDialog.setVisible(true);
            });
            buttonPanel.add(play);
        }

        this.add(buttonPanel);
    }
}
