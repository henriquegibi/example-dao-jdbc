package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DepartmentProgram {
    
    public void departmentProgram()
    {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
    
        System.out.println("\n **** TESTE 1: findById **** \n");
        System.out.println("\n" + departmentDao.findById(3));
    
        System.out.println("\n **** TESTE 2: findByDepartment **** \n");
        List<Department> list = departmentDao.findAll();
        System.out.println();
        for (Department obj : list)
        {
            System.out.println(obj);
        }
    
        System.out.println("\n **** TESTE 3: seller insert **** \n");
        Department newDepartment = new Department(null, "Gaming");
        departmentDao.insert(newDepartment);
        System.out.println("[LOG] Departamento inserido. Id: " + newDepartment.getId() + ", Nome: " + newDepartment.getName());
    
        System.out.println("\n **** TESTE 4: seller update **** \n");
        Department department = departmentDao.findById(1);
        department.setName("Food");
        departmentDao.update(department);
        System.out.println("[LOG] Departamento atualizado.");
    
        System.out.println("\n **** TESTE 6: seller delete **** \n");
        System.out.print("Enter ID for department delete: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        
        sc.close();
    }
}
