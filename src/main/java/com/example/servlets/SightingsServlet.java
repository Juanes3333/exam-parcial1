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
            out.println("<td>" + sightings.getTrackingCode() + "</td>");
            out.println("<td>" + sightings.getPackageDescription() + "</td>");
            out.println("<td>" + sightings.getDeliveryDate() + "</td>");
            out.println("<td>" + sightings.getDestination() + "</td>");
            out.println("<td>" + sightings.getPriorityLevel() + "</td>");
            out.println("<td>" + sightings.getVehicleId() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<form method='post' action='deliveries'>");
        out.println("Tracking Code: <input type='text' name='trackingCode'><br>");
        out.println("Package Description: <input type='text' name='packageDescription'><br>");
        out.println("Delivery Date: <input type='text' name='deliveryDate'><br>");
        out.println("Destination: <input type='text' name='destination'><br>");
        out.println("Priority Level: <input type='text' name='priorityLevel'><br>");
        out.println("Vehicle ID: <input type='text' name='vehicleId'><br>");
        out.println("<input type='submit' value='Register delivery record'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // POST /deliveries: lee los parámetros del formulario, arma un Sightings y delega la
    // validación/registro en adventureService.registerDeliveryRecord (las reglas de negocio viven ahí).
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer newId = Integer.valueOf(req.getParameter("newId"));
        String trackingCode = req.getParameter("trackingCode");
        String packageDescription = req.getParameter("packageDescription");
        String deliveryDate = req.getParameter("deliveryDate");
        String destination = req.getParameter("destination");
        Integer priorityLevel = Integer.valueOf(req.getParameter("priorityLevel"));
        Integer vehicleId = Integer.valueOf(req.getParameter("vehicleId"));


        Sightings sightings = new Sightings(newId, trackingCode, packageDescription,
                deliveryDate, destination, priorityLevel, vehicleId);

        boolean registered = adventureService.registerDeliveryRecord(sightings);

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
