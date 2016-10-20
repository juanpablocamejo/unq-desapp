package rest;

import model.builders.outings.OutingEventBuilder;
import model.outings.OutingEvent;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("events")
public class OutingEventRestService extends GenericRestService<OutingEvent> {
    @GET
    @Path("/")
    @Produces("application/json")
    public List<OutingEvent> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") int id) {
        return super.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id") int id) {
        return super.deleteById(id);
    }

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response createEvent(@FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {
        service.save(OutingEventBuilder.anOutingEvent().withName(name).withDescription(description).withPrice(price).build());
        return Response.ok("El evento " + name + " fue creado exitosamente").status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response updateEvent(@PathParam("id") int id, @FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {
        OutingEvent event = service.findById(id);
        if (event == null) {
            return Response.ok("No se puede actualizar el evento. Motivo: Evento inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        event.setName(name);
        event.setDescription(description);
        event.setPrice(price);
        service.update(event);
        return Response.ok("El evento fue actualizado exitosamente").status(HttpStatus.OK_200).build();
    }


}
