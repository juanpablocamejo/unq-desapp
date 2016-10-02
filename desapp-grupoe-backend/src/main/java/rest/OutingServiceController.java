package rest;

import model.outings.Outing;
import model.planning.IPlanningResult;
import repository.IOutingRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("outings")
public class OutingServiceController {

    private IOutingRepository outingRepository;

    public void setOutingRepository(IOutingRepository outingRepository) {
        this.outingRepository = outingRepository;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<IPlanningResult> allOutings() {
        List<IPlanningResult> outings = outingRepository.getOutings();
        return outings;
    }

    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public IPlanningResult findOutingsByName(@PathParam("name") String name) {
        IPlanningResult outing = outingRepository.getOutingsByName(name);
        return outing;
    }

    @GET
    @Path("/byTag/{tag}")
    @Produces("application/json")
    public List<Outing> findOutingsByTag(@PathParam("tag") String tag) {
        List<Outing> outing = outingRepository.getOutingsByTag(tag);
        return outing;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public IPlanningResult findOutingByID(@PathParam("id") final int id) {
        IPlanningResult outing = outingRepository.getOutingsById(id);
        return outing;
    }

}
