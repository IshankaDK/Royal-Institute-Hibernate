package lk.royal.project.dao;

import lk.royal.project.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO  <Entity extends SuperEntity,ID extends Serializable> extends SuperDAO{
    public boolean add(Entity entity)throws Exception;

    public boolean delete(ID id)throws Exception;

    public boolean update(Entity entity)throws Exception;

    public Entity find(ID id)throws Exception;

    public List<Entity> findAll()throws Exception;
}
