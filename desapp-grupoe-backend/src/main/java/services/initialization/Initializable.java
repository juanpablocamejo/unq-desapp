package services.initialization;

import exceptions.EntityValidationException;

public interface Initializable {

    void initialize() throws EntityValidationException;
}
