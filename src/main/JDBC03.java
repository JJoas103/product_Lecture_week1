package main;

import db.BookDAO;

import java.util.List;
import java.util.Scanner;

public class JDBC03 {
    
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        Scanner scan = new Scanner(System.in);

        System.out.println("고객 이름 검색 >>");
        String name = scan.nextLine();

        List<BookDAO> list = bookDAO.getOrderInfo(name);
    }
}
