package model.dao.impl;

import application.DepartmentProgram;
import db.Db;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDB implements DepartmentDao {
    
    private Connection conn;
    public DepartmentDaoJDB(Connection conn)
    {
        this.conn = conn;
    }
    
    @Override
    public void insert(Department obj)
    {
        PreparedStatement st = null;
        try
        {
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) " +
                    "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
    
            System.out.println("[LOG] Executando query do Insert");
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0)
            {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next())
                {
                    obj.setId(rs.getInt(1));
                }
                Db.closeResultSet(rs);
                System.out.println("[LOG] ResultSet encerrado com sucesso");
            }
            else
            {
                System.out.println("[LOG] Não foi possível fazer o update!");
                throw new DbException("Erro Inesperado! Nenhuma linha afetada! Verifique integridade do banco de dados!");
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
        }
    }
    
    @Override
    public void update(Department obj)
    {
        PreparedStatement st = null;
        try
        {
            st = conn.prepareStatement(
                    "UPDATE department " +
                    "SET Name = ? " +
                    "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            
            st.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("[LOG] Erro ao realizar o Update");
            throw new DbException(e.getMessage());
        }
        finally
        {
            Db.closeStatement(st);
            System.out.println("[LOG] Statement encerrado com sucesso");
        }
    }
    
    @Override
    public void deleteById(Integer id)
    {
        PreparedStatement st = null;
        try
        {
            st = conn.prepareStatement(
                    "DELETE FROM department " +
                    "WHERE Id = ?");
            st.setInt(1, id);
    
            System.out.println("[LOG] Executando quary do Delete por Id");
            int rows = st.executeUpdate();
    
            if (rows != 0)
            {
                System.out.println("[LOG] Departamento deletado com sucesso!");
            }
            else
            {
                System.out.println("[LOG] Nenhum departamento foi deletado!");
            }
        }
        catch (SQLException e)
        {
            System.out.println("[LOG] Erro ao realizar o Delete");
            throw new DbException(e.getMessage());
        }
        finally
        {
            Db.closeStatement(st);
            System.out.println("[LOG] Statement encerrado com sucesso");
        }
    }
    
    @Override
    public Department findById(Integer id)
    {
        System.out.println("[LOG] Iniciando a busca por Id");
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            System.out.println("[LOG] Iniciando SQL da busca por Id");
            st = conn.prepareStatement(
                    "SELECT * FROM department " +
                    "WHERE Id = ?");
            st.setInt(1, id);
            System.out.println("[LOG] Executando query da busca por Id");
            rs = st.executeQuery();
            if (rs.next())
            {
                Department dep = instantiateDepartment(rs);
                System.out.println("[LOG] Busca por ID executada com sucesso");
                return dep;
            }
            else
            {
                System.out.println("[LOG] Não foi possível realizar a busca por Id");
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
    
    private Department instantiateDepartment(ResultSet rs) throws SQLException
    {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
    
    @Override
    public List<Department> findAll()
    {
        System.out.println("[LOG] Iniciando a busca por todos os departamentos");
        PreparedStatement st = null;
        ResultSet rs = null;
        try
        {
            System.out.println("[LOG] Iniciando SQL da busca por todos os departamentos");
            st = conn.prepareStatement("SELECT * FROM department");
            
            System.out.println("[LOG] Executando query da busca por todos os departamentos");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next())
            {
                Department obj = instantiateDepartment(rs);
                list.add(obj);
            }
            System.out.println("[LOG] Busca por ID executada com sucesso");
            return list;
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
}
