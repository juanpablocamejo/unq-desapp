package services.appservice;

import model.builders.outings.OutingEventBuilder;
import model.outings.OutingEvent;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import persistence.OutingEventDAO;
import persistence.UserDAO;
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
    public void initialize() {
        OutingEvent oe1 = OutingEventBuilder.anOutingEvent().withName("Recital Indio Solari").withDescription("Misa India en Tandil").withPrice(800).withMaxAssistants(200000).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        OutingEvent oe2 = OutingEventBuilder.anOutingEvent().withName("Fiesta Ochentosa").withDescription("Revivi los 80").withPrice(100).withMaxAssistants(200).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        OutingEvent oe3 = OutingEventBuilder.anOutingEvent().withName("Oktober Fest").withDescription("Birritas!").withPrice(500).withMaxAssistants(300).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        OutingEvent oe4 = OutingEventBuilder.anOutingEvent().withName("PaintBall").withDescription("Oferta de Paintball para 10 personas").withPrice(80).withMaxAssistants(10).withImage("https://www.brujulea.net/public/lugares/lugarpkgi58.jpg").build();
        save(oe1);
        save(oe2);
        save(oe3);
        save(oe4);
    }

    @Override
    @Transactional
    public void save(OutingEvent object) {
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
