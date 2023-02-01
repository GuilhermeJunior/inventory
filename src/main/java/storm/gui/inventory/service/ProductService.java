package storm.gui.inventory.service;

import storm.gui.inventory.dao.InventoryDao;
import storm.gui.inventory.entity.Product;
import storm.gui.inventory.enums.Operation;

import java.util.List;

public class ProductService {

    private final InventoryDao inventoryDao;

    public ProductService() {
        this.inventoryDao = new InventoryDao();
    }

    public void addProduct(Product product) {
        inventoryDao.addToInventory(product);
    }

    public List<Product> getInventory() {
        return inventoryDao.getInventory();
    }

    public Integer getInventorySize() {
        return inventoryDao.getInventoryQty();
    }

    public void updateProductQty(Product product, final Integer qty, Operation op) {
        if (Operation.ADD.equals(op)) {
            product.setQuantity(product.getQuantity() + qty);
            inventoryDao.updateProduct(product);
            return;
        }
        // TODO - Check if the product has the quantity to prevent negative numbers
        product.setQuantity(product.getQuantity() - qty);
    }

    public Product getProductByName(String name) {
        return inventoryDao.getProductByName(name);
    }
}
