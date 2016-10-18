package persistence;

import model.users.Profile;

public class ProfileDao extends HibernateGenericDAO<Profile> implements GenericRepository<Profile> {

    @Override
    protected Class<Profile> getDomainClass() {
        return Profile.class;
    }
}
