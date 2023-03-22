package model.dao.impl;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    public SellerDaoJDBC(Connection conn)
    {
        this.conn = conn;
    }
    
    @Override
    public void insert(Seller obj)
    {
    
    }
    
    @Override
    public void update(Seller obj)
    {
    
    }
    
    @Override
    public void deleteById(Integer id)
    {
    
    }
    
    @Override
    public Seller findById(Integer id)
    {
        System.out.println("[LOG] Iniciando a busca por Id");
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            System.out.println("[LOG] Iniciando SQL da busca por Id");
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");
            st.setInt(1, id);
            System.out.println("[LOG] Executando query da busca por Id");
            rs = st.executeQuery();
            if(rs.next())
            {
                Department dep = instantiateDepartment(rs);
                Seller obj = instantiateSeller(rs, dep);
                System.out.println("[LOG] Busca por ID executada com sucesso");
                return obj;
            }
            else
            {
                System.out.println("[LOG] Busca por ID executada com sucesso, porém sem retorno");
                return null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("[LOG] Erro ao realizar busca por Id");
            throw new DbException(e.getMessage());
        }
        finally
        {
            Db.closeStatement(st);
            System.out.println("[LOG] Statement encerrado com sucesso");
            Db.closeResultSet(rs);
            System.out.println("[LOG] ResultSet encerrado com sucesso");
        }
    }
    
    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException
    {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }
    
    private Department instantiateDepartment(ResultSet rs) throws SQLException
    {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
    
    @Override
    public List<Seller> findAll()
    {
        return null;
    }
    
    @Override
    public List<Seller> findByDepartment(Department department)
    {
        System.out.println("[LOG] Iniciando a busca por Departamento");
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            System.out.println("[LOG] Iniciando SQL da busca por Departamento");
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");
            st.setInt(1, department.getId());
            System.out.println("[LOG] Executando query da busca por Departamento");
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next())
            {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null)
                {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            System.out.println("[LOG] Busca por ID executada com sucesso");
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("[LOG] Erro ao realizar busca por Departamento");
            throw new DbException(e.getMessage());
        }
        finally
        {
            Db.closeStatement(st);
            System.out.println("[LOG] Statement encerrado com sucesso");
            Db.closeResultSet(rs);
            System.out.println("[LOG] ResultSet encerrado com sucesso");
        }
    }
}
