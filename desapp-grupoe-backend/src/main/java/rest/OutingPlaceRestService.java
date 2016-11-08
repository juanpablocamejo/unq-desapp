package rest;

import model.locations.Address;
import model.outings.OutingPlace;
import model.tags.Tag;
import model.users.User;
import org.eclipse.jetty.http.HttpStatus;
import rest.dto.OutingPlaceDTO;
import services.appservice.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("places")
public class OutingPlaceRestService extends GenericRestService<OutingPlace> {

    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<OutingPlaceDTO> getPlaces() {
        List<OutingPlace> outings = super.getAll();
        List<OutingPlaceDTO> dtos = new ArrayList<>();

        for (OutingPlace op : outings) {
            dtos.add(toDTO(op));
        }
        return dtos;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findPlacesById(@PathParam("id") int id) {
        OutingPlace obj = super.findById(id);
        if (obj == null) {
            return Response.ok("No se encontro la entidad con el id: " + id).status(HttpStatus.NOT_FOUND_404).build();
        } else {
            OutingPlaceDTO dto = toDTO(obj);
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
    public Response createPlace(OutingPlaceDTO dto) {
        OutingPlace place = fromDTO(dto);
        return super.create(place);
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlace(OutingPlaceDTO dto) {
        OutingPlace place = fromDTO(dto, service.findById(dto.getId()));
        if (place == null) {
            return Response.ok("No se puede actualizar el lugar. Motivo: Lugar inexistente").status(HttpStatus.NOT_FOUND_404).build();
        }
        service.update(place);
        return Response.ok("El lugar fue actualizado exitosamente").status(HttpStatus.OK_200).build();
    }

    @PUT
    @Path("/{idPlace}/addAssistant/{idUser}")
    @Produces("application/json")
    public Response addAssistant(@PathParam("idPlace") int idPlace, @PathParam("idUser") int idUser) {
        OutingPlace place = service.findById(idPlace);
        User user = userService.findById(idUser);
        if (place == null) {
            return Response.ok("No existe un lugar con el id: " + idPlace).status(HttpStatus.NOT_FOUND_404).build();
        }

        if (user == null) {
            return Response.ok("No existe un usuario con id: " + idUser).status(HttpStatus.NOT_FOUND_404).build();
        }

        place.addAssistant(user);
        service.update(place);
        return Response.ok(place).status(HttpStatus.OK_200).build();
    }

    public OutingPlaceDTO toDTO(OutingPlace op) {
        OutingPlaceDTO dto = new OutingPlaceDTO();
        dto.setId(op.getId());
        dto.setName(op.getName());
        dto.setDescription(op.getDescription());
        dto.setAddress(op.getAddress().toArray());

        List<String> assistants = new ArrayList<>();
        op.getAssistants().parallelStream().forEach(a -> assistants.add(a.toString()));
        dto.setAssistants(assistants);

        dto.setPrice(op.getPrice());

        List<String> tags = new ArrayList<>();
        op.getTags().parallelStream().forEach(o -> tags.add(o.getName()));
        dto.setTags(tags);
        dto.setWeekTimeSchedule(op.getWeekTimeSchedule().toString());
        return dto;
    }

    public OutingPlace fromDTO(OutingPlaceDTO dto, OutingPlace... op) {
        OutingPlace o = op.length > 0 ? op[0] : new OutingPlace();
        o.setId(dto.getId());
        o.setName(dto.getName());
        o.setDescription(dto.getDescription());
        o.setPrice(dto.getPrice());
        o.setAddress(Address.fromArray(dto.getAddress()));

        List<Tag> tags = new ArrayList<>();
        dto.getTags().parallelStream().forEach(name -> tags.add(new Tag(name)));
        o.setTags(tags);

        List<User> assistants = new ArrayList<>();
        dto.getAssistants().parallelStream().forEach(f -> assistants.add(userService.findById(Integer.parseInt(f.split(",")[0]))));
        o.setAssistants(assistants);

        //o.setWeekTimeSchedule(dto.getWeekTimeSchedule().parseFromString());

        return o;
    }
}

