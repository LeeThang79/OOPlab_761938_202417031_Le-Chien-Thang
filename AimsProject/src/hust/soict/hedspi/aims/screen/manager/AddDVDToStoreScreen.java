package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDVDToStoreScreen extends AddItemToStoreScreen {

    public AddDVDToStoreScreen(Store store) {
        super(store);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(6, 2, 5, 5));

        center.add(new JLabel("Title: ", SwingConstants.RIGHT));
        JTextField tfTitle = new JTextField();
        center.add(tfTitle);

        center.add(new JLabel("Category: ", SwingConstants.RIGHT));
        JTextField tfCategory = new JTextField();
        center.add(tfCategory);

        center.add(new JLabel("Director: ", SwingConstants.RIGHT));
        JTextField tfDirector = new JTextField();
        center.add(tfDirector);

        center.add(new JLabel("Length: ", SwingConstants.RIGHT));
        JTextField tfLength = new JTextField();
        center.add(tfLength);

        center.add(new JLabel("Cost: ", SwingConstants.RIGHT));
        JTextField tfCost = new JTextField();
        center.add(tfCost);

        JButton btnAdd = new JButton("Add DVD");
        center.add(new JLabel());
        center.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                String director = tfDirector.getText();

                int length = Integer.parseInt(tfLength.getText());
                float cost = Float.parseFloat(tfCost.getText());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);

                JOptionPane.showMessageDialog(null, "DVD đã được thêm thành công!");

                tfTitle.setText(""); tfCategory.setText(""); tfDirector.setText("");
                tfLength.setText(""); tfCost.setText("");
            }
        });

        add(center, BorderLayout.CENTER);
        setTitle("Add DVD to Store");
        setVisible(true);
    }
}