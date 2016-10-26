package rest;

import model.outings.OutingPlace;
import model.users.User;
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
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPlace(OutingPlace place) {
        return super.create(place);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlace(OutingPlace op) {
        OutingPlace place = service.findById(op.getId());
        if (place == null) {
            return Response.ok("No se puede actualizar el lugar. Motivo: Lugar inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        place.setName(op.getName());
        place.setDescription(op.getDescription());
        place.setAddress(op.getAddress());
        place.setPrice(op.getPrice());
        place.setTags(op.getTags());
        place.setAssistants(op.getAssistants());
        place.setWeekTimeSchedule(op.getWeekTimeSchedule());
        service.update(place);
        return Response.ok("El lugar fue actualizado exitosamente").status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{id}/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addAssistant(OutingPlace op, User user) {
        OutingPlace place = service.findById(op.getId());
        if (place == null) {
            return Response.ok("No se puede actualizar el lugar. Motivo: Lugar inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        place.addAssistant(user);
        service.update(place);
        return Response.ok().status(HttpStatus.OK_200).build();
    }
}


