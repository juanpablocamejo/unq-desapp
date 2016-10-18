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
    public List<OutingEvent> allOutings() {
        return service.retriveAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public OutingEvent findEventById(@PathParam("id") int id) {
        return service.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity deleteEventById(@PathParam("id") int id) {

        OutingEvent event = findEventById(id);
        if (null == event) {
            return new ResponseEntity("No Event found for ID " + id, HttpStatus.NOT_FOUND);
        }
        service.delete(event);

        return new ResponseEntity(id, HttpStatus.OK);
    }

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity createUser(@FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {


        service.save(OutingEventBuilder.anOutingEvent().withName(name).withDescription(description).withPrice(price).build());
        return new ResponseEntity("The event " + name + " has been created successfuly", HttpStatus.OK);
    }


}
