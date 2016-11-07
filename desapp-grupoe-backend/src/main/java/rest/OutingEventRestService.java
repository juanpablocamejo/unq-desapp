package rest;

import model.locations.Address;
import model.outings.OutingEvent;
import model.tags.Tag;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.LocalDateTime;
import rest.dto.OutingEventDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("events")
public class OutingEventRestService extends GenericRestService<OutingEvent> {
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
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEvent(OutingEventDTO dto) {
        OutingEvent event = fromDTO(dto, service.findById(dto.getId()));
        if (event == null) {
            return Response.ok("No se puede actualizar el evento. Motivo: Evento inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        service.update(event);
        return Response.ok("El evento fue actualizado exitosamente").status(HttpStatus.OK_200).build();
    }

    public OutingEventDTO toDTO(OutingEvent oe) {
        OutingEventDTO dto = new OutingEventDTO();
        dto.setId(oe.getId());
        dto.setName(oe.getName());
        dto.setDescription(oe.getDescription());
        dto.setAddress(oe.getAddress().toArray());
        dto.setPrice(oe.getPrice());

        List<String> tags = new ArrayList<>();
        oe.getTags().parallelStream().forEach(o -> tags.add(o.getName()));
        dto.setTags(tags);


        dto.setStartDateTime(oe.getStartDateTime().toString());
        dto.setEndDateTime(oe.getEndDateTime().toString());

        return dto;
    }

    public OutingEvent fromDTO(OutingEventDTO dto, OutingEvent... oe) {
        OutingEvent o = oe.length > 0 ? oe[0] : new OutingEvent();
        o.setId(dto.getId());
        o.setName(dto.getName());
        o.setDescription(dto.getDescription());
        o.setPrice(dto.getPrice());
        o.setAddress(Address.fromArray(dto.getAddress()));

        List<Tag> tags = new ArrayList<>();
        dto.getTags().parallelStream().forEach(t -> tags.add(new Tag(t)));
        o.setTags(tags);

        o.setStartDateTime(LocalDateTime.parse(dto.getStartDateTime()));
        o.setEndDateTime(LocalDateTime.parse(dto.getEndDateTime()));

        return o;
    }


}
