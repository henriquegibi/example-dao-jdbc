package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    
        SellerDao sellerDao = DaoFactory.createSellerDao();
    
        System.out.println("\n **** TESTE: findById **** \n");
        System.out.println(sellerDao.findById(3));
    
        System.out.println("\n **** TESTE: findByDepartment **** \n");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        for (Seller obj : list)
        {
            System.out.println(obj);
        }
    }
}