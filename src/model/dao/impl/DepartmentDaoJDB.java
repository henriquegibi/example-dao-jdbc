package model.dao.impl;

import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoJDB implements DepartmentDao {
    
    
    public DepartmentDaoJDB(Connection connection)
    {
    
    }
    
    @Override
    public void insert(Department obj)
    {
    
    }
    
    @Override
    public void update(Department obj)
    {
    
    }
    
    @Override
    public void deleteById(Integer id)
    {
    
    }
    
    @Override
    public Department findById(Integer id)
    {
        return null;
    }
    
    @Override
    public List<Department> findAll()
    {
        return null;
    }
}
