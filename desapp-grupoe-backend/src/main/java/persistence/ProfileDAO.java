package persistence;

import model.users.Profile;

public class ProfileDAO extends HibernateGenericDAO<Profile> implements GenericRepository<Profile> {

    @Override
    protected Class<Profile> getDomainClass() {
        return Profile.class;
    }
}
