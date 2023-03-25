package application;

import model.entities.Department;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        
        SellerProgram sellerProgram = new SellerProgram();
        DepartmentProgram departmentProgram = new DepartmentProgram();
        
        System.out.print("""
                Implementação Padrão de Projeto Data Access Object
                
                1 - Seller
                2 - Department
                """);
        System.out.print("\nDigite a operação: ");
        int op = sc.nextInt();
        
        if (op == 1)
        {
            sellerProgram.sellerProgram();
        }
        else if (op == 2)
        {
            departmentProgram.departmentProgram();
        }
        else System.out.println("\nNenhuma opção válida selecionada");
    }
}