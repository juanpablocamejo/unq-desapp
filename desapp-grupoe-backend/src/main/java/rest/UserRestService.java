package rest;

import model.builders.UserBuilder;
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
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response createUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("profile") int idProfile) {
//        Profile ps = new ProfileService();
//        Profile prof = ps.findProfileByID(idProfile);
        service.save(UserBuilder.anyUser().withName(name).withSurname(surname).build());
        return Response.ok("El usuario " + name + " fue creado correctamente").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") int id, @FormParam("name") String name, @FormParam("surname") String surname, @FormParam("profile") int idProfile) {
        User user = service.findById(id);
        if (user == null) {
            return Response.ok("No se encontro el usuario con id " + id).status(HttpStatus.NOT_FOUND_404).build();
        }
        user.setName(name);
        user.setSurname(surname);
        //user.setProfile(profile);
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
