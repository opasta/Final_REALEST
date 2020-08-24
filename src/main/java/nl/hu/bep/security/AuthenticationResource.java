package nl.hu.bep.security;


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

@Path("/authentication")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();

    private static String createToken(String userName, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static String getToken(@FormParam("username") String username, @FormParam("password") String password){
        String role = User.validateLogin(username, password);
        String token = createToken(username, role);
        //System.out.println(token);
        return token;
    }


    public int a = 10;
    public static String b = "ThisisATest";
    public final int c = 30;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static Response authenticateUserByPassword(@FormParam("username") String username, @FormParam("password") String password){
        try {
                String role = User.validateLogin(username, password);

                String token = getToken(username, password);



            SimpleEntry<String, String> JWT = new SimpleEntry<>("JWT", token);
            return Response.ok(JWT).build();
        }
        catch (JwtException | IllegalArgumentException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
