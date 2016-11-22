package services;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import persistence.AddressDAO;
import persistence.ProfileDAO;
import persistence.UserDAO;


public class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserService();
    private AddressDAO addressDAO = Mockito.mock(AddressDAO.class);
    private ProfileDAO profileDAO = Mockito.mock(ProfileDAO.class);
    private UserDAO userDAO = Mockito.mock(UserDAO.class);


    @Test
    public void saveUserIsOk() {
        userService.setAddressDAO(addressDAO);
        userService.setProfileDAO(profileDAO);
        userService.setRepository(userDAO);
    }

    @Test
    public void findByEmail() {

    }

    @Test
    public void findByName() {

    }

}