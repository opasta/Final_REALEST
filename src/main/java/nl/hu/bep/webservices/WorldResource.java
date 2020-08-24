package nl.hu.bep.webservices;

import nl.hu.bep.model.Country;
import nl.hu.bep.model.World;

import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

@Path("/countries")
public class WorldResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createCountry(String jsonBody) {
        System.out.println("POST WORKS FINE");
        StringReader stringReader = new StringReader(jsonBody);
        JsonStructure structure = Json.createReader(stringReader).read();

        String message = "Wrong Json format!";;


        if (structure.getValueType() == JsonValue.ValueType.OBJECT) {
            JsonObject jsonObject = (JsonObject)structure;

            String code = jsonObject.getString("code");
            String iso3 = jsonObject.getString("iso3");
            String name = jsonObject.getString("name");
            String capital = jsonObject.getString("capital");
            String continent = jsonObject.getString("continent");
            String region = jsonObject.getString("region");
            double surface = Double.parseDouble(jsonObject.getString("surface"));
            int population = Integer.parseInt(jsonObject.getString("population"));
            String government = jsonObject.getString("government");
            double latitude = Double.parseDouble(jsonObject.getString("latitude"));
            double longitude = Double.parseDouble(jsonObject.getString("longitude"));


            Country newCountry = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);

            if (World.getWorld().addCountry(newCountry)){
                message = "country created!";
            } else {
                message = "country allready exists!";
            }

        }
        return Json.createObjectBuilder().add("message", message).build().toString();
    }

    @GET
    @Produces("application/json")
    public String getAllCountries() {
        System.out.println("GET WORKS FINE");

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Country country: World.getWorld().getAllCountries()){
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

            objectBuilder.add("code", country.getCode());
            objectBuilder.add("iso3", country.getIso3());
            objectBuilder.add("name", country.getName());
            objectBuilder.add("captial", country.getCapital());
            objectBuilder.add("continent", country.getContinent());
            objectBuilder.add("region", country.getRegion());
            objectBuilder.add("surface", country.getSurface());
            objectBuilder.add("population", country.getPopulation());
            objectBuilder.add("government", country.getGovernment());
            objectBuilder.add("lat", country.getLatitude());
            objectBuilder.add("lng", country.getLongitude());

            arrayBuilder.add(objectBuilder);
        }
        return arrayBuilder.build().toString();
    }

}
