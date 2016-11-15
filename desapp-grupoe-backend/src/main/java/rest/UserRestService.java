package rest;

import model.locations.Address;
import model.tags.Tag;
import model.users.Profile;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;
import rest.dto.UserDTO;
import services.appservice.TagService;
import services.appservice.UserService;

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
            return Response.ok(toDTO(obj)).status(HttpStatus.OK_200).build();
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

    @GET
    @Path("/byEmail/{email}")
    @Produces("application/json")
    public Response findUserByEmail(@PathParam("email") String email) {
        User user = ((UserService) service).findByEmail(email);
        if (user == null) {
            return Response.ok().status(HttpStatus.NOT_FOUND_404).build();
        }
        return Response.ok(toDTO(user)).build();
    }

    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response findUserByName(@PathParam("name") String name) {
        List<String> users = ((UserService) service).findByName(name);
        if (users.isEmpty()) {
            return Response.ok().status(HttpStatus.NOT_FOUND_404).build();
        }
        return Response.ok(users).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(UserDTO dto) {
        User user = fromDTO(dto);
        super.create(user);
        return Response.ok(service.findById(user.getId())).build();
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
        return Response.ok(toDTO(service.findById(user.getId()))).status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{idUser}/addFriend/{idFriend}")
    @Produces("application/json")
    public Response addFriend(@PathParam("idUser") int idUser, @PathParam("idFriend") int idFriend) {
        User user = service.findById(idUser);
        User friend = service.findById(idFriend);
        if (user == null) {
            return Response.ok("No existe un usuario con el id: " + idUser).status(HttpStatus.NOT_FOUND_404).build();
        }

        if (friend == null) {
            return Response.ok("No existe un usuario con id: " + idFriend).status(HttpStatus.NOT_FOUND_404).build();
        }

        user.addFriend(friend);
        service.update(user);
        return Response.ok(toDTO(user)).status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{idUser}/removeFriend/{idFriend}")
    @Produces("application/json")
    public Response removeFriend(@PathParam("idUser") int idUser, @PathParam("idFriend") int idFriend) {
        User user = service.findById(idUser);
        User friend = service.findById(idFriend);
        if (user == null) {
            return Response.ok("No existe un usuario con el id: " + idUser).status(HttpStatus.NOT_FOUND_404).build();
        }

        if (friend == null) {
            return Response.ok("No existe un usuario con id: " + idFriend).status(HttpStatus.NOT_FOUND_404).build();
        }

        user.removeFriend(friend);
        service.update(user);
        return Response.ok(toDTO(user)).status(HttpStatus.OK_200).build();
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
        dto.setEmail(user.getEmail());
        dto.setImage(user.getImage());
        dto.setAddress(user.getAddress().toArray());
        dto.setInexpensiveOutingLimit(user.getProfile().getInexpensiveOutingLimit());

        List<String[]> friends = new ArrayList<>();
        user.getFriends().forEach(f -> friends.add(f.toArray()));
        dto.setFriends(friends);

        List<Integer> tags = new ArrayList<>();
        user.getProfile().getTags().forEach(tag -> tags.add(tag.getId()));
        dto.setTags(tags);
        return dto;
    }

    public User fromDTO(UserDTO dto, User... user) {
        Boolean isUpdate = user.length > 0;
        User u = isUpdate ? user[0] : new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setEmail(dto.getEmail());
        u.setImage(dto.getImage());
        u.getProfile().setInexpensiveOutingLimit(dto.getInexpensiveOutingLimit());
        Address newAddress = Address.fromArray(dto.getAddress());
        if (isUpdate) {
            newAddress.setId(u.getAddress().getId());
            newAddress.getCoord().setId(u.getAddress().getCoord().getId());
        }
        u.setAddress(newAddress);

        List<User> friends = new ArrayList<>();
        dto.getFriends().parallelStream().forEach(f -> friends.add(service.findById(Integer.parseInt(f[0]))));
        u.setFriends(friends);

        List<Tag> tags = new ArrayList<>();
        dto.getTags().parallelStream().forEach(tagId -> tags.add(tagService.findById(tagId)));
        u.getProfile().setTags(tags);

        return u;
    }
}
