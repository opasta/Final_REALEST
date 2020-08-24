package nl.hu.bep.servlets;

import nl.hu.bep.security.AuthenticationResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/user_creation")
public class UserCreationServlet extends HttpServlet{

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)

            throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<DOCTYPE html>");
        out.println("<html>");
        out.println("   <title>User Creation</title>");
        out.println("<body>");
        out.println("   <h1>Account aanmaken</h1>");



        out.println("<h1>Customer creation</h1>");
        out.println("<form id=\"POSTCustomerForm1\">");
        out.println("    <label for=\"name\">Gebruikersnaam<input type=\"text\" id=\"name1\" name=\"name\"/></label>");
        out.println("    <label for=\"name\">Wachtwoord<input type=\"password\" id=\"password\" name=\"password\"/></label>");
        out.println("    <label for=\"name\">Voornaam<input type=\"text\" id=\"voornaam\" name=\"voornaam\"/></label>");
        out.println("    <label for=\"name\">Achternaam<input type=\"text\" id=\"achternaam\" name=\"achternaam\"/></label>");
        out.println("    <input type=\"button\" id=\"postJackson1\" value=\"JacksonPost!\"/>");
        out.println("</form>");

        out.println("<script type=\"text/javascript\">");
        out.println("    document.querySelector(\"#postJackson1\").addEventListener(\"click\", function () {");
        out.println("        var w = document.forms[\"POSTCustomerForm1\"][\"name1\"].value;");
        out.println("        var x = document.forms[\"POSTCustomerForm1\"][\"password\"].value;");
        out.println("        var y = document.forms[\"POSTCustomerForm1\"][\"voornaam\"].value;");
        out.println("        var z = document.forms[\"POSTCustomerForm1\"][\"achternaam\"].value;");
        out.println("        if (w == \"\" || w == null || x == \"\" || x == null|| y == \"\" || y == null|| z == \"\" || z == null) {");
        out.println("            alert(\"Niet alle velden zijn ingevuld!\");");
        out.println("            return false;");
        out.println("        }else{");
        out.println("            var formData = new FormData(document.querySelector(\"#POSTCustomerForm1\"));");
        out.println("            var encData = new URLSearchParams(formData.entries());");

        out.println("            fetch(\"restservices/users/allUsers\", {method: 'POST', body: encData})");
        out.println("                .then(response => response.json())");
        out.println("                .then(function (myJson) {");
        out.println("                    console.log(myJson)");
        out.println("                });");
        out.println("        }");
        out.println("    });");
        out.println("</script>");



        out.println("</body>");
        out.println("</html>");
    }
}





