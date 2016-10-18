package rest;

import model.builders.OutingEventBuilder;
import model.outings.OutingEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
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
    public ResponseEntity<OutingEvent> findById(@PathParam("id") int id) {
        return super.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity deleteById(@PathParam("id") int id) {
        return super.deleteById(id);
    }

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity createEvent(@FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {


        service.save(OutingEventBuilder.anOutingEvent().withName(name).withDescription(description).withPrice(price).build());
        return new ResponseEntity("The event " + name + " has been created successfuly", HttpStatus.OK);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity updateEvent(@PathParam("id") int id, @FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {
        OutingEvent event = findById(id).getBody();
        if (event == null) {
            return new ResponseEntity("No se puede actualizar el evento. Motivo: Evento inexistente", HttpStatus.NOT_FOUND);
        }
        event.setName(name);
        event.setDescription(description);
        event.setPrice(price);
        service.update(event);
        return new ResponseEntity("El evento fue actualizado exitosamente", HttpStatus.OK);
    }


}
