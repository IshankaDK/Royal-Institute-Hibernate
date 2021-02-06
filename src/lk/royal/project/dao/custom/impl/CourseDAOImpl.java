package lk.royal.project.dao.custom.impl;

import lk.royal.project.dao.custom.CourseDAO;
import lk.royal.project.entity.Course;
import lk.royal.project.entity.Student;
import lk.royal.project.factory.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Course load = session.load(Course.class, s);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        List<Course> list = session.createCriteria(Course.class).list();

        transaction.commit();

        session.close();
        return list;
    }
}
