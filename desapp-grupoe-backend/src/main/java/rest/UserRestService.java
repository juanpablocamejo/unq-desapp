package rest;

import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
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

    @GET
    @Path("/profile/{id}")
    @Produces("application/json")
    public Profile findUserProfile(@PathParam("id") int id) {
        return findUsersById(id).getProfile();
    }

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity createUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("profile") int idProfile) {
/*        Profile ps = new ProfileService();
        Profile prof = ps.findProfileByID(idProfile);*/

        service.save(UserBuilder.anyUser().withName(name).withSurname(surname).build());
        return new ResponseEntity("The user " + name + " has been created successfuly.", HttpStatus.OK);
    }

    @PUT
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public void updateUser(MultivaluedMap<String, String> params) {

    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity deleteUserById(@PathParam("id") int id) {

        User user = findUsersById(id);
        if (null == user) {
            return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
        }
        service.delete(user);

        return new ResponseEntity(id, HttpStatus.OK);
    }


}
