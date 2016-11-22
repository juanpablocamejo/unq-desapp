package services.initialization;

import exceptions.EntityValidationException;

public class ServiceInitializer {
    public ServiceInitializer(Initializable... services) throws EntityValidationException {
        for (Initializable o : services) {
            o.initialize();
        }
    }
}
