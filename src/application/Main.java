package application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        System.out.print("""
                Implementação Padrão de Projeto Data Access Object
                
                1 - Seller
                2 - Department
                
                Digite a operação: """);
        int op = sc.nextInt();
        
        if (op == 1)
        {
            new SellerProgram();
        }
        else if (op == 2)
        {
            new DepartmentProgram();
        }
        else System.out.println("Nenhuma opção válida selecionada");
    }
}