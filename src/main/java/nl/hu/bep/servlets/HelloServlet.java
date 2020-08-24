package nl.hu.bep.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/test123")
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        ///test123?name=blikjebier
        if (name == null){
            name = "test";
        }


        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        String[] Randomize = nl.hu.bep.model.Randomize.getList(cars);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<DOCTYPE html>");
        out.println("<html>");
        out.println("   <title>Dynamic voorbeeld</title>");
        out.println("<body>");
        out.println("   <h1>Kechtjup van: " + name + "</h1>");
        out.println("   <h3>" + Arrays.toString(Randomize) + "</h3>");
        out.println("</body>");
        out.println("</html>");
    }
}

