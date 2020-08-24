package nl.hu.bep.servlets;

import nl.hu.bep.model.Bewoner;
import nl.hu.bep.model.MyUser;
import nl.hu.bep.security.AuthenticationResource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.Arrays;

@WebServlet(urlPatterns = "/logged_in")
public class LoggedInServlet extends HttpServlet{
    public static String Salsaa;
    public static String adminoes;
    public static String usernumber;
    private static final long serialVersionUID = -1641096228274971485L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(MyUser.validateLogin(username, password));


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML response
        PrintWriter writer = response.getWriter();

        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("		<head>\r\n")
                .append("			<title>Welcome message</title>\r\n")
                .append("		</head>\r\n")
                .append("		<body>\r\n");


        if(MyUser.validateLogin(username, password) == null){
            System.out.println("Verkeerd wachtwoord of gebruiker bestaat niet");
            if(MyUser.getUserByName(username) == null) {
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
            System.out.println("Salsa wortd gevuld");
            Salsaa = AuthenticationResource.getToken(username , password);
            adminoes = MyUser.validateLogin(username, password);
        //    String tester = (MyUser.validateLogin(username, password));
            System.out.println("salsastring = "+ Salsaa);
         //   System.out.println(MyUser.validateLogin(username, password));
        //    System.out.println("Other stringgggg" + adminoes);




            writer.append("	Welcome " + username + ".\r\n");

            writer.append("<script>sessionStorage.setItem(\"myJWT\", \"");
            writer.append(Salsaa);
            writer.append("\");</script>");



            writer.append("	Your entered password is " + password + ".\r\n" + "u bent ingelogt als: " + MyUser.validateLogin(username, password));
            writer.append("	You successfully completed this javatutorial.net example.\r\n");
            writer.append("	<button onclick='myFunction()'>Uitloggen</button>");

            String currentuser = (MyUser.getUserByNameIDRETURN(username));
            usernumber = currentuser;

            writer.append("<h1>Aquarium creation</h1>");
            writer.append("<form id=\"POSTCustomerForm1\">");
            writer.append("    <label for=\"name\">Naam<input type=\"text\" id=\"name\" name=\"name\"/></label>");
            writer.append("    <label for=\"name\">Lengte<input type=\"number\" id=\"length\" name=\"length\"/></label>");
            writer.append("    <label for=\"name\">Breedte<input type=\"number\" id=\"width\" name=\"width\"/></label>");
            writer.append("    <label for=\"name\">Hoogte<input type=\"number\" id=\"height\" name=\"height\"/></label>");
            writer.append("    <label for=\"name\">Bodemsoort<input type=\"text\" id=\"bottom\" name=\"bottom\"/></label>");
            writer.append("    <label for=\"name\">Watersoort<input type=\"text\" id=\"water\" name=\"water\"/></label>");
            writer.append("    <input type=\"button\" id=\"postJackson1\" value=\"JacksonPost!\"/>");
            writer.append("</form>");

            writer.append("<script type=\"text/javascript\">");
            writer.append("    document.querySelector(\"#postJackson1\").addEventListener(\"click\", function () {");
            writer.append("        var u = document.forms[\"POSTCustomerForm1\"][\"name\"].value;");
            writer.append("        var v = document.forms[\"POSTCustomerForm1\"][\"length\"].value;");
            writer.append("        var w = document.forms[\"POSTCustomerForm1\"][\"width\"].value;");
            writer.append("        var x = document.forms[\"POSTCustomerForm1\"][\"height\"].value;");
            writer.append("        var y = document.forms[\"POSTCustomerForm1\"][\"bottom\"].value;");
            writer.append("        var z = document.forms[\"POSTCustomerForm1\"][\"water\"].value;");
            writer.append("        if (u == \"\" || u == null || v == \"\" || v == null || w == \"\" || w == null || x == \"\" || x == null|| y == \"\" || y == null|| z == \"\" || z == null) {");
            writer.append("            alert(\"Niet alle velden zijn ingevuld!\");");
            writer.append("            return false;");
            writer.append("        }else{");
            writer.append("            var formData = new FormData(document.querySelector(\"#POSTCustomerForm1\"));");
            System.out.println(currentuser);
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

            writer.append("<h1>Bewoner creation</h1>");
            writer.append("<form id=\"bewonerForm\">");
            writer.append("    <label for=\"name\">Aquarium ID<input type=\"number\" id=\"aquaID\" name=\"aquaID\"/></label>");
            writer.append("    <label for=\"name\">Soortnaam<input type=\"text\" id=\"sname\" name=\"sname\"/></label>");
            writer.append("    <label for=\"name\">Kleur<input type=\"text\" id=\"color\" name=\"color\"/></label>");
            writer.append("    <label for=\"name\">Hoeveelheid<input type=\"number\" id=\"amount\" name=\"amount\"/></label>");
            writer.append("    <input type=\"button\" id=\"send_bewoner\" value=\"Verzend!\"/>");
            writer.append("</form>");

            writer.append("<script type=\"text/javascript\">");
            writer.append("    document.querySelector(\"#send_bewoner\").addEventListener(\"click\", function () {");
            writer.append("        var u = document.forms[\"bewonerForm\"][\"aquaID\"].value;");
            writer.append("        var v = document.forms[\"bewonerForm\"][\"sname\"].value;");
            writer.append("        var w = document.forms[\"bewonerForm\"][\"color\"].value;");
            writer.append("        var x = document.forms[\"bewonerForm\"][\"amount\"].value;");
            writer.append("        if (u == \"\" || u == null || v == \"\" || v == null || w == \"\" || w == null || x == \"\" || x == null) {");
            writer.append("            alert(\"Niet alle velden zijn ingevuld!\");");
            writer.append("            return false;");
            writer.append("        }else{");
            writer.append("            var formData = new FormData(document.querySelector(\"#bewonerForm\"));");
            System.out.println(currentuser);
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

            writer.append("<script>");
            writer.append("     function myFunction() {");
            Bewoner.testStringgg.removeAll(Bewoner.testStringgg);
            writer.append("	        sessionStorage.clear();");
            writer.append("	        window.location='/'");
            writer.append("	    }");
            writer.append("	</script>");

            writer.append("<h2>Via Delete:</h2>\n" +
                    "<h3>Aquarium modification</h3>\n" +
                    "<form id=\"DeleteAquariumForm\">\n" +
                    "    <label for=\"deleteid1\">Customer id:<input type=\"text\" id=\"deleteid1\" name=\"id\"/></label>\n" +
                    "    <input type=\"button\" id=\"delete_aquarium\" value=\"Delete!\"/>\n" +
                    "</form>" +
                    "<script>");

            writer.append("document.querySelector(\"#delete_aquarium\").addEventListener(\"click\", function () {\n" +
                    "        var id = document.querySelector(\"#deleteid1\").value;\n" +
                    "        var fetchOptions = {\n" +
                    "            method: 'DELETE',\n" +
                    "            headers : {\n" +
                    "                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        fetch(\"restservices/aquariums/\"+id, fetchOptions)\n" +
                    "            .then(function (response) {\n" +
                    "                if (response.ok) {\n" +
                    "                    console.log(\"customer deleted\")\n" +
                    "                }\n" +
                    "                else if(response.status==404) {\n" +
                    "                    console.log(\"Customer not found\")\n" +
                    "                }\n" +
                    "                else if(response.status==483) {\n" +
                    "                    console.log(\"forbidden\")\n" +
                    "                }\n" +
                    "            }).catch(error => console.log(error));\n" +
                    "    });</script>");


            writer.append("<br><a href=\"/restservices/aquariums/"+ currentuser + "\">Own Aquariums</a><br>");

            writer.append("<br><a href=\"/restservices/bewoners/"+ currentuser + "\">Own bewoners</a><br>");
            writer.append("<br><a href=\"/restservices/toebehoren/"+ currentuser + "\">Own toebehoren</a><br>");

            writer.append("<h2>Delete oqn Bewoners!!</h2>\n" +
                    "<h3>Customer modification</h3>\n" +
                    "<form id=\"DeleteBewonerForm\">\n" +
                    "    <label for=\"deleteid\">Customer id:<input type=\"text\" id=\"deleteid\" name=\"id\"/></label>\n" +
                    "    <input type=\"button\" id=\"delete3\" value=\"Delete!\"/>\n" +
                    "</form>" +
                    "<script>");

            writer.append("document.querySelector(\"#delete3\").addEventListener(\"click\", function () {\n" +
                    "        var id = document.querySelector(\"#deleteid\").value;\n" +
                    "        var fetchOptions = {\n" +
                    "            method: 'DELETE',\n" +
                    "            headers : {\n" +
                    "                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "        fetch(\"restservices/bewoners/\"+id, fetchOptions)\n" +
                    "            .then(function (response) {\n" +
                    "                if (response.ok) {\n" +
                    "                    console.log(\"customer deleted\")\n" +
                    "                }\n" +
                    "                else if(response.status==404) {\n" +
                    "                    console.log(\"Customer not found\")\n" +
                    "                }\n" +
                    "                else if(response.status==483) {\n" +
                    "                    console.log(\"forbidden\")\n" +
                    "                }\n" +
                    "            }).catch(error => console.log(error));\n" +
                    "    });</script>");






            if(MyUser.validateLogin(username, password) == "admin") {
                writer.append("<br><a href=\"/restservices/users\">All Users</a>");
                writer.append("<br><a href=\"/restservices/aquariums\">All Aquariums</a>");
                writer.append("<a href=\"/restservices/toebehoren/\">All toebehorens</a><br>");
                writer.append("<a href=\"/restservices/bewoners/\">all bewonerss</a><br>");

                writer.append("<h2>Via Delete:</h2>\n" +
                        "<h3>Customer modification</h3>\n" +
                        "<form id=\"DeleteCustomerForm\">\n" +
                        "    <label for=\"deleteid\">Customer id:<input type=\"text\" id=\"deleteid\" name=\"id\"/></label>\n" +
                        "    <input type=\"button\" id=\"delete\" value=\"Delete!\"/>\n" +
                        "</form>" +
                        "<script>");

                writer.append("document.querySelector(\"#delete\").addEventListener(\"click\", function () {\n" +
                        "        var id = document.querySelector(\"#deleteid\").value;\n" +
                        "        var fetchOptions = {\n" +
                        "            method: 'DELETE',\n" +
                        "            headers : {\n" +
                        "                'Authorization' : 'Bearer ' + window.sessionStorage.getItem(\"myJWT\")\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        fetch(\"restservices/users/\"+id, fetchOptions)\n" +
                        "            .then(function (response) {\n" +
                        "                if (response.ok) {\n" +
                        "                    console.log(\"customer deleted\")\n" +
                        "                }\n" +
                        "                else if(response.status==404) {\n" +
                        "                    console.log(\"Customer not found\")\n" +
                        "                }\n" +
                        "                else if(response.status==483) {\n" +
                        "                    console.log(\"forbidden\")\n" +
                        "                }\n" +
                        "            }).catch(error => console.log(error));\n" +
                        "    });</script>");
            }


        }

        writer.append("		</body>\r\n")
                .append("</html>\r\n");


    }

}