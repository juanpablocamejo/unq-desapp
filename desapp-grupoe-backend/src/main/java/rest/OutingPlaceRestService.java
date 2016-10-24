package rest;

import model.builders.outings.OutingPlaceBuilder;
import model.outings.OutingPlace;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("places")
public class OutingPlaceRestService extends GenericRestService<OutingPlace> {

    @GET
    @Path("/")
    @Produces("application/json")
    public List<OutingPlace> getAll() {
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
    public Response createPlace(@FormParam("name") String name, @FormParam("description") String description, @FormParam("price") double price) {

        OutingPlace op = OutingPlaceBuilder.anOutingPlace().withName(name).withDescription(description).withPrice(price).build();
        service.save(op);
        return Response.ok("El place " + name + " fue creado exitosamente").status(HttpStatus.OK_200).build();
    }
}


