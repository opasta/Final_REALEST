package nl.hu.bep.servlets;

import nl.hu.bep.model.User;
import nl.hu.bep.security.AuthenticationResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logged_in")
public class LoggedInServlet extends HttpServlet{
    public static String JWT_Token;
    public static String Amin_or_not;
    public static String usernumber;
    private static final long serialVersionUID = -1641096228274971485L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(User.validateLogin(username, password));

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Html schrijven
        PrintWriter writer = response.getWriter();

        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Ingelogd</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n");


        if(User.validateLogin(username, password) == null){
            System.out.println("Verkeerd wachtwoord of gebruiker bestaat niet");
            if(User.getUserByName(username) == null) {
                writer.append("<script>")
                        .append("if(alert('Gebruiker bestaat niet!')){}")
                        .append("else    {")
                        .append("location.replace('/login')")
                        .append("}")
                        .append("</script>");
            }else {
                writer.append("<script>")
                        .append("if(alert('Incorrect wachtwoord')){}")
                        .append("else    {")
                        .append("location.replace('/login')")
                        .append("}")
                        .append("</script>");
            }
        }else{
            System.out.println("JWT wortd gevuld");
            JWT_Token = AuthenticationResource.getToken(username , password);
            Amin_or_not = User.validateLogin(username, password);
            System.out.println("JWT Token = "+ JWT_Token);
            writer.append("<h1>Welkom  " + username + "</h1>\r\n");
            writer.append("<script>sessionStorage.setItem(\"myJWT\", \"");
            writer.append(JWT_Token);
            writer.append("\");</script>");
            writer.append( "<h2>U bent ingelogt als: " + User.validateLogin(username, password) + "</h2>");
            writer.append("	<button onclick='myFunction()'>Uitloggen</button>");
            String currentuser = (User.getUserByNameIDRETURN(username));
            usernumber = currentuser;

            writer.append("<script>");
            writer.append("     function myFunction() {sessionStorage.clear();");
            writer.append("	        window.location='/'");
            writer.append("	    }");
            writer.append("	</script>");


            writer.append("<div class=\"Creation_DIV\" style=\"background-color: lightgray;\">");

                writer.append("<h2>Aquarium creation</h2>");
                writer.append("<form id=\"aquariumCreate\">");
                writer.append("    <label for=\"name\">Naam<input type=\"text\" id=\"name\" name=\"name\"/></label>");
                writer.append("    <label for=\"name\">Lengte<input type=\"number\" id=\"length\" name=\"length\"/></label>");
                writer.append("    <label for=\"name\">Breedte<input type=\"number\" id=\"width\" name=\"width\"/></label>");
                writer.append("    <label for=\"name\">Hoogte<input type=\"number\" id=\"height\" name=\"height\"/></label>");
                writer.append("    <label for=\"name\">Bodemsoort<input type=\"text\" id=\"bottom\" name=\"bottom\"/></label>");
                writer.append("    <label for=\"name\">Watersoort<input type=\"text\" id=\"water\" name=\"water\"/></label>");
                writer.append("    <input type=\"button\" id=\"send_Aquarium\" value=\"Verzend!\"/>");
                writer.append("</form>");

                writer.append("<script type=\"text/javascript\">");
                writer.append("    document.querySelector(\"#send_Aquarium\").addEventListener(\"click\", function () {");
                writer.append("        var u = document.forms[\"aquariumCreate\"][\"name\"].value;");
                writer.append("        var v = document.forms[\"aquariumCreate\"][\"length\"].value;");
                writer.append("        var w = document.forms[\"aquariumCreate\"][\"width\"].value;");
                writer.append("        var x = document.forms[\"aquariumCreate\"][\"height\"].value;");
                writer.append("        var y = document.forms[\"aquariumCreate\"][\"bottom\"].value;");
                writer.append("        var z = document.forms[\"aquariumCreate\"][\"water\"].value;");
                writer.append("        if (u == \"\" || u == null || v == \"\" || v == null || w == \"\" || w == null || x == \"\" || x == null|| y == \"\" || y == null|| z == \"\" || z == null) {");
                writer.append("            alert(\"Niet alle velden zijn ingevuld!\");");
                writer.append("            return false;");
                writer.append("        }else{");
                writer.append("            var formData = new FormData(document.querySelector(\"#aquariumCreate\"));");
                writer.append("            formData.append('ownerID', '" + currentuser + "');");
                writer.append("            var encData = new URLSearchParams(formData.entries());");
                writer.append("            fetch(\"restservices/aquariums/aquariumz\", {method: 'POST', body: encData})");
                writer.append("                .then(response => response.json())");
                writer.append("                .then(function (myJson) {");
                writer.append("                    console.log(myJson)");
                writer.append("                });");
                writer.append("        }");
                writer.append("    });");
                writer.append("</script>");

                writer.append("<h2>Bewoner creation</h2>");
                writer.append("<form id=\"bewonerCreate\">");
                writer.append("    <label for=\"name\">Aquarium ID<input type=\"number\" id=\"aquaID\" name=\"aquaID\"/></label>");
                writer.append("    <label for=\"name\">Soortnaam<input type=\"text\" id=\"sname\" name=\"sname\"/></label>");
                writer.append("    <label for=\"name\">Kleur<input type=\"text\" id=\"color\" name=\"color\"/></label>");
                writer.append("    <label for=\"name\">Hoeveelheid<input type=\"number\" id=\"amount\" name=\"amount\"/></label>");
                writer.append("    <input type=\"button\" id=\"send_bewoner\" value=\"Verzend!\"/>");
                writer.append("</form>");
                writer.append("<script type=\"text/javascript\">");
                writer.append("    document.querySelector(\"#send_bewoner\").addEventListener(\"click\", function () {");
                writer.append("        var u = document.forms[\"bewonerCreate\"][\"aquaID\"].value;");
                writer.append("        var v = document.forms[\"bewonerCreate\"][\"sname\"].value;");
                writer.append("        var w = document.forms[\"bewonerCreate\"][\"color\"].value;");
                writer.append("        var x = document.forms[\"bewonerCreate\"][\"amount\"].value;");
                writer.append("        if (u == \"\" || u == null || v == \"\" || v == null || w == \"\" || w == null || x == \"\" || x == null) {");
                writer.append("            alert(\"Niet alle velden zijn ingevuld!\");");
                writer.append("            return false;");
                writer.append("        }else{");
                writer.append("            var formData = new FormData(document.querySelector(\"#bewonerCreate\"));");
                writer.append("            formData.append('ownerID', '" + currentuser + "');");
                writer.append("            var encData = new URLSearchParams(formData.entries());");
                writer.append("            fetch(\"restservices/bewoners/bewoner_add\", {method: 'POST', body: encData})");
                writer.append("                .then(response => response.json())");
                writer.append("                .then(function (myJson) {");
                writer.append("                    console.log(myJson)");
                writer.append("                });");
                writer.append("        }");
                writer.append("    });");
                writer.append("</script>");

                writer.append("<h2>Toebehoren creation</h2>");
                writer.append("<form id=\"toebehorenCreate\">");
                writer.append("    <label for=\"name\">Aquarium ID<input type=\"number\" id=\"aquaID2\" name=\"aquaID2\"/></label>");
                writer.append("    <label for=\"name\">Model<input type=\"text\" id=\"model\" name=\"model\"/></label>");
                writer.append("    <label for=\"name\">Serienummer<input type=\"text\" id=\"snumber\" name=\"snumber\"/></label>");
                writer.append("    <input type=\"button\" id=\"send_toebehoren\" value=\"Verzend!\"/>");
                writer.append("</form>");
                writer.append("<script type=\"text/javascript\">");
                writer.append("    document.querySelector(\"#send_toebehoren\").addEventListener(\"click\", function () {");
                writer.append("        var u = document.forms[\"toebehorenCreate\"][\"aquaID2\"].value;");
                writer.append("        var v = document.forms[\"toebehorenCreate\"][\"model\"].value;");
                writer.append("        var w = document.forms[\"toebehorenCreate\"][\"snumber\"].value;");
                writer.append("        if (u == \"\" || u == null || v == \"\" || v == null || w == \"\" || w == null) {");
                writer.append("            alert(\"Niet alle velden zijn ingevuld!\");");
                writer.append("            return false;");
                writer.append("        }else{");
                writer.append("            var formData = new FormData(document.querySelector(\"#toebehorenCreate\"));");
                writer.append("            formData.append('ownerID2', '" + currentuser + "');");
                writer.append("            var encData = new URLSearchParams(formData.entries());");
                writer.append("            fetch(\"restservices/toebehoren/toebehoren_add\", {method: 'POST', body: encData})");
                writer.append("                .then(response => response.json())");
                writer.append("                .then(function (myJson) {");
                writer.append("                    console.log(myJson)");
                writer.append("                });");
                writer.append("        }");
                writer.append("    });");
                writer.append("</script>");

            writer.append("</div>");

            writer.append("<br>");

            writer.append("<div class=\"Delete_DIV\" style=\"background-color: lightgray;\">");
                writer.append("<h2>Aquarium Delete</h2>\n");
                writer.append("<form id=\"aquariumDelete\">\n");
                writer.append("    <label for=\"deleteid1\">Customer id:<input type=\"text\" id=\"deleteid1\" name=\"id\"/></label>\n");
                writer.append("    <input type=\"button\" id=\"delete_aquarium\" value=\"Delete!\"/>\n");
                writer.append("</form>");
                writer.append("<script>");

                writer.append("document.querySelector(\"#delete_aquarium\").addEventListener(\"click\", function () {\n");
                writer.append("        var id = document.querySelector(\"#deleteid1\").value;\n");
                writer.append("        var fetchOptions = {\n");
                writer.append("            method: 'DELETE',\n");
                writer.append("            headers : {\n");
                writer.append("                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n");
                writer.append("            }\n");
                writer.append("        }\n");
                writer.append("        fetch(\"restservices/aquariums/\"+id, fetchOptions)\n");
                writer.append("            .then(function (response) {\n");
                writer.append("                if (response.ok) {\n");
                writer.append("                    console.log(\"customer deleted\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==404) {\n");
                writer.append("                    console.log(\"Customer not found\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==483) {\n");
                writer.append("                    console.log(\"forbidden\")\n");
                writer.append("                }\n");
                writer.append("            }).catch(error => console.log(error));\n");
                writer.append("    });</script>");

                writer.append("<h2>Bewoner Delete</h2>\n");
                writer.append("<form id=\"bewonerDelete\">\n");
                writer.append("    <label for=\"deleteid\">Customer id:<input type=\"text\" id=\"deleteid2\" name=\"id\"/></label>\n");
                writer.append("    <input type=\"button\" id=\"delete_bewoner\" value=\"Delete!\"/>\n");
                writer.append("</form>");
                writer.append("<script>");

                writer.append("document.querySelector(\"#delete_bewoner\").addEventListener(\"click\", function () {\n");
                writer.append( "        var id = document.querySelector(\"#deleteid2\").value;\n");
                writer.append("        var fetchOptions = {\n");
                writer.append("            method: 'DELETE',\n");
                writer.append("            headers : {\n");
                writer.append("                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n");
                writer.append("            }\n");
                writer.append("        }\n");
                writer.append("        fetch(\"restservices/bewoners/\"+id, fetchOptions)\n");
                writer.append("            .then(function (response) {\n");
                writer.append("                if (response.ok) {\n");
                writer.append("                    console.log(\"customer deleted\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==404) {\n");
                writer.append("                    console.log(\"Customer not found\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==483) {\n");
                writer.append("                    console.log(\"forbidden\")\n");
                writer.append("                }\n");
                writer.append("            }).catch(error => console.log(error));\n");
                writer.append("    });</script>");

                writer.append("<h2>Toebehoren Delete</h2>\n");
                writer.append("<form id=\"toebehorenDelete\">\n");
                writer.append("    <label for=\"deleteid\">Customer id:<input type=\"text\" id=\"deleteid3\" name=\"id\"/></label>\n");
                writer.append("    <input type=\"button\" id=\"delete_toebehoren\" value=\"Delete!\"/>\n");
                writer.append("</form>");
                writer.append("<script>");

                writer.append("document.querySelector(\"#delete_toebehoren\").addEventListener(\"click\", function () {\n");
                writer.append( "        var id = document.querySelector(\"#deleteid3\").value;\n");
                writer.append("        var fetchOptions = {\n");
                writer.append("            method: 'DELETE',\n");
                writer.append("            headers : {\n");
                writer.append("                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n");
                writer.append("            }\n");
                writer.append("        }\n");
                writer.append("        fetch(\"restservices/toebehoren/\"+id, fetchOptions)\n");
                writer.append("            .then(function (response) {\n");
                writer.append("                if (response.ok) {\n");
                writer.append("                    console.log(\"customer deleted\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==404) {\n");
                writer.append("                    console.log(\"Customer not found\")\n");
                writer.append("                }\n");
                writer.append("                else if(response.status==483) {\n");
                writer.append("                    console.log(\"forbidden\")\n");
                writer.append("                }\n");
                writer.append("            }).catch(error => console.log(error));\n");
                writer.append("    });</script>");

            writer.append("</div>");

            writer.append("<br>");


            writer.append("<div class=\"user_links\" style=\"background-color: green;color: white;\">");

                writer.append("<br><a href=\"/restservices/aquariums/"+ currentuser + "\">Own Aquariums</a><br>");
                writer.append("<br><a href=\"/restservices/bewoners/"+ currentuser + "\">Own bewoners</a><br>");
                writer.append("<br><a href=\"/restservices/toebehoren/"+ currentuser + "\">Own toebehoren</a><br>");

            writer.append("</div>");

            writer.append("<br>");

            writer.append("<div class=\"admin_links\" style=\"background-color: green;color: white;\">");
                if(User.validateLogin(username, password) == "admin") {
                    writer.append("<br><a href=\"/restservices/users\">All Users</a><br>");
                    writer.append("<a href=\"/restservices/aquariums\">All Aquariums</a><br>");
                    writer.append("<a href=\"/restservices/bewoners/\">all bewonerss</a><br>");
                    writer.append("<a href=\"/restservices/toebehoren/\">All toebehorens</a><br>");

            writer.append("</div>");
            writer.append("<div class=\"admin_delete\" style=\"background-color: grey;\">");

                    writer.append("<h2>Customer Delete:</h2>\n");
                    writer.append("<form id=\"DeleteCustomerForm\">\n");
                    writer.append("    <label for=\"deleteid\">Customer id:<input type=\"text\" id=\"deleteid\" name=\"id\"/></label>\n");
                    writer.append("    <input type=\"button\" id=\"delete\" value=\"Delete!\"/>\n");
                    writer.append("</form>");
                    writer.append("<script>");

                    writer.append("document.querySelector(\"#delete\").addEventListener(\"click\", function () {\n");
                    writer.append("        var id = document.querySelector(\"#deleteid\").value;\n");
                    writer.append("        var fetchOptions = {\n");
                    writer.append("            method: 'DELETE',\n");
                    writer.append("            headers : {\n");
                    writer.append("                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n");
                    writer.append("            }\n");
                    writer.append("        }\n");
                    writer.append("        fetch(\"restservices/users/\"+id, fetchOptions)\n");
                    writer.append("            .then(function (response) {\n");
                    writer.append("                if (response.ok) {\n");
                    writer.append("                    console.log(\"customer deleted\")\n");
                    writer.append("                }\n");
                    writer.append("                else if(response.status==404) {\n");
                    writer.append("                    console.log(\"Customer not found\")\n");
                    writer.append("                }\n");
                    writer.append("                else if(response.status==483) {\n");
                    writer.append("                    console.log(\"forbidden\")\n");
                    writer.append("                }\n");
                    writer.append("            }).catch(error => console.log(error));\n");
                    writer.append("    });</script>");
                }

            writer.append("</div>");

            }

        writer.append("		</body>\r\n")
                .append("</html>\r\n");
    }

}