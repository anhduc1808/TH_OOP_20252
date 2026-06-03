package hust.soict.hedspi.swing;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    public AWTAccumulator() {
        setLayout(new GridLayout(3, 1));

        add(new Label("Enter Value:"));

        tfInput = new TextField(10);
        add(tfInput);
        tfInput.addActionListener(new TFInputListener());

        add(new Label("Accumulated Sum:"));

        tfOutput = new TextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);

        setTitle("AWT Accumulator");
        setSize(350, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }

    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int number = Integer.parseInt(tfInput.getText());
            sum += number;
            tfInput.setText("");
            tfOutput.setText(sum + "");
        }
    }
}
