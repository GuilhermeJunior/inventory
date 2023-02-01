package storm.gui.inventory.dao;

import storm.gui.inventory.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryDao {

    private final List<Product> inventory;

    public InventoryDao() {
        this.inventory = new ArrayList<>();
    }

    public List<Product> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public Integer getInventoryQty() {
        return inventory.size();
    }

    public void addToInventory(Product product) {
        if(existsInInventory(product)) {
            System.out.println("WARN - Product already added to Inventory");
            return;
        }
        inventory.add(product);

    }

    public void updateProduct(Product product) {
       final var index = getIndex(product);

       if (index < 0) {
           System.out.println("WARN - Product does not exist in Inventory");
           return;
       }

       inventory.set(index, product);
    }

    private int getIndex(Product product) {
        for(int index = 0; index < inventory.size(); index++) {
            if (inventory.get(index).equals(product)) {
                return index;
            }
        }
        return -1;
    }

    private boolean existsInInventory(Product product) {
        for(Product p : inventory) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String name) {
        var indexByName = getIndexByName(name);

        if (indexByName < 0) {
            return null;
        }

        return inventory.get(indexByName);

    }

    private int getIndexByName(String name) {
        for(int index = 0; index < inventory.size(); index++) {
            if (inventory.get(index).getName().equalsIgnoreCase(name)) {
                return index;
            }
        }
        return -1;
    }
}
