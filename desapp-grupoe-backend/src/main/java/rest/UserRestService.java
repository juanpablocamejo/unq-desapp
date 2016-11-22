package rest;

import exceptions.EntityValidationException;
import exceptions.RequestException;
import exceptions.ResourceNotFoundException;
import model.locations.Address;
import model.tags.Tag;
import model.users.Profile;
import model.users.User;
import rest.dto.UserDTO;
import services.TagService;
import services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findUserById(@PathParam("id") int id) {
        User obj;
        try {
            obj = super.findById(id);
            if (obj == null) {
                throw new ResourceNotFoundException("No user with id " + id);
            }
            return Response.ok(toDTO(obj)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }


    @GET
    @Path("/profile/{id}")
    @Produces("application/json")
    public Response findUserProfile(@PathParam("id") int id) {
        User user;
        try {
            user = service.findById(id);
            if (user == null) {
                throw new ResourceNotFoundException("No user with id " + id);
            }
            Profile profile = user.getProfile();
            return Response.ok(profile).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @GET
    @Path("/byEmail/{email}")
    @Produces("application/json")
    public Response findUserByEmail(@PathParam("email") String email) {
        User user;
        try {
            user = ((UserService) service).findByEmail(email);
            if (user == null) {
                throw new ResourceNotFoundException("No user with email " + email);
            }
            return Response.ok(toDTO(user)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @GET
    @Path("/byName/{name}")
    @Produces("application/json")
    public Response findUserByName(@PathParam("name") String name) {
        List<String> users;
        try {
            users = ((UserService) service).findByName(name);
            if (users.isEmpty()) {
                throw new ResourceNotFoundException("No users with name " + name);
            }
            return Response.ok(users).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(UserDTO dto) {
        User user = fromDTO(dto);
        try {
            super.create(user);
            return Response.ok(toDTO(user)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(UserDTO dto) {
        User user;
        try {
            user = fromDTO(dto, service.findById(dto.getId()));
            service.update(user);
            return Response.ok(toDTO(user)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @PUT
    @Path("/{idUser}/addFriend/{idFriend}")
    @Produces("application/json")
    public Response addFriend(@PathParam("idUser") int idUser, @PathParam("idFriend") int idFriend) {
        User user;
        User friend;
        try {
            user = service.findById(idUser);
            friend = service.findById(idFriend);
            if (user == null) {
                throw new ResourceNotFoundException("No user with id " + idUser);
            }

            if (friend == null) {
                throw new ResourceNotFoundException("No friend with id " + idFriend);
            }

            if (idUser == idFriend) {
                throw new EntityValidationException("Can't add yourself as a friend!");
            }

            user.addFriend(friend);
            service.update(user);
            return Response.ok(toDTO(user)).build();

        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @PUT
    @Path("/{idUser}/removeFriend/{idFriend}")
    @Produces("application/json")
    public Response removeFriend(@PathParam("idUser") int idUser, @PathParam("idFriend") int idFriend) {
        User user;
        User friend;
        try {
            user = service.findById(idUser);
            friend = service.findById(idFriend);
            if (user == null) {
                throw new ResourceNotFoundException("No user with id " + idUser);
            }

            if (friend == null) {
                throw new ResourceNotFoundException("No friend with id " + idFriend);
            }

            user.removeFriend(friend);
            service.update(user);
            return Response.ok(toDTO(user)).build();

        } catch (RequestException e) {
            return e.getHttpResponse();
        }
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
