package cat.xojan.rhinoexample;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represents a sale/order from any business.
 */
public class Sale {

    private final String id = "0gk238akdf";
    private final Date date = Calendar.getInstance().getTime();
    private final String price = "3.50 €";
    private final List<Product> products = initProducts();

    private List<Product> initProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Product A", "1.50 €"));
        list.add(new Product("Product B", "2.00 €"));

        return list;
    }

    public Date getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}
