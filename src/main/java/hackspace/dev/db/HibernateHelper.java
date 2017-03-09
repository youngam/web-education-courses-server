package hackspace.dev.db;

import hackspace.dev.pojo.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alex on 2/19/17.
 */
public final class HibernateHelper {

    @PersistenceContext
    public static EntityManager manager =
            Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();
    private HibernateHelper() {}

    public static void saveEntity(Object object) {
        execute(() -> manager.persist(object));
    }

    public static <T extends BaseEntity> List<T> selectEntities(String selectQuery, Class<T> clazz) {
        TypedQuery<T> query = manager.createQuery(selectQuery, clazz);
        return query.getResultList();
    }

    public static <T extends BaseEntity> void updateEntity(T entity) {
        execute(() -> manager.merge(entity));
    }

    public static void deleteEntity(final Object entity) {
        execute(() -> manager.remove(entity));
    }

    private static void execute(Runnable runnable) {
        manager.getTransaction().begin();
        runnable.run();
        manager.getTransaction().commit();
    }

    public static <T extends BaseEntity> void  deleteEntity(Class<T> clazz, Integer id) {
        deleteEntity(getEntity(clazz, id));
    }

    public static <T extends BaseEntity> T getEntity(Class<T> clazz, int id) {
        return manager.find(clazz, id);
    }

    public static <T extends BaseEntity> T getEntity(Class<T> clazz, Query dbQuery) {
        T result = (T) dbQuery.getSingleResult();
        return result;
    }

    public static Query createQuery(String query) {
        return manager.createQuery(query);
    }
}
