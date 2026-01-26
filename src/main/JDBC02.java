package main;

import java.util.List;

import db.CustomerDAO;
import vo.CustomerDTO;

public class JDBC02 {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        
        //고객 추가
        // CustomerDTO customerDTO = new CustomerDTO();
        // customerDTO.setCustname("홍길동");
        // customerDTO.setAddress("서울시 어딘가");
        // customerDTO.setPhone("010-1111-2222");
        // customerDAO.addCustomer(customerDTO);

        //모든 회원 조회
        List<CustomerDTO> list = customerDAO.getAllCustomers();
        list.stream().forEach(customer -> System.out.println(customer));

        //특정 회원 조회
        CustomerDTO customer = customerDAO.getCustomer("홍길동");
        System.out.println(customer);

        //특정 회원 수정(기본키)
        //customerDAO.updateCustomer("수원시 어딘가", 2);

        //특정 회원 삭제(기본키)
        customerDAO.deleteBook(2);
    }
    
    
    //특정 회원 수정(기본키)

}
