package nl.hu.bep.security;

import nl.hu.bep.model.User;

import java.security.Principal;

public class SecurityContext implements javax.ws.rs.core.SecurityContext {
    private User user;
    private String scheme;

    public SecurityContext(User user, String scheme){
        this.user = user;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String role) {
        if(user.getRole() != null){
            return role.equals(user.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
