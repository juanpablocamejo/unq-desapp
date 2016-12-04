package services;

import exceptions.EntityValidationException;
import model.builders.outings.OutingEventBuilder;
import model.locations.Address;
import model.locations.Coord;
import model.outings.OutingEvent;
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
                .withImage("http://bucket.rollingstone.com.ar/anexos/fotos/23/2137823.jpg")
                .withAddress(new Address(new Coord(-37.3021237, -59.1132899), "Tandil"))
                .build();
        OutingEvent ochentoso = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta Ochentosa")
                .withDescription("Revivi los 80")
                .withPrice(100)
                .withMaxAssistants(200)
                .withImage("https://rlv.zcache.es/fiesta_80s_invitacion_13_3_cm_x_13_3cm-r272ecc5c59fe42e997546cfea2fe72b9_zk9yi_324.jpg?rlvnet=1")
                .withAddress(new Address(new Coord(-34.585951, -58.434559), "Palermo"))
                .build();
        OutingEvent oktober = OutingEventBuilder.anOutingEvent()
                .withName("Oktober Fest")
                .withDescription("Birritas!")
                .withPrice(500)
                .withMaxAssistants(300)
                .withImage("http://1.bp.blogspot.com/-23TTolMDie8/VfbPKTgtJMI/AAAAAAAAPOw/TTljk81h11Y/s1600/Oktoberfest-Logo-2015-2.jpg")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .build();
        OutingEvent paintBall = OutingEventBuilder.anOutingEvent()
                .withName("PaintBall")
                .withDescription("Oferta de Paintball para 10 personas")
                .withPrice(80).withMaxAssistants(10)
                .withImage("https://i.ytimg.com/vi/r2g4vQR_1i8/maxresdefault.jpg")
                .withAddress(new Address(new Coord(-34.5343855, -58.4510147), "Ezeiza"))
                .build();
        OutingEvent comicCon = OutingEventBuilder.anOutingEvent()
                .withName("ComicCon 2016")
                .withDescription("El evento mas esperado del 2016!")
                .withPrice(1500).withMaxAssistants(500)
                .withImage("http://www.argentinacomiccon.com.ar/wp-content/uploads/2016/08/logo-preview.png")
                .withAddress(new Address(new Coord(-34.570165, -58.396094), "Costa Salguero"))
                .build();
        OutingEvent gameAwards = OutingEventBuilder.anOutingEvent()
                .withName("Game Awards 2016")
                .withDescription("Evento ideal para gamers. Candidato: Overwatch de Blizzard")
                .withPrice(1000).withMaxAssistants(1000)
                .withImage("http://sm.ign.com/ign_es/screenshot/default/2016111618176-1_n9hf.jpg")
                .withAddress(new Address(new Coord(-34.6065844, -58.4105916), "CABA"))
                .build();
        OutingEvent chori = OutingEventBuilder.anOutingEvent()
                .withName("Choripateada UNQ")
                .withDescription("Pa' despedir el año")
                .withPrice(50).withMaxAssistants(200)
                .withImage("https://ugc.kn3.net/i/origin/http://farm9.static.flickr.com/8322/7975156453_f1d168cf0d.jpg")
                .withAddress(new Address(new Coord(-34.5848217, -58.393176), "Recoleta"))
                .build();
        OutingEvent feria = OutingEventBuilder.anOutingEvent()
                .withName("Feria americana")
                .withDescription("Comprá todas las boludeces que quieras...")
                .withPrice(10)
                .withMaxAssistants(150)
                .withImage("http://www.baraderoteinforma.com.ar/wp-content/uploads/2016/09/feria.jpg")
                .withAddress(new Address(new Coord(-34.6277849, -58.3806365), "Constitucion"))
                .build();
        OutingEvent partido = OutingEventBuilder.anOutingEvent()
                .withName("Partido de futbol")
                .withDescription("Argentina vs Brasil")
                .withPrice(300)
                .withMaxAssistants(75000)
                .withImage("http://toqueygambeta.files.wordpress.com/2011/06/estadio-monumental-copa-america6.jpg")
                .withAddress(new Address(new Coord(-34.5453062, -58.4497749), "Nuñez"))
                .build();
        OutingEvent espuma = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta de la espuma")
                .withDescription("Oh si....")
                .withPrice(200)
                .withMaxAssistants(400)
                .withImage("http://leporepropiedades.com.ar/public/lepore/templates/img/sections/barrios/barrio-puerto-madero.jpg")
                .withAddress(new Address(new Coord(-34.605632, -58.364317), "Puerto Madero"))
                .build();
        OutingEvent disfraces = OutingEventBuilder.anOutingEvent()
                .withName("Fiesta de disfraces")
                .withDescription("Bizarreada total.. Hay varios premios en juego! No podes faltar.")
                .withPrice(250).withMaxAssistants(100)
                .withImage("http://media.informedigital.com.ar//fotos/2011/10/14/o_1318617360.jpg")
                .withAddress(new Address(new Coord(-34.610465, -58.3636987), "Puerto Madero"))
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
