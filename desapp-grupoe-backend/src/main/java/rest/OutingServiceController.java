package rest;

import model.planning.IPlanningResult;
import org.springframework.stereotype.Controller;
import repository.IOutingRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Controller
@Path("/outing")
public class OutingServiceController {

    private IOutingRepository outingRepository;

    public void setOutingRepository(IOutingRepository outingRepository) {
        this.outingRepository = outingRepository;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public IPlanningResult findOutingByID(@PathParam("id") final int id) {
        IPlanningResult outing = outingRepository.getOutingsById(id);
        return outing;
    }

    @GET
    @Path("/outings")
    @Produces("application/json")
    public List<IPlanningResult> allOutings() {
        List<IPlanningResult> outings = outingRepository.getOutings();
        return outings;
    }


}
