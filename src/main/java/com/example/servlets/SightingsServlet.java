package com.example.servlets;

import com.example.beans.AdventureService;
import com.example.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// @WebServlet: mapea este Servlet a la ruta "/deliveries". Es descubierto automáticamente
// gracias a @ServletComponentScan en Demo1Application (no requiere web.xml).
@WebServlet("/deliveries")
// @Component: hace de este Servlet un bean de Spring, requisito para poder inyectarle
// FleetService con @Autowired en lugar de instanciarlo con "new".
@Component
public class SightingsServlet extends HttpServlet {

    // @Autowired de campo: Spring inyecta el bean @Service (FleetService) ya existente.
    @Autowired
    private AdventureService adventureService;

    // GET /deliveries: lista todos los registros de entrega en una tabla HTML simple.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Sightings> deliveryRecords = adventureService.getAllSightingss();

        out.println("<html><head><title>Delivery Records</title></head><body>");
        out.println("<h1>Delivery Records</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>ID</th><th>Tracking Code</th><th>Package Description</th>"
                + "<th>Delivery Date</th><th>Destination</th><th>Priority Level</th><th>Vehicle ID</th></tr>");
        for (Sightings sightings : deliveryRecords) {
            out.println("<tr>");
            out.println("<td>" + sightings.getId() + "</td>");
            out.println("<td>" + sightings.getSightingCode() + "</td>");
            out.println("<td>" + sightings.getName() + "</td>");
            out.println("<td>" + sightings.getDescription() + "</td>");
            out.println("<td>" + sightings.getScientificName() + "</td>");
            out.println("<td>" + sightings.getSightedAt() + "</td>");
            out.println("<td>" + sightings.getLocation() + "</td>");
            out.println("<td>" + sightings.getQuantity() + "</td>");
            out.println("<td>" + sightings.getConfidenceLevel() + "</td>");
            out.println("<td>" + sightings.getExpeditionId() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<form method='post' action='sightings'>");
        out.println("id: <input type='text' name='id'><br>");
        out.println("SightingCode: <input type='text' name='sightingCode'><br>");
        out.println("Name: <input type='text' name='name'><br>");
        out.println("Description: <input type='text' name='description'><br>");
        out.println("ScientificName: <input type='text' name='scientificName'><br>");
        out.println("SightedAt: <input type='text' name='sightedAt'><br>");
        out.println("Location: <input type='text' name='location'><br>");
        out.println("Quantity: <input type='text' name='quantity'><br>");
        out.println("ConfidenceLevel: <input type='text' name='confidenceLevel'><br>");
        out.println("ExpeditionId: <input type='text' name='expeditionId'><br>");
        out.println("<input type='submit' value='Register sighting'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // POST /deliveries: lee los parámetros del formulario, arma un Sightings y delega la
    // validación/registro en adventureService.registerDeliveryRecord (las reglas de negocio viven ahí).
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String sightingCode = req.getParameter("sightingCode");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String scientificName = req.getParameter("scientificName");
        String sightedAt = req.getParameter("sightedAt");
        String location = req.getParameter("location");
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));
        Integer confidenceLevel = Integer.valueOf(req.getParameter("confidenceLevel"));
        Integer expeditionId = Integer.valueOf(req.getParameter("expeditionId"));


        Sightings sightings = new Sightings(id, sightingCode, name,
                description, scientificName, sightedAt, location, quantity, confidenceLevel, expeditionId);

        boolean registered = adventureService.registerSightings(sightings);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        if (registered) {
            out.println("<p>Sighting registered successfully.</p>");
        } else {
            out.println("<p>Sightings could not be registered.</p>");
        }
        out.println("<a href='deliveries'>Back to Sightings list</a>");
        out.println("</body></html>");
    }
}
