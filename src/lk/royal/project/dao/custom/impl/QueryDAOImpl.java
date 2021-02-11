package lk.royal.project.dao.custom.impl;

import lk.royal.project.dao.QueryDAO;
import lk.royal.project.entity.Student;
import lk.royal.project.factory.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Student> getCourseWiseStudent(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query sqlQuery = session.createNativeQuery("SELECT DISTINCT s.id,s.name,s.address,s.contact,s.dob,s.gender FROM Student s,Registration r,Course c " +
                "WHERE (c.code=r.course_code && s.id = r.student_id) && c.code = :id");
//        Query query = session.createQuery("SELECT s FROM Student s  join fetch s.registration");
        sqlQuery.setParameter("id", id);

        List<Student> resultList = (List<Student>) sqlQuery.getResultList();

        transaction.commit();
        session.close();

        List<Student> studentList = new ArrayList<>();
        Iterator itr = resultList.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            String sId = String.valueOf(obj[0]);
            String sName = String.valueOf(obj[1]);
            String sAddress = String.valueOf(obj[2]);
            String sContact = String.valueOf(obj[3]);
            String sDob = String.valueOf(obj[4]);
            String sGender = String.valueOf(obj[5]);
            studentList.add(new Student(sId, sName, sAddress, sContact, sDob, sGender));
        }
        System.out.println(studentList);
        return studentList;
    }

    @Override
    public int getTotalStudent() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query sqlQuery = session.createNativeQuery("SELECT COUNT(id) FROM Student");
        int count = ((BigInteger) sqlQuery.uniqueResult()).intValue();
        transaction.commit();
        session.close();
        return count;
    }

    @Override
    public int getTotalCourse() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query sqlQuery = session.createNativeQuery("SELECT COUNT(code) FROM Course");
        int count = ((BigInteger) sqlQuery.uniqueResult()).intValue();
        transaction.commit();
        session.close();
        return count;
    }
}
