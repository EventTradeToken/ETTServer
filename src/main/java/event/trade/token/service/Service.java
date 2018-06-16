package event.trade.token.service;

import event.trade.token.contract.SmartContract;
import event.trade.token.storage.Contract;
import event.trade.token.storage.Event;
import event.trade.token.storage.Product;
import event.trade.token.storage.Storage;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;

@Path("/event")
public class Service {

    @GET
    @Path("/hello/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        System.out.println("------> service /event/hello/" + msg);
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/new")
    @Consumes("application/json")
    public Response newEvent(NewEventRequest body) {
        System.out.println("------> service /event/new: " + body.toString());
        Storage.save(body.getEvent(), body.getContract());
        return Response.status(200).build();
    }

    @POST
    @Path("/test")
    @Consumes("application/json")
    public Response testEvent(Event body) {
        System.out.println("------> service /event/test: " + body.toString());
        return Response.status(200).build();
    }

    @GET
    @Path("/file")
    public Response file() {
        try {
            System.getProperties().list(System.out);
            StreamingOutput stream = new StreamingOutput() {
                @Override
                public void write(OutputStream os) throws IOException, WebApplicationException {
                    PrintStream print = new PrintStream(os);
                    System.getProperties().list(print);
                    print.flush();
                }
            };
            return Response.ok(stream).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/properties")
    public Response properties() {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("walletSource: ");
            sb.append(System.getProperty("walletSource").toString());
            sb.append(", ");
            sb.append("walletPassword: ");
            sb.append(System.getProperty("walletPassword").toString());
            return Response.ok(sb.toString()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).build();
        }
    }

    @GET
    @Path("{eventCode}/newclient/{client}")
    public Response newClient(@PathParam("eventCode") String eventCode, @PathParam("client") String client) {
        try {
            System.out.println("Service: new client '" + client + "' for event with code: " + eventCode);
            SmartContract contract = Storage.getContract(eventCode);
            System.out.println("Smart contract address: " + contract.getAddress());
            contract.newClient(client);
            return Response.status(200).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("{eventCode}/products")
    @Produces("application/json")
    public Response getProducts(@PathParam("eventCode") String eventCode) {
        try {
            List<Product> products = Storage.getContract(eventCode).getProducts();
            GenericEntity<List<Product>> entities = new GenericEntity<List<Product>>(products) {
            };
            String output = new JSONArray(products).toString();
            return Response.status(200).entity(output).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).entity(e).build();
        }
    }

    @GET
    @Path("{eventCode}/client/{client}/buyproduct/{productCode}")
    public Response buyProduct(@PathParam("eventCode") String eventCode, @PathParam("client") String client, @PathParam("productCode") String productCode) {
        try {
            Storage.getContract(eventCode).buyProduct(client, Integer.parseInt(productCode));
            return Response.status(200).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500).entity(e).build();
        }
    }
}

class NewEventRequest {
    private Event event;
    private Contract contract;

    public NewEventRequest() {
    }

    public NewEventRequest(Event event, Contract contract) {
        this.event = event;
        this.contract = contract;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "{event: " + this.event.toString() + ", contract: " + this.contract.toString() + "}";
    }
}
