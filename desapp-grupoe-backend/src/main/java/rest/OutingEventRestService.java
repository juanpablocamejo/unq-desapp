package rest;

import exceptions.EntityValidationException;
import exceptions.RequestException;
import exceptions.ResourceNotFoundException;
import model.builders.outings.OutingFilterBuilder;
import model.locations.Address;
import model.outings.OutingEvent;
import model.tags.Tag;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.LocalDateTime;
import persistence.strategies.OutingFilter;
import rest.dto.OutingEventDTO;
import services.OutingEventService;
import services.TagService;
import services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("events")
public class OutingEventRestService extends GenericRestService<OutingEvent> {

    UserService userService;
    TagService tagService;

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<OutingEventDTO> getEvents() {
        List<OutingEvent> outings = super.getAll();
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
        List<OutingEvent> results = ((OutingEventService) service).searchEvents(filter);
        List<OutingEventDTO> dtos = results.stream().map(this::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findEventById(@PathParam("id") int id) {
        OutingEvent event;
        try {
            event = super.findById(id);
            if (event == null) {
                throw new ResourceNotFoundException("No event with id " + id);
            }
            return Response.ok(toDTO(event)).build();
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

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createEvent(OutingEventDTO dto) {
        OutingEvent event = fromDTO(dto);
        try {
            super.create(event);
            return Response.ok(toDTO(event)).build();
        } catch (EntityValidationException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEvent(OutingEventDTO dto) {
        OutingEvent event;
        try {
            event = fromDTO(dto, service.findById(dto.getId()));
            if (event == null) {
                throw new ResourceNotFoundException("Invalid event...");
            }
            service.update(event);
            return Response.ok(toDTO(event)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @PUT
    @Path("/{idPlace}/addAssistant/{idUser}")
    @Produces("application/json")
    public Response addAssistant(@PathParam("idPlace") int idEvent, @PathParam("idUser") int idUser) {
        OutingEvent event;
        User user;
        try {
            event = service.findById(idEvent);
            user = userService.findById(idUser);
            if (event == null) {
                throw new ResourceNotFoundException("No outing event found with id " + idEvent);
            }

            if (user == null) {
                throw new ResourceNotFoundException("No user found with id " + idUser);
            }
            event.addAssistant(user);
            service.update(event);
            return Response.ok(toDTO(event)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    @PUT
    @Path("/{idPlace}/removeAssistant/{idUser}")
    @Produces("application/json")
    public Response removeAssistant(@PathParam("idPlace") int idEvent, @PathParam("idUser") int idUser) {
        OutingEvent event;
        User user;
        try {
            event = service.findById(idEvent);
            user = userService.findById(idUser);
            if (event == null) {
                throw new ResourceNotFoundException("No outing place found with id " + idEvent);
            }
            if (user == null) {
                throw new ResourceNotFoundException("No user found with id " + idUser);
            }
            event.removeAssistant(user);
            service.update(event);
            return Response.ok(toDTO(event)).build();
        } catch (RequestException e) {
            return e.getHttpResponse();
        }
    }

    public OutingEventDTO toDTO(OutingEvent oe) {
        OutingEventDTO dto = new OutingEventDTO();
        dto.setId(oe.getId());
        dto.setName(oe.getName());
        dto.setDescription(oe.getDescription());
        dto.setImage(oe.getImage());
        dto.setAddress(oe.getAddress().toArray());
        dto.setPrice(oe.getPrice());

        List<Integer> tags = new ArrayList<>();
        oe.getTags().parallelStream().forEach(o -> tags.add(o.getId()));
        dto.setTags(tags);

        List<String> assistants = new ArrayList<>();
        oe.getAssistants().parallelStream().forEach(a -> assistants.add(a.toString()));
        dto.setAssistants(assistants);

        dto.setMaxAssistants(oe.getMaxAssistants());


        dto.setStartDateTime(oe.getStartDateTime().toString());
        dto.setEndDateTime(oe.getEndDateTime().toString());

        return dto;
    }

    public OutingEvent fromDTO(OutingEventDTO dto, OutingEvent... oe) {
        boolean isUpdate = oe.length > 0;
        OutingEvent o = isUpdate ? oe[0] : new OutingEvent();
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
        dto.getAssistants().parallelStream().forEach(f -> assistants.add(userService.findById(Integer.parseInt(f.split(",")[0]))));
        o.setAssistants(assistants);

        o.setMaxAssistants(dto.getMaxAssistants());

        o.setStartDateTime(LocalDateTime.parse(dto.getStartDateTime()));
        o.setEndDateTime(LocalDateTime.parse(dto.getEndDateTime()));

        return o;
    }


}
