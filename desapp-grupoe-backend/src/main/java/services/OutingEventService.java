package services;

import exceptions.EntityValidationException;
import model.builders.outings.OutingEventBuilder;
import model.locations.Address;
import model.locations.Coord;
import model.outings.OutingEvent;
import model.tags.Tag;
import org.joda.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.OutingEventDAO;
import persistence.TagDAO;
import persistence.strategies.OutingFilter;
import services.initialization.Initializable;

import java.util.List;
import java.util.regex.Pattern;

public class OutingEventService extends GenericService<OutingEvent> implements Initializable {

    private AddressDAO addressDAO;
    private TagDAO tagDAO;

    public TagDAO getTagDAO() {
        return tagDAO;
    }

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Transactional
    public void initialize() throws EntityValidationException {
        OutingEvent indio = OutingEventBuilder.anOutingEvent()
                .withName("Recital Indio Solari")
                .withDescription("Misa India en Olavarria")
                .withPrice(800)
                .withMaxAssistants(200000)
                .withImage("/images/indio.jpg")
                .withAddress(new Address(new Coord(-36.8937167, -60.3233499), "Olavarria"))
                .withStartDateTime(new LocalDateTime("2017-03-11"))
                .withTag(tagDAO.findById(24))
                .withTag(tagDAO.findById(23))
                .build();
        OutingEvent ochentoso = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta Ochentosa")
                .withDescription("Revivi los 80' en esta fiesta descomunal.")
                .withPrice(100)
                .withMaxAssistants(200)
                .withImage("/images/ochentoso.jpg")
                .withAddress(new Address(new Coord(-34.585951, -58.434559), "Palermo"))
                .withStartDateTime(new LocalDateTime("2017-01-07"))
                .withTag(tagDAO.findById(24))
                .withTag(tagDAO.findById(23))
                .withTag(tagDAO.findById(22))
                .build();
        OutingEvent oktober = OutingEventBuilder.anOutingEvent()
                .withName("Oktober Fest")
                .withDescription("La fiesta de la cerveza mas popular del mundo. No te podes quedar afuera.")
                .withPrice(500)
                .withMaxAssistants(300)
                .withImage("/images/oktober.jpg")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .withTag(tagDAO.findById(23))
                .withTag(tagDAO.findById(22))
                .withStartDateTime(new LocalDateTime("2016-10-08"))
                .build();
        OutingEvent paintBall = OutingEventBuilder.anOutingEvent()
                .withName("PaintBall")
                .withDescription("Oferta de Paintball para 10 personas")
                .withPrice(80).withMaxAssistants(10)
                .withImage("/images/paintball.jpg")
                .withAddress(new Address(new Coord(-34.5343855, -58.4510147), "Ezeiza"))
                .withStartDateTime(new LocalDateTime("2016-12-12"))
                .withTag(tagDAO.findById(24))
                .withTag(tagDAO.findById(23))
                .build();
        OutingEvent comicCon = OutingEventBuilder.anOutingEvent()
                .withName("ComicCon 2016")
                .withDescription("El evento mas esperado del 2016!")
                .withPrice(1500).withMaxAssistants(500)
                .withImage("/images/comiccon.png")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .withStartDateTime(new LocalDateTime("2016-12-10"))
                .withTag(tagDAO.findById(23))
                .withTag(tagDAO.findById(24))
                .build();
        OutingEvent gameAwards = OutingEventBuilder.anOutingEvent()
                .withName("Game Awards 2016")
                .withDescription("Evento ideal para gamers. Candidato: Overwatch de Blizzard")
                .withPrice(1000).withMaxAssistants(1000)
                .withImage("/images/game.jpg")
                .withAddress(new Address(new Coord(-34.6065844, -58.4105916), "CABA"))
                .withStartDateTime(new LocalDateTime("2016-12-27"))
                .withTag(tagDAO.findById(23))
                .build();
        OutingEvent chori = OutingEventBuilder.anOutingEvent()
                .withName("Choripateada UNQ")
                .withDescription("Pa' despedir el año")
                .withPrice(50).withMaxAssistants(200)
                .withImage("/images/chori.jpg")
                .withAddress(new Address(new Coord(-34.5848217, -58.393176), "Recoleta"))
                .withStartDateTime(new LocalDateTime("2016-12-28"))
                .withTag(tagDAO.findById(23))
                .withTag(tagDAO.findById(24))
                .build();
        OutingEvent feria = OutingEventBuilder.anOutingEvent()
                .withName("Feria americana")
                .withDescription("Comprá todas las boludeces que quieras...")
                .withPrice(10)
                .withMaxAssistants(150)
                .withImage("/images/feria.jpg")
                .withAddress(new Address(new Coord(-34.6277849, -58.3806365), "Constitucion"))
                .withStartDateTime(new LocalDateTime("2017-01-05"))
                .withTag(tagDAO.findById(23))
                .build();
        OutingEvent partido = OutingEventBuilder.anOutingEvent()
                .withName("Partido de futbol")
                .withDescription("Argentina vs Brasil")
                .withPrice(300)
                .withMaxAssistants(75000)
                .withImage("/images/partido/jpg")
                .withAddress(new Address(new Coord(-34.5453062, -58.4497749), "Nuñez"))
                .withStartDateTime(new LocalDateTime("2017-04-18"))
                .build();
        OutingEvent espuma = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta de la espuma")
                .withDescription("Oh si... Nosotros sabemos que esperabas este momento, esta fiesta, esta espuma!")
                .withPrice(200)
                .withMaxAssistants(400)
                .withImage("/images/espuma.jpg")
                .withAddress(new Address(new Coord(-34.605632, -58.364317), "Puerto Madero"))
                .withStartDateTime(new LocalDateTime("2017-01-02"))
                .build();
        OutingEvent disfraces = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta de disfraces")
                .withDescription("Bizarreada total.. Hay varios premios en juego! No podes faltar.")
                .withPrice(250).withMaxAssistants(100)
                .withImage("/images/disfraces.jpg")
                .withAddress(new Address(new Coord(-34.610465, -58.3636987), "Puerto Madero"))
                .withStartDateTime(new LocalDateTime("2017-02-15"))
                .withTag(tagDAO.findById(23))
                .build();

        save(indio);
        save(ochentoso);
        save(oktober);
        save(paintBall);
        save(comicCon);
        save(gameAwards);
        save(chori);
        save(feria);
        save(partido);
        save(espuma);
        save(disfraces);
    }

    @Override
    @Transactional
    public void save(OutingEvent event) throws EntityValidationException {
        validate(event);
        OutingEvent newOutingEvent = OutingEventBuilder.anOutingEvent().build();
        super.save(newOutingEvent);
        event.setId(newOutingEvent.getId());
        addressDAO.save(event.getAddress());

        super.update(event);
    }

    @Transactional
    public List<OutingEvent> searchEvents(OutingFilter filter) {
        OutingEventDAO repo = (OutingEventDAO) getRepository();
        return repo.findEvents(filter);
    }
    private void validate(OutingEvent outing) {
        if (outing.getName().length()>50) {
            throw new EntityValidationException("name_too_long");
        }
        if (outing.getDescription().length()>500) {
            throw new EntityValidationException("description_too_long");
        }
        if (outing.getPrice()<0 && outing.getPrice()>500000) {
            throw new EntityValidationException("invalid_price");
        }

        if (outing.getMaxAssistants()>1000000000) {
            throw new EntityValidationException("invalid_max_assistants");
        }
    }
}
