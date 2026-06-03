package hust.soict.hedspi.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        tfDisplay = new JTextField();
        tfDisplay.setHorizontalAlignment(JTextField.RIGHT);
        cp.add(tfDisplay, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4, 3));
        addButtons(panelButtons);
        cp.add(panelButtons, BorderLayout.CENTER);

        setTitle("Number Grid");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addButtons(JPanel panelButtons) {
        ButtonListener btnListener = new ButtonListener();

        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton("" + i);
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(btnListener);
        }

        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener);

        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);
    }

    public static void main(String[] args) {
        new NumberGrid();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            if (button.charAt(0) >= '0' && button.charAt(0) <= '9') {
                tfDisplay.setText(tfDisplay.getText() + button);
            } else if (button.equals("DEL")) {
                String current = tfDisplay.getText();
                if (current.length() > 0) {
                    tfDisplay.setText(current.substring(0, current.length() - 1));
                }
            } else if (button.equals("C")) {
                tfDisplay.setText("");
            }
        }
    }
}
