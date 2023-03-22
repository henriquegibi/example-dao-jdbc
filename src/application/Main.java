package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    
        SellerDao sellerDao = DaoFactory.createSellerDao();
    
        System.out.println("\n **** TESTE 1: findById **** \n");
        System.out.println("\n" + sellerDao.findById(3));
    
        System.out.println("\n **** TESTE 2: findByDepartment **** \n");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        System.out.println();
        for (Seller obj : list)
        {
            System.out.println(obj);
        }
    
        System.out.println("\n **** TESTE 3: findByAll **** \n");
        list = sellerDao.findAll();
        System.out.println();
        for (Seller obj : list)
        {
            System.out.println(obj);
        }
    }
}