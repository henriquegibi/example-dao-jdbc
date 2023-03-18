package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {
    
    private static Connection conn = null;
    
    public static Connection getConnection()
    {
        System.out.println("[LOG] Iniciando conexão com o banco de dados");
        if (conn == null) {
            try
            {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                System.out.println("[LOG] Conexão realizada com sucesso");
            }
            catch (SQLException e)
            {
                System.out.println("[LOG] Erro ao conectar com a base de dados");
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection()
    {
        System.out.println("[LOG] Iniciando a desconexão com o banco de dados");
        if (conn != null)
        {
            try
            {
                conn.close();
                System.out.println("[LOG] Banco de dados desconectado com sucesso");
            }
            catch (SQLException e)
            {
                System.out.println("[LOG] Falha ao desconectar com o banco de dados");
                throw new DbException(e.getMessage());
            }
        }
    }
    
    private static Properties loadProperties()
    {
        System.out.println("[LOG] Carregando informações de conexão com a base de dados");
        try (FileInputStream fs = new FileInputStream("db.properties"))
        {
            Properties props = new Properties();
            props.load(fs);
            System.out.println("[LOG] Informações carregadas com sucesso");
            return props;
        }
        catch (IOException e)
        {
            System.out.println("[LOG] Erro ao carregar as informações");
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeStatement(Statement st)
    {
        System.out.println("[LOG] Iniciando o encerramento do Statement");
        if (st != null)
        {
            try
            {
                st.close();
                System.out.println("[LOG] Statement encerrado com sucesso");
            } catch (SQLException e)
            {
                System.out.println("[LOG] Falha ao encerrar o Statement");
                throw new DbException(e.getMessage());
            }
        }
    }
    
    public static void closeResultSet(ResultSet rs)
    {
        System.out.println("[LOG] Iniciando o encerramento do ResultSet");
        if (rs != null)
        {
            try
            {
                rs.close();
                System.out.println("[LOG] ResultSet encerrado com sucesso");
            }
            catch (SQLException e)
            {
                System.out.println("[LOG] Falha ao encerrar o ResultSet");
                throw new DbException(e.getMessage());
            }
        }
    }
}