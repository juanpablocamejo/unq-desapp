package rest;

import model.locations.Address;
import model.tags.Tag;
import model.users.Profile;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;
import rest.dto.UserDTO;
import services.appservice.TagService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("users")
public class UserRestService extends GenericRestService<User> {

    TagService tagService;

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<UserDTO> getUsers() {
        List<User> users = super.getAll();
        List<UserDTO> dtos = new ArrayList<>();

        for (User u : users) {
            dtos.add(toDTO(u));
        }

        return dtos;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findUserById(@PathParam("id") int id) {
        User obj = super.findById(id);
        if (obj == null) {
            return Response.ok("No se encontro el usuario con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        } else {
            UserDTO dto = toDTO(obj);
            return Response.ok(dto).status(HttpStatus.OK_200).build();
        }
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
    public Response createUser(UserDTO dto) {
        User user = fromDTO(dto);
        return super.create(user);
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(UserDTO dto) {
        User user = fromDTO(dto, service.findById(dto.getId()));
        if (user == null) {
            return Response.ok("No se encontro el usuario").status(HttpStatus.NOT_FOUND_404).build();
        }
        service.update(user);
        return Response.ok(user).status(HttpStatus.OK_200).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteById(@PathParam("id") int id) {
        return super.deleteById(id);
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setAddress(user.getAddress().toArray());
        dto.setInexpensiveOutingLimit(user.getProfile().getInexpensiveOutingLimit());

        List<String> friends = new ArrayList<>();
        user.getFriends().parallelStream().forEach(f -> friends.add(f.toString()));
        dto.setFriends(friends);

        List<Integer> tags = new ArrayList<>();
        user.getProfile().getTags().parallelStream().forEach(tag -> tags.add(tag.getId()));
        dto.setTags(tags);
        return dto;
    }

    public User fromDTO(UserDTO dto, User... user) {
        User u = user.length > 0 ? user[0] : new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.getProfile().setInexpensiveOutingLimit(dto.getInexpensiveOutingLimit());
        u.setAddress(Address.fromArray(dto.getAddress()));

        List<User> friends = new ArrayList<>();
        dto.getFriends().parallelStream().forEach(f -> friends.add(service.findById(Integer.parseInt(f.split(",")[0]))));
        u.setFriends(friends);

        List<Tag> tags = new ArrayList<>();
        dto.getTags().parallelStream().forEach(tagId -> tags.add(tagService.findById(tagId)));
        u.getProfile().setTags(tags);

        return u;
    }
}
