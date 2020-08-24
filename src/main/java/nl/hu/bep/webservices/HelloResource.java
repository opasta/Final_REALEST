package nl.hu.bep.webservices;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class HelloResource {

    @GET
    @Produces("application/json")
    public String getGreeting() {
        JsonObjectBuilder job = Json.createObjectBuilder();
        System.out.println("testerttt");
        job.add("greeting, ", "hello world");
        return job.build().toString();
    }

}