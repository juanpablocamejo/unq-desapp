package rest;

import model.builders.UserBuilder;
import model.users.Profile;
import model.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;

import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
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
    public ResponseEntity<User> findById(@PathParam("id") int id) {
        return super.findById(id);
    }

    @GET
    @Path("/profile/{id}")
    @Produces("application/json")
    public ResponseEntity<Profile> findUserProfile(@PathParam("id") int id) {
        User user = service.findById(id);
        if (user == null) {
            return new ResponseEntity("No existe un usuario con id " + id, HttpStatus.NOT_FOUND);
        }
        Profile profile = user.getProfile();
        return new ResponseEntity<>(profile, HttpStatus.FOUND);
    }

    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity createUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("profile") int idProfile) {
/*        Profile ps = new ProfileService();
        Profile prof = ps.findProfileByID(idProfile);*/

        service.save(UserBuilder.anyUser().withName(name).withSurname(surname).build());
        return new ResponseEntity("El usuario " + name + " fué creado exitosamente.", HttpStatus.OK);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public ResponseEntity updateUser(@PathParam("id") int id, @FormParam("name") String name, @FormParam("surname") String surname, @FormParam("profile") int idProfile) {
        User user = findById(id).getBody();
        if (user == null) {
            return new ResponseEntity("No se puede actualizar el usuario. Motivo: Usuario inexistente", HttpStatus.NOT_FOUND);
        }
        user.setName(name);
        user.setSurname(surname);
        //user.setProfile(profile);
        service.update(user);
        return new ResponseEntity("El usuario fue actualizado exitosamente", HttpStatus.OK);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public ResponseEntity deleteById(@PathParam("id") int id) {
        return super.deleteById(id);
    }
}
