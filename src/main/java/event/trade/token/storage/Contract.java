package event.trade.token.storage;

public class Contract {
    private String name;
    private String address;
    private String tokenCode;

    public Contract(String name, String address, String tokenCode) {
        this.name = name;
        this.address = address;
        this.tokenCode = tokenCode;
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

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }
}
