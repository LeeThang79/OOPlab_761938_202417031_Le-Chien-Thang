package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 2, 5, 5));

        center.add(new JLabel("Title: ", SwingConstants.RIGHT));
        JTextField tfTitle = new JTextField();
        center.add(tfTitle);

        center.add(new JLabel("Category: ", SwingConstants.RIGHT));
        JTextField tfCategory = new JTextField();
        center.add(tfCategory);

        center.add(new JLabel("Cost: ", SwingConstants.RIGHT));
        JTextField tfCost = new JTextField();
        center.add(tfCost);

        JButton btnAdd = new JButton("Add Book");
        center.add(new JLabel()); // Ô trống đẩy nút sang phải
        center.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());

                Book book = new Book(title, category, cost);
                store.addMedia(book);

                JOptionPane.showMessageDialog(null, "Sách đã được thêm thành công!");

                tfTitle.setText(""); tfCategory.setText(""); tfCost.setText("");
            }
        });

        add(center, BorderLayout.CENTER);
        setTitle("Add Book to Store");
        setVisible(true);
    }
}