package services;

import model.builders.outings.OutingEventBuilder;
import model.outings.OutingEvent;
import org.springframework.transaction.annotation.Transactional;
import persistence.AddressDAO;
import services.initialization.Initializable;

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
        OutingEvent oe1 = OutingEventBuilder.anOutingEvent().withName("Event 1").withDescription("Descripcion Evento 1").withPrice(50).build();
        OutingEvent oe2 = OutingEventBuilder.anOutingEvent().withName("Event 2").withDescription("Descripcion Evento 2").withPrice(20).build();
        OutingEvent oe3 = OutingEventBuilder.anOutingEvent().withName("Event 3").withDescription("Descripcion Evento 3").withPrice(100).build();
        OutingEvent oe4 = OutingEventBuilder.anOutingEvent().withName("Event 4").withDescription("Descripcion Evento 4").withPrice(600).build();
        save(oe1);
        save(oe2);
        save(oe3);
        save(oe4);
    }

    @Override
    @Transactional
    public void save(OutingEvent object) {
        addressDAO.save(object.getAddress());
        super.save(object);
    }
}
