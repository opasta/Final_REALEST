package nl.hu.bep.security;

import com.fasterxml.jackson.core.JacksonException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.bep.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

@Path("/auth")
public class AuthenticationResource {
     public static final Key key = MacProvider.generateKey();

     private String createToken(String username, String role) throws JacksonException{
         Calendar expiration = Calendar.getInstance();
         expiration.add(Calendar.MINUTE, 30);

         return Jwts.builder()
                 .setSubject(username)
                 .setExpiration(expiration.getTime())
                 .claim("role", role)
                 .signWith(SignatureAlgorithm.HS512, key)
                 .compact();
     }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUserByPassowrd(@FormParam("userName") String username, @FormParam("passWord") String password){
         try{
             String role = User.validateLogin(username, password);
             System.out.println("role " + role);
             if (role == null){
                 System.out.println("Gegevens incorrect!!");
                 return Response.status(Response.Status.UNAUTHORIZED).build();
             }else{
                 System.out.println("Correcte gegevens");
                 String token = createToken(username, role);
                 System.out.println("Token " + token);

                 SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
                 return Response.ok(JWT).build();
             }


         } catch (JwtException | IllegalArgumentException | JacksonException e) {
             return Response.status(Response.Status.UNAUTHORIZED).build();
         }
    }
}
