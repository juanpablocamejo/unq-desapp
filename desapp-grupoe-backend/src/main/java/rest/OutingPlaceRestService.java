package rest;

import exceptions.ResourceNotFoundException;
import model.builders.outings.OutingFilterBuilder;
import model.locations.Address;
import model.outings.OutingPlace;
import model.tags.Tag;
import model.users.User;
import persistence.strategies.OutingFilter;
import rest.dto.OutingPlaceDTO;
import services.OutingPlaceService;
import services.TagService;
import services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("places")
public class OutingPlaceRestService extends GenericRestService<OutingPlace> {

    UserService userService;
    TagService tagService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<OutingPlaceDTO> getPlaces() {
        List<OutingPlace> outings = super.getAll();
        return outings.stream().map(this::toDTO).collect(Collectors.toList());

    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public Response findEvents(@QueryParam("strategy") String strategy,
                               @QueryParam("user") int user,
                               @QueryParam("assistants") int assistants
    ) {

        OutingFilter filter = OutingFilterBuilder.anOutingFilter()
                .withStrategy(strategy)
                .withUserID(user)
                .withAssistants(assistants)
                .build();
        List<OutingPlace> results = ((OutingPlaceService) service).searchPlaces(filter);
        List<OutingPlaceDTO> dtos = results.stream().map(this::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findPlacesById(@PathParam("id") int id) {
        OutingPlace place = super.findById(id);
        if (place == null) {
            throw new ResourceNotFoundException("No place with id " + id);
        }
        return Response.ok(toDTO(place)).build();
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
    public Response createPlace(OutingPlaceDTO dto) {
        OutingPlace place = fromDTO(dto);
        super.create(place);
        return Response.ok(toDTO(place)).build();
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlace(OutingPlaceDTO dto) {
        OutingPlace place = fromDTO(dto, service.findById(dto.getId()));
        service.update(place);
        return Response.ok(toDTO(place)).build();
    }

    @PUT
    @Path("/{idPlace}/addAssistant/{idUser}")
    @Produces("application/json")
    public Response addAssistant(@PathParam("idPlace") int idPlace, @PathParam("idUser") int idUser) {
        OutingPlace place = service.findById(idPlace);
        User user = userService.findById(idUser);
        if (place == null) {
            throw new ResourceNotFoundException("No outing place found with id " + idPlace);
        }
        if (user == null) {
            throw new ResourceNotFoundException("No user found with id " + idUser);
        }
        place.addAssistant(user);
        service.update(place);
        return Response.ok(toDTO(place)).build();
    }

    @PUT
    @Path("/{idPlace}/removeAssistant/{idUser}")
    @Produces("application/json")
    public Response removeAssistant(@PathParam("idPlace") int idPlace, @PathParam("idUser") int idUser) {
        OutingPlace place = service.findById(idPlace);
        User user = userService.findById(idUser);
        if (place == null) {
            throw new ResourceNotFoundException("No outing place found with id " + idPlace);
        }
        if (user == null) {
            throw new ResourceNotFoundException("No user found with id " + idUser);
        }
        place.removeAssistant(user);
        service.update(place);
        return Response.ok(toDTO(place)).build();
    }

    public OutingPlaceDTO toDTO(OutingPlace op) {
        OutingPlaceDTO dto = new OutingPlaceDTO();
        dto.setId(op.getId());
        dto.setName(op.getName());
        dto.setDescription(op.getDescription());
        dto.setImage(op.getImage());
        dto.setAddress(op.getAddress().toArray());

        List<Integer> assistants = new ArrayList<>();
        op.getAssistants().parallelStream().forEach(a -> assistants.add(a.getId()));
        dto.setAssistants(assistants);

        dto.setMaxAssistants(op.getMaxAssistants());
        dto.setPrice(op.getPrice());

        List<Integer> tags = new ArrayList<>();
        op.getTags().parallelStream().forEach(t -> tags.add(t.getId()));
        dto.setTags(tags);
        dto.setWeekTimeSchedule(op.getWeekTimeSchedule().toString());
        return dto;
    }

    public OutingPlace fromDTO(OutingPlaceDTO dto, OutingPlace... op) {
        boolean isUpdate = op.length > 0;
        OutingPlace o = isUpdate ? op[0] : new OutingPlace();
        o.setId(dto.getId());
        o.setName(dto.getName());
        o.setDescription(dto.getDescription());
        o.setImage(dto.getImage());
        o.setPrice(dto.getPrice());
        Address newAddress = Address.fromArray(dto.getAddress());
        if (isUpdate) {
            newAddress.setId(o.getAddress().getId());
            newAddress.getCoord().setId(o.getAddress().getCoord().getId());
        }
        o.setAddress(newAddress);

        List<Tag> tags = new ArrayList<>();
        dto.getTags().parallelStream().forEach(t -> tags.add(tagService.findById(t)));
        o.setTags(tags);

        List<User> assistants = new ArrayList<>();
        dto.getAssistants().parallelStream().forEach(f -> assistants.add(userService.findById(f)));
        o.setAssistants(assistants);

        o.setMaxAssistants(dto.getMaxAssistants());

        //o.setWeekTimeSchedule(dto.getWeekTimeSchedule().parseFromString());

        return o;
    }
}


