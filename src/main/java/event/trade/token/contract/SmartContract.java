package event.trade.token.contract;

import event.trade.token.storage.Contract;
import event.trade.token.storage.Product;
import event.trade.token.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class SmartContract {

    private Contract contract;

    public SmartContract(Contract contract) {
        this.contract = contract;
    }

    public void newClient(String client) {
        // TODO
    }

    public List<Product> getProducts(String eventCode) {
        // FIXME
        Product p1 = new Product("tshirt", "футболка", 10.0f, 50);
        Product p2 = new Product("label", "брелок", 1.0f, 200);
        Product p3 = new Product("hoody", "толстовка", 20.0f, 15);
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        return products;
    }

    public void buyProduct(String eventCode, String client, String productCode) {
        // TODO
    }
}
