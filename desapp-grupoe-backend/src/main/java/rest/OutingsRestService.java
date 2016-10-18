package rest;

import model.outings.Outing;
import services.EventsService;
import services.PlacesService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("outings")
public class OutingsRestService extends GenericRestService<Outing> {
    @GET
    @Path("/")
    @Produces("application/json")
    public List<Outing> allOutings() {
        List<Outing> outings = new ArrayList<>();
        outings.addAll(new EventsService().retriveAll());
        outings.addAll(new PlacesService().retriveAll());
        return outings;
    }

    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Outing findOutingsByName(@PathParam("name") String name) {
        List<Outing> list = allOutings();
        for (Outing o : list) {
            if (o.getName().equals(name)) {
                return o;
            }
        }

        return null;
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Outing findOutingByID(@PathParam("id") final int id) {
        return null;
    }
}
