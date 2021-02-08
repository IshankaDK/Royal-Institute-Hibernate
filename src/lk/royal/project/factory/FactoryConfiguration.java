package lk.royal.project.factory;

import lk.royal.project.entity.Course;
import lk.royal.project.entity.Login;
import lk.royal.project.entity.Registration;
import lk.royal.project.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
            sessionFactory = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Registration.class)
                    .addAnnotatedClass(Login.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

}
