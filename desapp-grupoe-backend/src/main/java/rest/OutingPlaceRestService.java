package rest;

import model.outings.OutingPlace;

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
}
