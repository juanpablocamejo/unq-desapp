package persistence;

import model.tags.Tag;

public class TagDAO extends HibernateGenericDAO<Tag> implements GenericRepository<Tag> {
    @Override
    protected Class<Tag> getDomainClass() {
        return Tag.class;
    }
}
