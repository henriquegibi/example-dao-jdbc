package model.dao;

import db.Db;
import model.dao.impl.DepartmentDaoJDB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    
    public static SellerDao createSellerDao()
    {
        return new SellerDaoJDBC(Db.getConnection());
    }
    
    public static DepartmentDao createDepartmentDao()
    {
        return new DepartmentDaoJDB(Db.getConnection());
    }
}
