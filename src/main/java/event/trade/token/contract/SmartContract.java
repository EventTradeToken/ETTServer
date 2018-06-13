package event.trade.token.contract;

import api.ContractAPI;
import api.EventTradeToken;
import event.trade.token.storage.Contract;
import event.trade.token.storage.Product;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SmartContract {

    private EventTradeToken ett;

    public SmartContract(Contract contract) {
        ContractAPI contractAPI = new ContractAPI();
        this.ett = contractAPI.getContract(contract.getAddress());
        System.out.println("Smart contract with address " + contract.getAddress() + ", Event Trade Token: " + this.ett);
    }

    public void newClient(String client) throws Exception {
        this.ett.newClient(client).send();
    }

    public List<Product> getProducts() throws Exception {
        System.out.println("Get products...");
        BigInteger count = this.ett.getProductsCount().send();
        System.out.println("Count of products: " + count);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count.intValue(); i++) {
            Tuple4<BigInteger, String, BigInteger, BigInteger> p = this.ett.getProductByIndex(BigInteger.valueOf(i)).send();
            Product product = new Product(p.getValue1().intValue(), p.getValue2(), (float)p.getValue3().intValue(), p.getValue4().intValue());
            products.add(product);
        }
        System.out.println("Got " + products.size() + " products from contract");
        return products;
    }

    public void buyProduct(String client, Integer productCode) throws Exception {
        this.ett.buyProduct(client, BigInteger.valueOf(productCode)).send();
    }
}
