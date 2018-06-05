package event.trade.token.storage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
    private String code;
    private String name;
    private Float price;
    private Integer count;

    public Product(String code, String name, Float price, Integer count) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void reduceCount() {
        this.count--;
    }
}
