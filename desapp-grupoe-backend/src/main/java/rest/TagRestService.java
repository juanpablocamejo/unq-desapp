package rest;

import model.tags.Tag;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("tags")
public class TagRestService extends GenericRestService<Tag>{

    @GET
    @Path("/")
    @Produces("application/json")
    public List<Tag> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        Tag tag= service.findById(id);
        if (tag == null) {
            return Response.ok("No se encontro el tag con id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        }
        return Response.ok(tag).status(HttpStatus.OK_200).build();
    }
}
