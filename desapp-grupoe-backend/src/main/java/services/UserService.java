package services;

import model.builders.UserBuilder;
import model.users.User;
import org.springframework.transaction.annotation.Transactional;
import services.initialization.Initializable;

public class UserService extends GenericService<User> implements Initializable {

    @Transactional
    public void initialize() {
        User tomas = UserBuilder.anyUser().withName("Tomas").withSurname("Perez").build();
        User diego = UserBuilder.anyUser().withName("Diego").withSurname("Garcia").build();
        save(tomas);
        save(diego);
    }

}
