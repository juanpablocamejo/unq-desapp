package rest;

import model.locations.Address;
import model.outings.OutingEvent;
import model.tags.Tag;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.LocalDateTime;
import rest.dto.OutingEventDTO;
import services.appservice.TagService;
import services.appservice.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
        List<OutingEventDTO> dtos = new ArrayList<>();

        for (OutingEvent e : outings) {
            dtos.add(toDTO(e));
        }

        return dtos;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findEventById(@PathParam("id") int id) {
        OutingEvent obj = super.findById(id);
        if (obj == null) {
            return Response.ok("No se encontro la entidad con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        } else {
            OutingEventDTO dto = toDTO(obj);
            return Response.ok(dto).status(HttpStatus.OK_200).build();
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
        return super.create(event);
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEvent(OutingEventDTO dto) {
        OutingEvent event = fromDTO(dto, service.findById(dto.getId()));
        if (event == null) {
            return Response.ok("No se puede actualizar el evento. Motivo: Evento inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        service.update(event);
        return Response.ok(toDTO(event)).status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{idPlace}/addAssistant/{idUser}")
    @Produces("application/json")
    public Response addAssistant(@PathParam("idPlace") int idEvent, @PathParam("idUser") int idUser) {
        OutingEvent event = service.findById(idEvent);
        User user = userService.findById(idUser);
        if (event == null) {
            return Response.ok("No existe un evento con el id: " + idEvent).status(HttpStatus.NOT_FOUND_404).build();
        }

        if (user == null) {
            return Response.ok("No existe un usuario con id: " + idUser).status(HttpStatus.NOT_FOUND_404).build();
        }

        event.addAssistant(user);
        service.update(event);
        return Response.ok(toDTO(event)).status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{idPlace}/removeAssistant/{idUser}")
    @Produces("application/json")
    public Response removeAssistant(@PathParam("idPlace") int idEvent, @PathParam("idUser") int idUser) {
        OutingEvent event = service.findById(idEvent);
        User user = userService.findById(idUser);
        if (event == null) {
            return Response.ok("No existe un evento con el id: " + idEvent).status(HttpStatus.NOT_FOUND_404).build();
        }

        if (user == null) {
            return Response.ok("No existe un usuario con id: " + idUser).status(HttpStatus.NOT_FOUND_404).build();
        }

        event.removeAssistant(user);
        service.update(event);
        return Response.ok(toDTO(event)).status(HttpStatus.OK_200).build();
    }

    public OutingEventDTO toDTO(OutingEvent oe) {
        OutingEventDTO dto = new OutingEventDTO();
        dto.setId(oe.getId());
        dto.setName(oe.getName());
        dto.setDescription(oe.getDescription());
        dto.setAddress(oe.getAddress().toArray());
        dto.setPrice(oe.getPrice());

        List<Integer> tags = new ArrayList<>();
        oe.getTags().parallelStream().forEach(o -> tags.add(o.getId()));
        dto.setTags(tags);

        List<String> assistants = new ArrayList<>();
        oe.getAssistants().parallelStream().forEach(a -> assistants.add(a.toString()));
        dto.setAssistants(assistants);


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

        o.setStartDateTime(LocalDateTime.parse(dto.getStartDateTime()));
        o.setEndDateTime(LocalDateTime.parse(dto.getEndDateTime()));

        return o;
    }


}
