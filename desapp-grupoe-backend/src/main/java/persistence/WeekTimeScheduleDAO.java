package persistence;

import model.time.WeekTimeSchedule;

/**
 * Created by Esteban on 4/12/2016.
 */
public class WeekTimeScheduleDAO extends HibernateGenericDAO<WeekTimeSchedule> implements GenericRepository<WeekTimeSchedule> {
    @Override
    protected Class<WeekTimeSchedule> getDomainClass() {
        return WeekTimeSchedule.class;
    }
}
