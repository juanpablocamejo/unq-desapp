package services;

import exceptions.EntityValidationException;
import model.builders.outings.OutingEventBuilder;
import model.locations.Address;
import model.locations.Coord;
import model.outings.OutingEvent;
import org.joda.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.OutingEventDAO;
import persistence.strategies.OutingFilter;
import services.initialization.Initializable;

import java.util.List;

public class OutingEventService extends GenericService<OutingEvent> implements Initializable {

    private AddressDAO addressDAO;

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
                .withDescription("Misa India en Tandil")
                .withPrice(800)
                .withMaxAssistants(200000)
                .withImage("/images/indio.jpg")
                .withAddress(new Address(new Coord(-37.3021237, -59.1132899), "Tandil"))
                .withStartDateTime(new LocalDateTime("2016-03-12"))
                .build();
        OutingEvent ochentoso = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta Ochentosa")
                .withDescription("Revivi los 80' en esta fiesta descomunal.")
                .withPrice(100)
                .withMaxAssistants(200)
                .withImage("/images/ochentoso.jpg")
                .withAddress(new Address(new Coord(-34.585951, -58.434559), "Palermo"))
                .withStartDateTime(new LocalDateTime("2017-01-07"))
                .build();
        OutingEvent oktober = OutingEventBuilder.anOutingEvent()
                .withName("Oktober Fest")
                .withDescription("La fiesta de la cerveza mas popular del mundo. No te podes quedar afuera.")
                .withPrice(500)
                .withMaxAssistants(300)
                .withImage("/images/oktober.jpg")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .withStartDateTime(new LocalDateTime("2016-10-08"))
                .build();
        OutingEvent paintBall = OutingEventBuilder.anOutingEvent()
                .withName("PaintBall")
                .withDescription("Oferta de Paintball para 10 personas")
                .withPrice(80).withMaxAssistants(10)
                .withImage("/images/paintball.jpg")
                .withAddress(new Address(new Coord(-34.5343855, -58.4510147), "Ezeiza"))
                .withStartDateTime(new LocalDateTime("2016-12-12"))
                .build();
        OutingEvent comicCon = OutingEventBuilder.anOutingEvent()
                .withName("ComicCon 2016")
                .withDescription("El evento mas esperado del 2016!")
                .withPrice(1500).withMaxAssistants(500)
                .withImage("/images/comiccon.png")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .withStartDateTime(new LocalDateTime("2016-12-10"))
                .build();
        OutingEvent gameAwards = OutingEventBuilder.anOutingEvent()
                .withName("Game Awards 2016")
                .withDescription("Evento ideal para gamers. Candidato: Overwatch de Blizzard")
                .withPrice(1000).withMaxAssistants(1000)
                .withImage("/images/game.jpg")
                .withAddress(new Address(new Coord(-34.6065844, -58.4105916), "CABA"))
                .withStartDateTime(new LocalDateTime("2016-12-27"))
                .build();
        OutingEvent chori = OutingEventBuilder.anOutingEvent()
                .withName("Choripateada UNQ")
                .withDescription("Pa' despedir el año")
                .withPrice(50).withMaxAssistants(200)
                .withImage("/images/chori.jpg")
                .withAddress(new Address(new Coord(-34.5848217, -58.393176), "Recoleta"))
                .withStartDateTime(new LocalDateTime("2016-12-28"))
                .build();
        OutingEvent feria = OutingEventBuilder.anOutingEvent()
                .withName("Feria americana")
                .withDescription("Comprá todas las boludeces que quieras...")
                .withPrice(10)
                .withMaxAssistants(150)
                .withImage("/images/feria.jpg")
                .withAddress(new Address(new Coord(-34.6277849, -58.3806365), "Constitucion"))
                .withStartDateTime(new LocalDateTime("2017-01-05"))
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
    public void save(OutingEvent object) throws EntityValidationException {
        OutingEvent newOutingEvent = OutingEventBuilder.anOutingEvent().build();
        super.save(newOutingEvent);
        object.setId(newOutingEvent.getId());
        addressDAO.save(object.getAddress());
        super.update(object);
    }

    @Transactional
    public List<OutingEvent> searchEvents(OutingFilter filter) {
        OutingEventDAO repo = (OutingEventDAO) getRepository();
        return repo.findEvents(filter);
    }
}
