package storm.gui.inventory.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storm.gui.inventory.entity.Product;
import storm.gui.inventory.enums.Operation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
         this.productService = new ProductService();
    }

    @Test
    void shouldAddItemsToInventory() {
        // Given
        Product p1 = new Product("Smartphone", 1, BigDecimal.valueOf(150.50));
        Product p2 = new Product("Smartphone Xaomi", 4, BigDecimal.valueOf(250.50));
        Product p3 = new Product("Smartphone", 1, BigDecimal.valueOf(150.50));

        // When
        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);

        // Then
        assertEquals(2, productService.getInventorySize());

        //When
        productService.updateProductQty(p2, 4, Operation.ADD);

        // Then
        assertEquals(8, productService.getProductByName(p2.getName())
                .getQuantity());

        //When
        productService.updateProductQty(p1, 1, Operation.SUB);

        assertEquals(0, productService.getProductByName(p1.getName())
                .getQuantity());
    }

}