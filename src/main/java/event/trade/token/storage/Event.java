package event.trade.token.storage;

public class Event {

    private String eventName;
    private String eventCode;

    public Event() {}

    public Event(String eventName, String eventCode) {
        this.eventName = eventName;
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    @Override
    public String toString() {
        return "{eventName: " + this.eventName + ", eventCode: " + this.eventCode + "}";
    }
}
