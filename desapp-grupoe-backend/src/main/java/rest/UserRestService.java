package rest;

import model.users.Profile;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserRestService extends GenericRestService<User> {

    @GET
    @Path("/")
    @Produces("application/json")
    public List<User> getAll() {
        return super.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") int id) {
        return super.findById(id);
    }

    @GET
    @Path("/profile/{id}")
    @Produces("application/json")
    public Response findUserProfile(@PathParam("id") int id) {
        User user = service.findById(id);
        if (user == null) {
            return Response.ok("No se encontro el usuario con id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        }
        Profile profile = user.getProfile();
        return Response.ok(profile).status(HttpStatus.OK_200).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(User user) {
        return super.create(user);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(User u) {
        User user = service.findById(u.getId());
        if (user == null) {
            return Response.ok("No se encontro el usuario").status(HttpStatus.NOT_FOUND_404).build();
        }
        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setProfile(u.getProfile());
        user.setFriends(u.getFriends());
        user.setAddress(u.getAddress());
        service.update(user);
        return Response.ok("Se actualizo correctamente el usuario").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id") int id) {
        return super.deleteById(id);
    }
}
