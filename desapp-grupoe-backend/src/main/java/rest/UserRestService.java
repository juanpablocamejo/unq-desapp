package rest;

import model.builders.UserBuilder;
import model.users.User;
import services.GenericService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("users")
public class UserRestService extends GenericRestService<User> {

    @GET
    @Path("/")
    @Produces("application/json")
    public List<User> allUsers() {
        return service.retriveAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public User findUsersById(@PathParam("id") int id) {
        return service.findById(id);
    }

/*    @GET
    @Path("/profile/{id}")
    @Produces("application/json")
    public Profile findUserProfile(@PathParam("id") int id) {
        return service.getProfile(id);
    }*/


}
