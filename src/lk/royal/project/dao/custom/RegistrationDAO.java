package lk.royal.project.dao.custom;

import lk.royal.project.dao.CrudDAO;
import lk.royal.project.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration,String> {
    public String getLastRegId()throws Exception;
}
