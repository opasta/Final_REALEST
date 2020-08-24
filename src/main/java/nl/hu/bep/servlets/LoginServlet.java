package nl.hu.bep.servlets;

import nl.hu.bep.security.AuthenticationResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)

            throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<DOCTYPE html>");
        out.println("<html>");
        out.println("   <title>Login</title>");
        out.println("<body>");
        out.println("   <h1>Login Formulier</h1>");
        out.println("    <form name=\"myForm\" action=\"/logged_in\" onsubmit=\"return validateForm()\" method=\"post\" required>");
        out.println("       <input type=\"text\" name=\"username\">");
        out.println("       <input type=\"password\" name=\"password\">");
        out.println("       <input type=\"submit\" value=\"Submit\">");
        out.println("   </form>");

        //User creation
        out.println("   <br>");
        out.println("   <a href='/user_creation'>Account aanmaken</a>");

        //Field validate
        out.println("<script>");
        out.println("    function validateForm() {");
        out.println("        var x = document.forms[\"myForm\"][\"username\"].value;");
        out.println("        var y = document.forms[\"myForm\"][\"password\"].value;");
        out.println("        if (x == \"\" || x == null || y == \"\" || y == null) {");
        out.println("            alert(\"Niet alle velden zijn ingevuld!\");");
        out.println("            return false;");
        out.println("        }");
        out.println("    }");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");
    }
}





