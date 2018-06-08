package event.trade.token.storage;

public class Contract {
    private String name;
    private String address;

    public Contract() {}

    public Contract(String name, String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{name: " + this.name + ", address: " + this.address + "}";
    }
}
