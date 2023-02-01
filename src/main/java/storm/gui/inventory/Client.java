package storm.gui.inventory;

import storm.gui.inventory.entity.Product;
import storm.gui.inventory.enums.Operation;
import storm.gui.inventory.service.Input;
import storm.gui.inventory.service.ProductService;
import storm.gui.inventory.ui.TextUtils;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {

    private final ProductService service;
    private final Input in = new Input();

    public Client() {
        this.service = new ProductService();

            System.out.println(TextUtils.TITLE);
            System.out.println(TextUtils.MENU);
            System.out.println(TextUtils.OPTIONS);

            boolean running = true;

            while (running) {
                System.out.print(TextUtils.ASK);
                int option = handleOption();

                switch (option) {
                    case 1 -> listInventory();
                    case 2 -> addProducts();
                    case 3 -> updateProducts();
                    case 4 -> getProductDetails();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid Option");
                }
            }

    }

    private int handleOption() {
        try {
            return Integer.parseInt(in.read());
        }
        catch (NumberFormatException ex) { return 0; }
    }

    private void addProducts() {
        System.out.print("Enter product name: ");
        String name = in.read();

        System.out.print("Enter quantity: ");
        int qty = Integer.parseInt(in.read());

        System.out.print("Enter price: ");
        BigDecimal price = new BigDecimal(in.read());

        service.addProduct(new Product(name, qty, price));
        System.out.println("INFO - Product added!\n");
    }

    private void listInventory() {
        if (service.getInventorySize() > 0) {
            service.getInventory().forEach(System.out::println);
        } else {
            System.out.println("There are no products!\n");
        }
    }

    private void updateProducts() {
        System.out.print("Enter Product Name: ");
        String name = in.read();
        Product product = service.getProductByName(name);

        System.out.print("Enter new Qty");
        int qty = Integer.parseInt(in.read());

        System.out.println("Enter new product price");
        BigDecimal price = new BigDecimal(in.read());

        service.updateProductQty(product, qty, Operation.ADD);
    }

    private void getProductDetails() {
        System.out.print("Enter Product Name: ");
        String name = in.read();
        Product product = service.getProductByName(name);

        if (Objects.isNull(product) || Objects.isNull(product.getName())) {
            System.out.println("WARN - Product not Found");
            return;
        }

        String buffer = "Name: " + product.getName() +
                " Price: " + product.getPrice() +
                " Qty: " + product.getQuantity() +
                " Total Value " + product.getTotalPrice();
        System.out.println(buffer);


    }
}
