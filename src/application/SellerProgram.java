package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SellerProgram {
    
    public void sellerProgram()
    {
        Scanner sc = new Scanner(System.in);
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
    
        System.out.println("\n **** TESTE 4: seller insert **** \n");
        Seller newSeller = new Seller (null, "Mulan", "mulan@email.com", new Date(), 5000.0, new Department(2, null));
        sellerDao.insert(newSeller);
        System.out.println("[LOG] Vendedor inserido. Id: " + newSeller.getId());
    
        System.out.println("\n **** TESTE 5: seller update **** \n");
        Seller seller = sellerDao.findById(1);
        seller.setName("Jiraya");
        seller.setBaseSalary(2500);
        sellerDao.update(seller);
        System.out.println("[LOG] Vendedor altualizado.");
    
        System.out.println("\n **** TESTE 6: seller delete **** \n");
        System.out.print("Enter ID for delete seller: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
    
        sc.close();
    }
}
