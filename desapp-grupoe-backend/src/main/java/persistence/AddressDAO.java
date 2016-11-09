package persistence;

import model.locations.Address;

public class AddressDAO extends HibernateGenericDAO<Address> implements GenericRepository<Address> {
    @Override
    protected Class<Address> getDomainClass() {
        return Address.class;
    }
}
