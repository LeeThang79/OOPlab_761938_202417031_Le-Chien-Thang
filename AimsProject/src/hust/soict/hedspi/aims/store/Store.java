package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class Store {
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[1000];
    private int qtyInStore = 0;

    public void addDVD (DigitalVideoDisc dvd) {
        if (qtyInStore < itemsInStore.length) {
            itemsInStore[qtyInStore] = dvd;
            qtyInStore++;
            System.out.println("System has added " + dvd.getTitle() + " in the store");
        }
        else{
            System.out.println("Store has been full, can't add any more disc");
        }
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        boolean found = false;
        for(int i = 0; i < qtyInStore; i++) {
            if(itemsInStore[i]==dvd) {
                found = true;
                for(int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j+1];
                }
                itemsInStore[qtyInStore-1] = null;
                qtyInStore--;
                System.out.println("System has removed " + dvd.getTitle() + " from the store");
            }
        }
        if(!found) {
            System.out.println("No disc has been found");
        }
    }
}
