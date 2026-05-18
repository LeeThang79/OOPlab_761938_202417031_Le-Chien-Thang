package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;


public class StoreManagerScreen extends JFrame {
    private Store store;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreMenu = new JMenuItem("View store");
        menu.add(viewStoreMenu);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookMenu = new JMenuItem("Add Book");
        JMenuItem addCdMenu = new JMenuItem("Add CD");
        JMenuItem addDvdMenu = new JMenuItem("Add DVD");

        smUpdateStore.add(addBookMenu);
        smUpdateStore.add(addCdMenu);
        smUpdateStore.add(addDvdMenu);
        menu.add(smUpdateStore);

        addBookMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddBookToStoreScreen(store);
            }
        });

        addCdMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddCompactDiscToStoreScreen(store);
            }
        });

        addDvdMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddDVDToStoreScreen(store);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout (header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground (Color.CYAN);

        header.add(Box.createRigidArea (new Dimension (10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea (new Dimension (10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout (3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i=0; i<9; i++) {
            MediaStore cell = new MediaStore (mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    public StoreManagerScreen (Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
