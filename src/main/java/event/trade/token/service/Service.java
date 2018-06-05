package event.trade.token.service;

import event.trade.token.contract.SmartContract;
import event.trade.token.storage.Contract;
import event.trade.token.storage.Product;
import event.trade.token.storage.Storage;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.List;

@Path("/event")
public class Service {

    @GET
    @Path("/hello/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("{eventCode}/newclient/{client}")
    public Response newClient(@PathParam("eventCode") String eventCode, @PathParam("client") String client) {
        Storage.getContract(eventCode).newClient(client);
        return Response.status(200).build();
    }

    @GET
    @Path("{eventCode}/products")
    @Produces("application/json")
    public Response getProducts(@PathParam("eventCode") String eventCode) {
        List<Product> products = Storage.getContract(eventCode).getProducts(eventCode);
        GenericEntity<List<Product>> entities = new GenericEntity<List<Product>>(products){};
        String output = new JSONArray(products).toString();
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("{eventCode}/client/{client}/buyproduct/{productCode}")
    public Response buyProduct(@PathParam("eventCode") String eventCode, @PathParam("client") String client, @PathParam("productCode") String productCode) {
        Storage.getContract(eventCode).buyProduct(eventCode, client, productCode);
        return Response.status(200).build();
    }
}
