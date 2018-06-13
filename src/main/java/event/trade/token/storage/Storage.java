package event.trade.token.storage;

import event.trade.token.contract.SmartContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static Map<String, Event> events = new HashMap<>();
    private static Map<String, SmartContract> contracts = new HashMap<>();
    static {
        Event event = new Event("Хакатон blockchain lovers", "blockchain_lovers");
        Contract contract = new Contract("contract_blockchain_lovers", "<address>");
        save(event, contract);
    }

    public static void save(Event event, Contract contract) {
        Storage.events.put(event.getEventCode(), event);
        Storage.contracts.put(event.getEventCode(), new SmartContract(contract));
        System.out.println("Successfully saved event!");
    }

    public static Event getEvent(String eventCode) {
        return Storage.events.get(eventCode);
    }

    public static SmartContract getContract(String eventCode) {
        System.out.println("Getting smart contract for eventCode: " + eventCode);
        return Storage.contracts.get(eventCode);
    }
}
