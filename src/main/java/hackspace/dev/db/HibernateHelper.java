package hackspace.dev.db;

import hackspace.dev.pojo.BaseEntity;
import hackspace.dev.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

import static hackspace.dev.utils.HibernateUtils.commitCloseSession;
import static hackspace.dev.utils.HibernateUtils.getInitedSession;

/**
 * Created by alex on 2/19/17.
 */
public final class HibernateHelper {
    private HibernateHelper() {}

    public static void saveEntity(Object object) {
        Session session = getInitedSession();
        session.save(object);
        HibernateUtils.commitCloseSession(session);
    }

    public static <T extends BaseEntity> List<T> selectEntities(String selectQuery, Class<T> clazz) {
        Session session = getInitedSession();
        Query query = session.createQuery(selectQuery);
        List list = query.list();
        commitCloseSession(session);
        return list;
    }

    public static <T extends BaseEntity> void updateEntity(T entity) {
        Session session = getInitedSession();
        session.update(entity);
        commitCloseSession(session);
    }

    public static void deleteEntity(Object entity) {
        Session session = getInitedSession();
        session.delete(entity);
        commitCloseSession(session);
    }

    public static void deleteEntity(Class clazz, Integer id) {
        Session session = getInitedSession();
        session.delete(session.get(clazz, id));
        commitCloseSession(session);
    }

    public static <T extends BaseEntity> T getEntity(Class<T> clazz, int id) {
        Session session = getInitedSession();
        T entity  = (T)session.get(clazz, id);
        commitCloseSession(session);
        return entity;
    }

    public static <T extends BaseEntity> T getEntity(Class<T> clazz, Query dbQuery) {
        T result = (T) dbQuery.uniqueResult();
        return result;
    }

    public static Query createQuery(String query) {
        Session initedSession = getInitedSession();
        return initedSession.createQuery(query);
    }
}
